package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializableException;

import java.io.Serializable;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public interface UiLoggingManagementService extends RemoteService {

    public enum Level {
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    };

    void log(Level level, String message, SerializableException serialzableException);
    void log(Level level, String message);

    void debug(String message, SerializableException serializableException);
    void debug(String message);

    void info(String message, SerializableException serializableException);
    void info(String message);

    void warn(String message, SerializableException serializableException);
    void warn(String message);

    void error(String message, SerializableException serializableException);
    void error(String message);

    void fatal(String message, SerializableException serializableException);
    void fatal(String message);

}
