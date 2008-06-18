package org.tmjee.miniwiki.server;

import org.tmjee.miniwiki.client.server.UiLoggingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 18, 2008
 * Time: 4:48:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class UiLoggingServiceImpl implements UiLoggingService {

    private static final Log LOG = LogFactory.getLog(UiLoggingServiceImpl.class);

    public void log(Level level, String message, Serializable serialzableException) {
        if (Level.DEBUG.equals(level)) {

        }
        else if (Level.INFO.equals(level)) {
            
        }
    }

    public void log(Level level, String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void debug(String message, Serializable serializableException) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void debug(String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void info(String message, Serializable serializableException) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void info(String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void warn(String message, Serializable serializableException) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void warn(String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void error(String message, Serializable serializableException) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void error(String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void fatal(String message, Serializable serializableException) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void fatal(String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
