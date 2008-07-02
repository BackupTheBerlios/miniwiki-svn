package org.tmjee.miniwiki.radeox.filter.mock;

import org.tmjee.miniwiki.radeox.radeox.api.engine.context.InitialRenderContext;
import org.tmjee.miniwiki.radeox.radeox.filter.Filter;
import org.tmjee.miniwiki.radeox.radeox.filter.context.FilterContext;

public class MockReplacesFilter implements Filter {
  public String filter(String input, FilterContext context) {
    return input;
  }

  public String[] replaces() {
    return new String[] { MockReplacedFilter.class.getName() };
  }

  public void setInitialContext(InitialRenderContext context) {
  }

  public String[] before() {
    return new String[0];
  }

  public String getDescription() {
    return "";
  }
}
