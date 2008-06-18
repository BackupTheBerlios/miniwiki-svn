package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 18, 2008
 * Time: 4:38:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UiLoggingServiceAsync {

    void log(UiLoggingService.Level level, String message, Serializable serialzableException, AsyncCallback callback);
    void log(UiLoggingService.Level level, String message, AsyncCallback callback);

    void debug(String message, Serializable serializableException, AsyncCallback callback);
    void debug(String message, AsyncCallback callback);

    void info(String message, Serializable serializableException, AsyncCallback callback);
    void info(String message, AsyncCallback callback);

    void warn(String message, Serializable serializableException, AsyncCallback callback);
    void warn(String message, AsyncCallback callback);

    void error(String message, Serializable serializableException, AsyncCallback callback);
    void error(String message, AsyncCallback callback);

    void fatal(String message, Serializable serializableException, AsyncCallback callback);
    void fatal(String message, AsyncCallback callback);
}
