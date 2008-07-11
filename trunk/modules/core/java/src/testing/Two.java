package testing;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class Two {

    private String twoProp1;
    private String twoProp2;

    private One one;

    public Two(String twoProp1, String twoProp2, One one) {
        this.twoProp1 = twoProp1;
        this.twoProp2 = twoProp2;
        this.one = one;
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

    public One getOne() {
        return one;
    }

    public void setOne(One one) {
        this.one = one;
    }
}
