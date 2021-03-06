package org.tmjee.miniwiki.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tmjee.miniwiki.client.handlers.Handler;
import org.tmjee.miniwiki.client.handlers.WikiHandler;
import org.tmjee.miniwiki.client.widgets.LoadingMessageDisplayWidget;
import org.tmjee.miniwiki.client.widgets.TemplateDisplayWidget;
import org.tmjee.miniwiki.client.server.TemplateInfo;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author tmjee
 * @version $Date$ $Id$
 */
public class MiniWiki implements EntryPoint {

  private static final String DEFAULT_HISTORY_TOKEN = "";
  private static final String WIKI_HISTORY_TOKEN_PREFIX = "WIKI_";
  private static final String SPACE_HISTORY_TOKEN_PREFIX = "SPACE_";
  private static final String PAGE_HISTORY_TOKEN_PREFIX = "PAGE_";


  private String currentWiki = "";
  private String currentSpace = "";
  private String currentPage = "";

  private Handler wikiHandler;


  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

      wikiHandler = new WikiHandler();

      RootPanel.get().add(LoadingMessageDisplayWidget.getInstance());


      HistoryListener historyListener = new MiniWikiHistoryListener();
      History.addHistoryListener(historyListener);

      if ((History.getToken() == null) ||
          (History.getToken().trim().length()<=0)) {
        historyListener.onHistoryChanged(DEFAULT_HISTORY_TOKEN);
      }
  }


  private class MiniWikiHistoryListener implements HistoryListener {
      public void onHistoryChanged(String historyToken) {

        LoadingMessageDisplayWidget.getInstance().display();

        if (DEFAULT_HISTORY_TOKEN.equals(historyToken)) {
            currentWiki = "";
            currentSpace = "";
            currentPage = "";

            wikiHandler.handle(currentWiki, currentSpace, currentPage, "", new HandlerAsyncCallback());
        }
        else if (historyToken.startsWith(WIKI_HISTORY_TOKEN_PREFIX)) {
            currentWiki = historyToken.substring(WIKI_HISTORY_TOKEN_PREFIX.length());
            currentSpace = "";
            currentPage = "";

            wikiHandler.handle(currentWiki, currentSpace, currentPage, "", new HandlerAsyncCallback());
        }
        else if (historyToken.startsWith(SPACE_HISTORY_TOKEN_PREFIX)) {
            currentSpace = historyToken.substring(SPACE_HISTORY_TOKEN_PREFIX.length());
            currentPage = "";

            wikiHandler.handle(currentWiki, currentSpace,  currentPage, "", new HandlerAsyncCallback());
        }
        else if (historyToken.startsWith(PAGE_HISTORY_TOKEN_PREFIX)) {
            currentPage = historyToken.substring(PAGE_HISTORY_TOKEN_PREFIX.length());
            if (currentSpace.trim().length() <= 0) {
                Window.alert("No space being selected, unable to go to page ["+currentPage+"] without knowing the space");
                return;
            }

            wikiHandler.handle(currentWiki, currentSpace, currentPage, "", new HandlerAsyncCallback());
        }
        else {  // it's a command
          if (currentWiki.trim().length() <= 0 ||
              currentSpace.trim().length() <= 0 ||
              currentPage.trim().length() <= 0 ) {
                Window.alert("No current wiki, space and page selected");
                return;
          }
          else {
              Window.alert("Unrecognized url / bookmark");
          }
        }
      }
  }


  private class HandlerAsyncCallback implements AsyncCallback {
      public void onFailure(Throwable caught) {
          LoadingMessageDisplayWidget.getInstance().done();
      }
      public void onSuccess(Object result) {

          TemplateInfo templateInfo = (TemplateInfo) result;
          TemplateDisplayWidget templateDisplayWidget = new TemplateDisplayWidget(templateInfo);

          RootPanel.get("content").clear();
          RootPanel.get("content").add(templateDisplayWidget);
          
          LoadingMessageDisplayWidget.getInstance().done();
      }
  }
}
