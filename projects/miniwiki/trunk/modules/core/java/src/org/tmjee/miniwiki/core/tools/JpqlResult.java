package org.tmjee.miniwiki.core.tools;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.Collection;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class JpqlResult implements Result {

    private Object jpqlReturnedObject;

    public JpqlResult(Object jpqlReturnedObject) {
        this.jpqlReturnedObject = jpqlReturnedObject;
    }

    public String print() {
        StringBuffer s = new StringBuffer();
        printJpqlResult(jpqlReturnedObject, s);
        return s.toString();
    }
    
    protected void printJpqlResult(Object jpqlReturnedObject, StringBuffer result) {
        if (jpqlReturnedObject instanceof Collection) {
            int count = 1;
            for (Object o : ((Collection)jpqlReturnedObject)) {
                result.append(count+"]");
                printJpqlResult(o, result);
                count++;
            }
        }
        else if (jpqlReturnedObject.getClass().isArray()) {
            int count = 1;
            for (Object o : (Object[])jpqlReturnedObject) {
                result.append(count+"]");
                printJpqlResult(o, result);
                count++;
            }
        }
        else {
            result.append(ReflectionToStringBuilder.toString(jpqlReturnedObject));
            result.append("\n");
        }
    }
}
