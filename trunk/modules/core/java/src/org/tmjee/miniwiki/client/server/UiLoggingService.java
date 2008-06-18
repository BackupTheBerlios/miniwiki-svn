package org.tmjee.miniwiki.client.server;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 18, 2008
 * Time: 4:37:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UiLoggingService {

    public enum Level {
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    };

    void log(Level level, String message, Serializable serialzableException);
    void log(Level level, String message);

    void debug(String message, Serializable serializableException);
    void debug(String message);

    void info(String message, Serializable serializableException);
    void info(String message);

    void warn(String message, Serializable serializableException);
    void warn(String message);

    void error(String message, Serializable serializableException);
    void error(String message);

    void fatal(String message, Serializable serializableException);
    void fatal(String message);

}
