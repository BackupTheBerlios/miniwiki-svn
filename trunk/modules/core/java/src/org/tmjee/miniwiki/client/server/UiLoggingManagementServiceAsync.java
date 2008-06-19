package org.tmjee.miniwiki.client.server;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.SerializableException;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 18, 2008
 * Time: 4:38:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UiLoggingManagementServiceAsync {

    void log(UiLoggingManagementService.Level level, String message, SerializableException serialzableException, AsyncCallback callback);
    void log(UiLoggingManagementService.Level level, String message, AsyncCallback callback);

    void debug(String message, SerializableException serializableException, AsyncCallback callback);
    void debug(String message, AsyncCallback callback);

    void info(String message, SerializableException serializableException, AsyncCallback callback);
    void info(String message, AsyncCallback callback);

    void warn(String message, SerializableException serializableException, AsyncCallback callback);
    void warn(String message, AsyncCallback callback);

    void error(String message, SerializableException serializableException, AsyncCallback callback);
    void error(String message, AsyncCallback callback);

    void fatal(String message, SerializableException serializableException, AsyncCallback callback);
    void fatal(String message, AsyncCallback callback);
}
