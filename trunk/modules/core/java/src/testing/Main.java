package testing;

import net.sf.dozer.util.mapping.MapperIF;
import net.sf.dozer.util.mapping.DozerBeanMapper;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 9, 2008
 * Time: 4:42:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        One one = new One("oneProp1", "oneProp2");
        one.addTwo(new Two("twoProp1", "twoProp2", one));
        one.addTwo(new Two("twoProp1_1", "twoProp2_2", one));

        MapperIF mapper = new DozerBeanMapper();
        DupOne dupOne = (DupOne) mapper.map(one, DupOne.class);

        System.out.println(dupOne);
    }
}
