package testing;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 9, 2008
 * Time: 4:46:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class DupTwo {

    private String twoProp1;
    private String twoProp2;

    private DupOne one;


    public String toString() {
        return  "twoProp1="+twoProp1+"\n"+
                "twoProp2="+twoProp2+"\n"+
                (one == null ? "one=null" : "one="+one.hashCode())+"\n";

    }

    public String getTwoProp1() {
        return twoProp1;
    }

    public void setTwoProp1(String twoProp1) {
        this.twoProp1 = twoProp1;
    }

    public String getTwoProp2() {
        return twoProp2;
    }

    public void setTwoProp2(String twoProp2) {
        this.twoProp2 = twoProp2;
    }

    public DupOne getOne() {
        return one;
    }

    public void setOne(DupOne one) {
        this.one = one;
    }
}
