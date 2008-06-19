package org.tmjee.miniwiki.server;

import org.tmjee.miniwiki.client.server.UiLoggingManagementService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 18, 2008
 * Time: 4:48:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class UiLoggingManagementServiceImpl extends RemoteServiceServlet implements UiLoggingManagementService  {

    private static final transient Log LOG = LogFactory.getLog(UiLoggingManagementServiceImpl.class);

    public void log(Level level, String message, Serializable serializableException) {
        if (Level.DEBUG.equals(level)) {
            debug(message, serializableException);
        }
        else if (Level.INFO.equals(level)) {
            info(message, serializableException);
        }
        else if (Level.WARN.equals(level)) {
            warn(message, serializableException);
        }
        else if (Level.ERROR.equals(level)) {
            error(message, serializableException);
        }
        else if (Level.FATAL.equals(level)) {
            fatal(message, serializableException);
        }
    }

    public void log(Level level, String message) {
        if (Level.DEBUG.equals(level)) {
            debug(message);
        }
        else if (Level.INFO.equals(level)) {
            info(message);
        }
        else if (Level.WARN.equals(level)) {
            warn(message);
        }
        else if (Level.ERROR.equals(level)) {
            error(message);
        }
        else if (Level.FATAL.equals(level)) {
            fatal(message);
        }
    }

    public void debug(final String message, final Serializable serializableException) {
        new ExceptionCatcher() {
            protected void _doIt() {
                LOG.debug(message, (Throwable) serializableException);
            }
        }.doIt();
    }

    public void debug(final String message) {
        new ExceptionCatcher() {
            protected void _doIt() {
                LOG.debug(message);
            }
        }.doIt();
    }

    public void info(final String message, final Serializable serializableException) {
        new ExceptionCatcher() {
            protected void _doIt() {
                LOG.info(message, (Throwable) serializableException);
            }
        }.doIt();
    }

    public void info(final String message) {
        new ExceptionCatcher() {
            protected void _doIt() {
                LOG.info(message);
            }
        }.doIt();
    }

    public void warn(final String message, final Serializable serializableException) {
        new ExceptionCatcher() {
            protected void _doIt() {
                LOG.warn(message, (Throwable)serializableException);
            }
        }.doIt();
    }

    public void warn(final String message) {
        new ExceptionCatcher() {
            protected void _doIt() {
                LOG.warn(message);
            }
        }.doIt();
    }

    public void error(final String message, final Serializable serializableException) {
        new ExceptionCatcher() {
            protected void _doIt() {
                LOG.error(message, (Throwable) serializableException);
            }
        }.doIt();
    }

    public void error(final String message) {
        new ExceptionCatcher() {
            protected void _doIt() {
                LOG.error(message);
            }
        }.doIt();
    }

    public void fatal(final String message, final Serializable serializableException) {
        new ExceptionCatcher(){
            protected void _doIt() {
                LOG.fatal(message, (Throwable)serializableException);
            }
        }.doIt();
    }

    public void fatal(final String message) {
        new ExceptionCatcher() {
            protected void _doIt() {
                LOG.fatal(message);
            }
        }.doIt();
    }


    abstract class ExceptionCatcher {
        public void doIt() {
            try {
                _doIt();
            }
            catch(Throwable t) {
                LOG.error("unable to log due to :-", t);
            }
        }
        protected abstract void _doIt();
    }
}
