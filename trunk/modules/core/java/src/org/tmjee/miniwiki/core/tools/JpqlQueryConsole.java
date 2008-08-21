package org.tmjee.miniwiki.core.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class JpqlQueryConsole {

    private ApplicationContext applicationContext;
    private BufferedReader reader;


    public static void main(String[] args) throws Exception {
        new JpqlQueryConsole().start();
    }


    public JpqlQueryConsole() throws IOException {
        applicationContext = new ClassPathXmlApplicationContext(
                new String[] {
                        "jpql_console_support.xml"
                });
        reader = new BufferedReader(new InputStreamReader(System.in));
        start();
    }

    protected void start() throws IOException {
        try {
            String jpql = readJpql();
            while(!"END".equalsIgnoreCase(jpql)) {
                try {
                    process(jpql);
                    jpql = readJpql();
                }
                catch(DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            if (reader != null) {
                try { reader.close(); } catch(IOException e) { }
            }
            if ((applicationContext != null) && (applicationContext instanceof ConfigurableApplicationContext)) {
                ((ConfigurableApplicationContext)applicationContext).close();
            }
        }
    }

    public void process(String jpql) {
        Object result = processJpql(jpql);
        printJpqlResult(result);
    }

    protected String readJpql() throws IOException {
        System.out.print("\nJPQL>");
        StringBuffer jpql = new StringBuffer();
        String _jpql = null;
        while((_jpql = reader.readLine()) != null) {
            if(!(_jpql.trim().endsWith(";"))) {
                jpql.append(_jpql.trim());
                System.out.print("\nJPQL>");
            }
            else {
                jpql.append(_jpql.trim().substring(0, (_jpql.trim().length()-1)));
                System.out.print("\n");
                break;
            }
        }
        //System.out.println("******** ["+jpql.toString()+"]");
        return jpql.toString();
    }

    protected Object processJpql(String jpql) {
        JpqlQueryConsoleSupportService service = (JpqlQueryConsoleSupportService) applicationContext.getBean("jpqlQueryConsoleSupportService");
        return service.executeJpaQuery(jpql);
    }

    protected void printJpqlResult(Object jpqlResult) {
        StringBuffer s = new StringBuffer();
        printJpqlResult(jpqlResult, s);
        System.out.println(s.toString());
    }

    protected void printJpqlResult(Object jpqlResult, StringBuffer result) {
        if (jpqlResult instanceof Collection) {
            int count = 1;
            for (Object o : ((Collection)jpqlResult)) {
                result.append(count+"]");
                printJpqlResult(o, result);
                count++;
            }
        }
        else if (jpqlResult.getClass().isArray()) {
            int count = 1;
            for (Object o : (Object[])jpqlResult) {
                result.append(count+"]");
                printJpqlResult(o, result);
                count++;
            }
        }
        else {
            result.append(ReflectionToStringBuilder.toString(jpqlResult));
            result.append("\n");
        }
    }

}
