package testing;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 9, 2008
 * Time: 4:46:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class DupOne {

    private String oneProp1;
    private String oneProp2;

    private List<DupTwo> twos = new ArrayList<DupTwo>();


    public String toString() {
        String s = "hashCode="+hashCode()+"\n"+
                   "oneProp1=" + oneProp1 + "\n" +
                   "oneProp2=" + oneProp2 + "\n";
        int a = 1;
        for (DupTwo two: twos) {
            s = s   + "twos ["+a+"] \n"+two;
            a++;
        }

        return s;
    }


    public void setXxx(String s) { }
    public String getXxx() { return null; }

    public String getOneProp1() {
        return oneProp1;
    }

    public void setOneProp1(String oneProp1) {
        this.oneProp1 = oneProp1;
    }

    public String getOneProp2() {
        return oneProp2;
    }

    public void setOneProp2(String oneProp2) {
        this.oneProp2 = oneProp2;
    }

    public List<DupTwo> getTwos() {
        return twos;
    }

    public void setTwos(List<DupTwo> twos) {
        this.twos = twos;
    }
}
