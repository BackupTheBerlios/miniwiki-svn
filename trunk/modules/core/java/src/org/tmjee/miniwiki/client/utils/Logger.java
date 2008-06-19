package org.tmjee.miniwiki.client.utils;

import org.tmjee.miniwiki.client.server.UiLoggingManagementService;
import org.tmjee.miniwiki.client.service.Service;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.SerializableException;
import com.google.gwt.user.client.Window;
import com.google.gwt.core.client.GWT;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 19, 2008
 * Time: 3:28:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Logger {

    public static void log(UiLoggingManagementService.Level level, String message, Throwable serialzableException) {
        //Service.getLoggingService().log(level, message, convert(serialzableException), new NullAsyncCallback());
        GWT.log(message, serialzableException);
    }

    public static void log(UiLoggingManagementService.Level level, String message) {
        //Service.getLoggingService().log(level, message, new NullAsyncCallback());
        GWT.log(message, null);
    }

    public static void debug(String message, Throwable serializableException) {
        //Service.getLoggingService().debug(message, convert(serializableException), new NullAsyncCallback());
        GWT.log(message, serializableException);
    }

    public static void debug(String message) {
        //Service.getLoggingService().debug(message, new NullAsyncCallback());
        GWT.log(message, null);
    }

    public static void info(String message, Throwable serializableException) {
        //Service.getLoggingService().info(message, convert(serializableException), new NullAsyncCallback());
        GWT.log(message, serializableException);
    }

    public static void info(String message) {
        //Service.getLoggingService().info(message, new NullAsyncCallback());
        GWT.log(message, null);
    }

    public static void warn(String message, Throwable serializableException) {
        //Service.getLoggingService().warn(message, convert(serializableException), new NullAsyncCallback());
        GWT.log(message, serializableException);
    }

    public static void warn(String message) {
        //Service.getLoggingService().warn(message, new NullAsyncCallback());
        GWT.log(message, null);
    }

    public static void error(String message, Throwable serializableException) {
        //Service.getLoggingService().error(message, convert(serializableException), new NullAsyncCallback());
        GWT.log(message, serializableException);
    }

    public static void error(String message) {
        //Service.getLoggingService().error(message, new NullAsyncCallback());
        GWT.log(message, null);
    }

    public static void fatal(String message, Throwable serializableException) {
        //Service.getLoggingService().fatal(message, convert(serializableException), new NullAsyncCallback());
        GWT.log(message, serializableException);
    }

    public static void fatal(String message) {
        //Service.getLoggingService().fatal(message, new NullAsyncCallback());
        GWT.log(message, null);
    }


    protected static SerializableException convert(Throwable t) {
        if (!(t instanceof SerializableException)) {
            return new SerializableException(t.getMessage());
        }
        return new SerializableException(t.toString());
    }


    private static class NullAsyncCallback implements AsyncCallback {
        public void onFailure(Throwable throwable) {
            GWT.log(throwable.toString(), throwable);
            Window.alert("Unable to log to server side \n"+throwable.toString());
        }
        public void onSuccess(Object o) {

        }
    }
}
