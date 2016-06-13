package learnityasmtserver.assessmentportal.standaloneasmt;

import org.grlea.log.*;
import learnityasmtserver.assessmentportal.dbconnection.*;
import java.util.*;

public class AllStAloneTestItems
{
    public static final SimpleLogger log;
    
    public Vector getItemsAsTopic(final String topicId, final String dlevel, final String No_questions_per_topic, final String q_type) {
        Vector QuestionAsTopic = new Vector();
        try {
        	Vector<Vector> vAllDetails = new Vector<Vector>();
            final Vector vRandItemIdList1 = new Vector();
            Vector vRandItemIdList2 = new Vector();
            final Vector vItemIdList_dlevel = new Vector();
            final ReportDataBaseLayer ob1 = new ReportDataBaseLayer();
            final Vector vItemIdList = ReportDataBaseLayer.getTestItemIDs(topicId, q_type);
            if (dlevel.equals("Any")) {
                vRandItemIdList2 = this.rand(vItemIdList);
            }
            else {
                ReportDataBaseLayer.getItemId(vItemIdList, dlevel);
                vRandItemIdList2 = this.rand(vItemIdList_dlevel);
            }
            vItemIdList.removeAllElements();
            final int no_ques = Integer.parseInt(No_questions_per_topic);
            if (vRandItemIdList2.size() <= no_ques) {
                vAllDetails = ReportDataBaseLayer.getQuesTextAndType(vRandItemIdList2);
            }
            else {
                for (int m = 0; m < vRandItemIdList2.size(); ++m) {
                    vRandItemIdList1.addElement(vRandItemIdList2.elementAt(m));
                    vAllDetails = ReportDataBaseLayer.getQuesTextAndType(vRandItemIdList1);
                    if (vRandItemIdList1.size() == no_ques) {
                        break;
                    }
                }
            }
            final Vector vId = vAllDetails.elementAt(0);
            final Vector vTitle = vAllDetails.elementAt(1);
            final Vector vText = vAllDetails.elementAt(2);
            final Retrive_Item rItem = new Retrive_Item();
            QuestionAsTopic = rItem.RetriveItem(vId, vTitle, vText);
        }
        catch (Exception lEx) {
            System.out.println(lEx.toString() + " error to Generate Model Test Page According to Topic");
        }
        return QuestionAsTopic;
    }
    
    public Vector getAllItems(final Vector<String[]> vAssessmentDeffination) {
        Vector AllQuestions = new Vector();
        final Vector AllTempQuestions = new Vector();
        try {
            for (int k = 0; k < vAssessmentDeffination.size(); ++k) {
                final String[] AssessmentInfo = vAssessmentDeffination.elementAt(k);
                final String topic_id = AssessmentInfo[0];
                final String no_of_questions = AssessmentInfo[1];
                final String degree_of_difficulty = AssessmentInfo[2];
                final String question_type = AssessmentInfo[3];
                final Vector TopicWiseItems = this.getItemsAsTopic(topic_id, degree_of_difficulty, no_of_questions, question_type);
                AllTempQuestions.addElement(TopicWiseItems);
            }
            AllQuestions = this.InAVector(AllTempQuestions);
        }
        catch (Exception lEx) {
            System.out.println(lEx.toString() + " error to gets all items  in AllStAloneTestItems");
        }
        return AllQuestions;
    }
    
    public Vector InAVector(final Vector<Vector> vTempVector) {
        final Vector vActualVector = new Vector();
        try {
            for (int k = 0; k < vTempVector.size(); ++k) {
                final Vector v1 = vTempVector.elementAt(k);
                for (int m = 0; m < v1.size(); ++m) {
                    vActualVector.addElement(v1.elementAt(m));
                }
            }
        }
        catch (Exception lEx) {
            System.out.println(lEx.toString() + " error in InAVector function of AllStAloneTestItems");
        }
        return vActualVector;
    }
    
    public Vector rand(final Vector questionAll) {
        final int[] a = new int[questionAll.size()];
        for (int i = 0; i < a.length; ++i) {
            a[i] = i;
        }
        final Random rnd = new Random();
        int k = 0;
        for (int j = 0; j < a.length; ++j) {
            k = rnd.nextInt(a.length);
            final int t = a[k];
            a[k] = a[j];
            a[j] = t;
        }
        final Vector v = new Vector();
        for (int l = 0; l < a.length; ++l) {
            v.addElement(questionAll.elementAt(a[l]));
        }
        return v;
    }
    
    static {
        log = new SimpleLogger((Class)AllStAloneTestItems.class, true);
    }
}
