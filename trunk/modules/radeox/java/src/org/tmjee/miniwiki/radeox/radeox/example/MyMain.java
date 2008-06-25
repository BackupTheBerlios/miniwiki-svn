package org.tmjee.miniwiki.radeox.example;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 24, 2008
 * Time: 4:04:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyMain {

    public static void main(String[] args) throws Exception {

        char[] c = new char[] { '1', '2', '3' };
        System.out.println(c[1]);
        System.out.println(+c[1]);

        /*Pattern pattern = Pattern.compile("\\{([^:}]+)(?::([^\\}]*))?\\}(.*?)\\{\\1\\}", Pattern.MULTILINE);
        //Pattern pattern = Pattern.compile("\\{([^:}]+)(?::([^\\}]*))?\\}", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher("{table:ggg}\nxxx\n{table}");
        System.out.println(matcher.groupCount());
        matcher.reset();
        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            matcher.appendReplacement(sb, "");
            System.out.println("*** "+matcher.group(1));
            System.out.println("*** "+matcher.group(2));
            System.out.println("*** "+matcher.group(3));
        }
        matcher.appendTail(sb);
        System.out.println("sb="+sb.toString());
        //System.out.println(matcher.group(3));*/


        

    }
}
