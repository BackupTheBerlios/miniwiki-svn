package org.tmjee.miniwiki.core.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class JpqlQueryConsole {

    private ApplicationContext applicationContext;
    private BufferedReader reader;

    private Configuration configuration = new Configuration();


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
            String commandLine = readCommandLine();
            while(!"END".equalsIgnoreCase(commandLine)) {
                try {
                    process(commandLine);
                    commandLine = readCommandLine();
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

    public void process(String command) {
        Result result = processCommand(command);
        printCommand(result);
    }

    protected String readCommandLine() throws IOException {
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

    protected Result processCommand(String command) {
        if (command.startsWith("c_")) {
            return processConfigurationCommand(command);
        }
        else {
            return processJpqlCommand(command);
        }
    }

    protected Result processConfigurationCommand(String command) {
        if (command.startsWith("c_configuration")) {
            return new CommandResult(configuration.print());
        }
        if (command.startsWith("c_firstPage=")) {
            int firstPage = -1;
            try {
                firstPage = Integer.parseInt(command.substring("c_firstPage=".length()));
                configuration.setFirstPage(firstPage);
                return new CommandResult("c_firstPage set to "+firstPage+" successfully");
            }
            catch(Exception e) {
                return new CommandResult("Should be in the form c_firstPage=<integer>");
            }
        }
        if (command.startsWith("c_maxPage=")) {
            int maxPage = -1;
            try {
                maxPage = Integer.parseInt(command.substring("c_maxPage=".length()));
                configuration.setMaxPage(maxPage);
                return new CommandResult("c_maxPage set to "+maxPage+" successfully");
            }
            catch(Exception e) {
                return new CommandResult("Should be in the form c_maxPage=<integer>");
            }
        }
        return new CommandResult("Unrecongnized command ["+command+"]");
    }

    protected Result processJpqlCommand(String jpql) {
        JpqlQueryConsoleSupportService service = (JpqlQueryConsoleSupportService) applicationContext.getBean("jpqlQueryConsoleSupportService");
        return service.executeJpaQuery(jpql);
    }

    protected void printCommand(Result commandObject) {
        System.out.println(commandObject.print());
    }

    

}
