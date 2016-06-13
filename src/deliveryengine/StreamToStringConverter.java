package deliveryengine;

import java.io.*;

public class StreamToStringConverter
{
    InputStream in;
    
    public StreamToStringConverter(final InputStream in) {
        this.in = in;
    }
    
    @Override
    public String toString() {
        return this.convertStreamToString(this.in);
    }
    
    private String convertStreamToString(final InputStream is) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        final StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            try {
                is.close();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return sb.toString();
    }
}
