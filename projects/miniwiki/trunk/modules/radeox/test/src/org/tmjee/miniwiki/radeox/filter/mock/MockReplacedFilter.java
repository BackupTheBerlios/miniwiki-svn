package org.tmjee.miniwiki.radeox.filter.mock;

import org.tmjee.miniwiki.radeox.api.engine.context.InitialRenderContext;
import org.tmjee.miniwiki.radeox.filter.Filter;
import org.tmjee.miniwiki.radeox.filter.context.FilterContext;

public class MockReplacedFilter implements Filter {
  public String filter(String input, FilterContext context) {
    return input;
  }

  public void setInitialContext(InitialRenderContext context) {
  }

  public String[] replaces() {
    return new String[0];
  }

  public String[] before() {
    return new String[0];
  }

  public String getDescription() {
    return "";
  }
}
