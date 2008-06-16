package testing;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 9, 2008
 * Time: 4:42:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class One {

    private String oneProp1;
    private String oneProp2;

    private List<Two> twos;

    public One(String oneProp1, String oneProp2) {
        this.oneProp1 = oneProp1;
        this.oneProp2 = oneProp2;

        twos = new ArrayList<Two>();
    }


    public void addTwo(Two two) {
        twos.add(two);
    }


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

    public List<Two> getTwos() {
        return twos;
    }

    public void setTwos(List<Two> twos) {
        this.twos = twos;
    }


    public void setYyy(String s) {
    }

    public String getYyy() {
        return null;
    }
}
