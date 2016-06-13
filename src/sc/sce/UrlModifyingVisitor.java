package sc.sce;

import org.htmlparser.visitors.*;
import org.grlea.log.*;
import org.htmlparser.tags.*;
import java.net.*;
import java.io.*;
import java.util.*;
import org.htmlparser.util.*;
import org.htmlparser.*;
import org.apache.oro.text.regex.*;

public class UrlModifyingVisitor extends NodeVisitor
{
    private String linkPrefix;
    private String linkPrefixForFrame;
    private StringBuffer modifiedResult;
    private Parser parser;
    private static final SimpleLogger log;
    
    public UrlModifyingVisitor(final Parser parser, final String linkPrefix, final String linkPrefixForFrame) {
        super(true, true);
        this.parser = parser;
        this.linkPrefix = linkPrefix;
        this.linkPrefixForFrame = linkPrefixForFrame;
        this.modifiedResult = new StringBuffer();
    }
    
    public void visitLinkTag(final LinkTag linkTag) {
        linkTag.setLink(this.linkPrefix + linkTag.getLink());
    }
    
    public void visitImageTag(final ImageTag imageTag) {
        imageTag.setImageURL(imageTag.getImageURL());
    }
    
    public void visitStringNode(final StringNode stringNode) {
    }
    
    public void visitTag(final Tag tag) {
        if (tag instanceof ScriptTag) {
            final ScriptTag scriptTag = (ScriptTag)tag;
            if (scriptTag.getAttribute("SRC") != null) {
                try {
                    scriptTag.getChild(0).setText(this.getResult(this.httpGet(scriptTag.getImageURL())));
                    scriptTag.removeAttribute("SRC");
                }
                catch (Exception e) {
                    UrlModifyingVisitor.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
                }
            }
            String result = scriptTag.getChild(0).getText();
            result = this.getResult(result);
            scriptTag.getChild(0).setText(result);
        }
        if (tag instanceof BodyTag) {
            final BodyTag bodyTag = (BodyTag)tag;
            bodyTag.setAttribute("BACKGROUND", this.extractLink(bodyTag));
        }
        if (tag instanceof FormTag) {
            final FormTag formTag = (FormTag)tag;
            UrlModifyingVisitor.log.debug("Action = " + formTag.getFormLocation());
            if (formTag.getFormMethod().equalsIgnoreCase("POST")) {
                formTag.setFormLocation(this.linkPrefix + formTag.getFormLocation());
                if (null != formTag.getAttribute("onsubmit")) {
                    UrlModifyingVisitor.log.debug("onsubmit = " + formTag.getAttribute("onsubmit"));
                    formTag.setAttribute("onsubmit", this.parseOnsubmit(formTag.getAttribute("onsubmit")));
                }
            }
            else {
                formTag.setFormLocation(this.linkPrefix);
                formTag.getChild(0).setText("<input type=hidden name=page value=\"" + formTag.getFormLocation() + "\">");
            }
        }
        if (tag instanceof AreaTag) {
            final AreaTag areaTag = (AreaTag)tag;
            areaTag.setLink(this.linkPrefix + areaTag.getLink());
        }
        if (tag instanceof FrameTag) {
            final FrameTag frameTag = (FrameTag)tag;
            frameTag.setAttribute("SRC", this.linkPrefixForFrame + frameTag.getFrameLocation());
        }
        if (tag instanceof CSSTag) {
            final CSSTag cssTag = (CSSTag)tag;
            cssTag.setLink(cssTag.getLink());
        }
    }
    
    public String getResult(String result) {
        final String regexpForHref = "href=\"";
        final String regexpForHrefHttp = "href=\"http";
        final String regexpForDoubleDotHref = "href=\"../";
        final String regexpForSingleDotHref = "href=\"./";
        final String regexpForAction = "action=";
        final String regexpForActionHttp = "action=http";
        final String regexpForSingleDotAction = "action=./";
        final String regexpForDoubleDotAction = "action=../";
        final Pattern patternForAction = regExprInfo("regexpForAction", regexpForAction);
        final Pattern patternForActionHttp = regExprInfo("regexpForActionHttp", regexpForActionHttp);
        final Pattern patternForSingleDotAction = regExprInfo("regexpForSingleDotAction", regexpForSingleDotAction);
        final Pattern patternForDoubleDotAction = regExprInfo("regexpForDoubleDotAction", regexpForDoubleDotAction);
        final PatternMatcher matcher = (PatternMatcher)new Perl5Matcher();
        final String uri = this.parser.getURL().trim();
        final String atn = uri.substring(0, uri.lastIndexOf(47));
        result = Util.substitute(matcher, patternForActionHttp, (Substitution)new Perl5Substitution("action = \"./delivery.SCClient?page=http"), result, -1);
        result = Util.substitute(matcher, patternForDoubleDotAction, (Substitution)new Perl5Substitution("action = ./delivery.SCClient?page=http" + atn + "/"), result, -1);
        result = Util.substitute(matcher, patternForSingleDotAction, (Substitution)new Perl5Substitution("action = ./delivery.SCClient?page=http" + atn + "/"), result, -1);
        if (!matcher.contains(result, patternForActionHttp)) {
            result = Util.substitute(matcher, patternForAction, (Substitution)new Perl5Substitution("action = ./delivery.SCClient?page=http" + atn + "/"), result, -1);
        }
        return result;
    }
    
    public String parseOnsubmit(String result) {
        final String regexpForActionHttp = "'http://";
        final Pattern patternForActionHttp = regExprInfo("regexpForActionHttp", regexpForActionHttp);
        final PatternMatcher matcher = (PatternMatcher)new Perl5Matcher();
        final String uri = this.parser.getURL().trim();
        result = Util.substitute(matcher, patternForActionHttp, (Substitution)new Perl5Substitution("'./delivery.SCClient?page=http://"), result, -1);
        return result;
    }
    
    public String httpGet(final String urlString) throws MalformedURLException, IOException {
        final URL url = new URL(urlString);
        final URLConnection connection = url.openConnection();
        connection.connect();
        final String data = new String(this.InputStreamToByteArray(connection.getInputStream()));
        return data;
    }
    
    public byte[] InputStreamToByteArray(final InputStream input) throws IOException {
        final int bufferLength = 65500;
        final byte[] buffer = new byte[bufferLength];
        final ArrayList<byte[]> bufferBuffer = new ArrayList<byte[]>();
        int bytesRead = 0;
        int bytesTotal = 0;
        while (bytesRead != -1) {
            bytesRead = input.read(buffer);
            if (bytesRead == -1) {
                break;
            }
            bytesTotal += bytesRead;
            final byte[] temp = new byte[bytesRead];
            System.arraycopy(buffer, 0, temp, 0, bytesRead);
            bufferBuffer.add(temp);
        }
        final byte[] binary = new byte[bytesTotal];
        int offset = 0;
        for (final byte[] temp2 : bufferBuffer) {
            System.arraycopy(temp2, 0, binary, offset, temp2.length);
            offset += temp2.length;
        }
        return binary;
    }
    
    public String extractLink(final BodyTag bodyTag) {
        String relativeLink = bodyTag.getAttribute("BACKGROUND");
        if (relativeLink != null) {
            relativeLink = ParserUtils.removeChars(relativeLink, '\n');
            relativeLink = ParserUtils.removeChars(relativeLink, '\r');
        }
        return bodyTag.getPage().getLinkProcessor().extract(relativeLink, bodyTag.getPage().getUrl());
    }
    
    public void visitEndTag(final Tag tag) {
        final Node parent = tag.getParent();
        if (null == parent) {
            UrlModifyingVisitor.log.debug("Name1 = " + tag.getTagName());
            this.modifiedResult.append(tag.toHtml());
        }
        else if (null == parent.getParent()) {
            this.modifiedResult.append(parent.toHtml());
        }
    }
    
    public static Pattern regExprInfo(final String thisName, final String thisExpr) {
        Pattern regExprPattern = null;
        final PatternCompiler compiler = (PatternCompiler)new Perl5Compiler();
        try {
            regExprPattern = compiler.compile(thisExpr, 1);
            return regExprPattern;
        }
        catch (MalformedPatternException mae) {
            UrlModifyingVisitor.log.debug("trouble compiling " + thisName + " -> " + mae.getMessage());
            return regExprPattern;
        }
    }
    
    public String getModifiedResult() {
        return this.modifiedResult.toString();
    }
    
    static {
        log = new SimpleLogger((Class)UrlModifyingVisitor.class);
    }
}
