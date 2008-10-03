package org.apache.jsp.WEB_002dINF.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import foo.bar.site.interceptor.AccessInterceptor;
import foo.bar.site.controller.DefaultFormController;
import org.springframework.validation.BindingResult;
import java.util.Iterator;
import org.springframework.validation.FieldError;
import foo.bar.site.controller.LoginFormControllerCommand;

public final class ajax_005flogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(10);
    _jspx_dependants.add("/WEB-INF/pages/includes/taglibs.jsp");
    _jspx_dependants.add("/WEB-INF/tlds/fmt.tld");
    _jspx_dependants.add("/WEB-INF/tlds/fmt-1_0-rt.tld");
    _jspx_dependants.add("/WEB-INF/tlds/c.tld");
    _jspx_dependants.add("/WEB-INF/tlds/c-1_0-rt.tld");
    _jspx_dependants.add("/WEB-INF/tlds/sql.tld");
    _jspx_dependants.add("/WEB-INF/tlds/sql-1_0-rt.tld");
    _jspx_dependants.add("/WEB-INF/tlds/x.tld");
    _jspx_dependants.add("/WEB-INF/tlds/x-1_0-rt.tld");
    _jspx_dependants.add("/WEB-INF/tlds/spring-form.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_002drt_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_002drt_005fwhen_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fparam_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_002drt_005fotherwise;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_002drt_005fif_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_002drt_005fforEach_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_002drt_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_002drt_005fwhen_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fparam_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_002drt_005fotherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_002drt_005fif_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_002drt_005fforEach_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_002drt_005fchoose.release();
    _005fjspx_005ftagPool_005fc_002drt_005fwhen_005ftest.release();
    _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody.release();
    _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey.release();
    _005fjspx_005ftagPool_005ffmt_005fparam_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.release();
    _005fjspx_005ftagPool_005fc_002drt_005fotherwise.release();
    _005fjspx_005ftagPool_005fc_002drt_005fif_005ftest.release();
    _005fjspx_005ftagPool_005fc_002drt_005fforEach_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write('\n');
      out.write('\n');
      //  c-rt:choose
      org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_002drt_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_002drt_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
      _jspx_th_c_002drt_005fchoose_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_002drt_005fchoose_005f0.setParent(null);
      int _jspx_eval_c_002drt_005fchoose_005f0 = _jspx_th_c_002drt_005fchoose_005f0.doStartTag();
      if (_jspx_eval_c_002drt_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("        ");
          out.write('\n');
          //  c-rt:when
          org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_002drt_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_002drt_005fwhen_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
          _jspx_th_c_002drt_005fwhen_005f0.setPageContext(_jspx_page_context);
          _jspx_th_c_002drt_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fchoose_005f0);
          // /WEB-INF/pages/ajax_login.jsp(11,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_002drt_005fwhen_005f0.setTest(session.getAttribute(AccessInterceptor.USER_SESSION_ID) != null);
          int _jspx_eval_c_002drt_005fwhen_005f0 = _jspx_th_c_002drt_005fwhen_005f0.doStartTag();
          if (_jspx_eval_c_002drt_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\n");
              out.write("    ");
              //  c-rt:set
              org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_002drt_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
              _jspx_th_c_002drt_005fset_005f0.setPageContext(_jspx_page_context);
              _jspx_th_c_002drt_005fset_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fwhen_005f0);
              // /WEB-INF/pages/ajax_login.jsp(12,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_002drt_005fset_005f0.setVar("accessSession");
              // /WEB-INF/pages/ajax_login.jsp(12,4) name = scope type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_002drt_005fset_005f0.setScope("request");
              // /WEB-INF/pages/ajax_login.jsp(12,4) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_002drt_005fset_005f0.setValue(session.getAttribute(AccessInterceptor.USER_SESSION_ID));
              int _jspx_eval_c_002drt_005fset_005f0 = _jspx_th_c_002drt_005fset_005f0.doStartTag();
              if (_jspx_th_c_002drt_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody.reuse(_jspx_th_c_002drt_005fset_005f0);
                return;
              }
              _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody.reuse(_jspx_th_c_002drt_005fset_005f0);
              out.write("\n");
              out.write("    ");
              if (_jspx_meth_fmt_005fmessage_005f0(_jspx_th_c_002drt_005fwhen_005f0, _jspx_page_context))
                return;
              out.write("\n");
              out.write("    [ <a href=\"");
              if (_jspx_meth_c_005furl_005f0(_jspx_th_c_002drt_005fwhen_005f0, _jspx_page_context))
                return;
              out.write('"');
              out.write('>');
              if (_jspx_meth_fmt_005fmessage_005f1(_jspx_th_c_002drt_005fwhen_005f0, _jspx_page_context))
                return;
              out.write("</a> ]\n");
              out.write("    [ <a href=\"");
              if (_jspx_meth_c_005furl_005f1(_jspx_th_c_002drt_005fwhen_005f0, _jspx_page_context))
                return;
              out.write('"');
              out.write('>');
              if (_jspx_meth_fmt_005fmessage_005f2(_jspx_th_c_002drt_005fwhen_005f0, _jspx_page_context))
                return;
              out.write("</a> ]\n");
              int evalDoAfterBody = _jspx_th_c_002drt_005fwhen_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_c_002drt_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fc_002drt_005fwhen_005ftest.reuse(_jspx_th_c_002drt_005fwhen_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fc_002drt_005fwhen_005ftest.reuse(_jspx_th_c_002drt_005fwhen_005f0);
          out.write("    \n");
          //  c-rt:otherwise
          org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_002drt_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_002drt_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
          _jspx_th_c_002drt_005fotherwise_005f0.setPageContext(_jspx_page_context);
          _jspx_th_c_002drt_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fchoose_005f0);
          int _jspx_eval_c_002drt_005fotherwise_005f0 = _jspx_th_c_002drt_005fotherwise_005f0.doStartTag();
          if (_jspx_eval_c_002drt_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("       ");
              out.write("\n");
              out.write("    <script type=\"text/javascript\">\n");
              out.write("    $(document).ready(\n");
              out.write("        function() {\n");
              out.write("            $(\"#loginButton\").click(function(){\n");
              out.write("                var username = $(\"#username\").val();\n");
              out.write("                var password = $(\"#password\").val();\n");
              out.write("                //alert(\"username=\"+username+\",password=\"+password);\n");
              out.write("                $.post(\n");
              out.write("                        \"");
              if (_jspx_meth_c_005furl_005f2(_jspx_th_c_002drt_005fotherwise_005f0, _jspx_page_context))
                return;
              out.write("\",\n");
              out.write("                        {username: $(\"#username\").val(), password:$(\"#password\").val()},\n");
              out.write("                        function(data){\n");
              out.write("                            $(\"#login\").html(data);\n");
              out.write("                        });\n");
              out.write("            });\n");
              out.write("        });\n");
              out.write("    </script>\n");
              out.write("    ");
              //  c-rt:if
              org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_002drt_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_002drt_005fif_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
              _jspx_th_c_002drt_005fif_005f0.setPageContext(_jspx_page_context);
              _jspx_th_c_002drt_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fotherwise_005f0);
              // /WEB-INF/pages/ajax_login.jsp(36,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_002drt_005fif_005f0.setTest(request.getAttribute(DefaultFormController.BINDING_RESULT_REQUEST_ID) != null);
              int _jspx_eval_c_002drt_005fif_005f0 = _jspx_th_c_002drt_005fif_005f0.doStartTag();
              if (_jspx_eval_c_002drt_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                do {
                  out.write("\n");
                  out.write("        ");
                  //  c-rt:set
                  org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_002drt_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
                  _jspx_th_c_002drt_005fset_005f1.setPageContext(_jspx_page_context);
                  _jspx_th_c_002drt_005fset_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fif_005f0);
                  // /WEB-INF/pages/ajax_login.jsp(37,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_c_002drt_005fset_005f1.setVar("bindingResult");
                  // /WEB-INF/pages/ajax_login.jsp(37,8) name = scope type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_c_002drt_005fset_005f1.setScope("request");
                  // /WEB-INF/pages/ajax_login.jsp(37,8) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_c_002drt_005fset_005f1.setValue(request.getAttribute(DefaultFormController.BINDING_RESULT_REQUEST_ID));
                  int _jspx_eval_c_002drt_005fset_005f1 = _jspx_th_c_002drt_005fset_005f1.doStartTag();
                  if (_jspx_th_c_002drt_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody.reuse(_jspx_th_c_002drt_005fset_005f1);
                    return;
                  }
                  _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody.reuse(_jspx_th_c_002drt_005fset_005f1);
                  out.write("\n");
                  out.write("        ");
                  //  c-rt:if
                  org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_002drt_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_002drt_005fif_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
                  _jspx_th_c_002drt_005fif_005f1.setPageContext(_jspx_page_context);
                  _jspx_th_c_002drt_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fif_005f0);
                  // /WEB-INF/pages/ajax_login.jsp(38,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_c_002drt_005fif_005f1.setTest(((BindingResult)request.getAttribute("bindingResult")).hasErrors());
                  int _jspx_eval_c_002drt_005fif_005f1 = _jspx_th_c_002drt_005fif_005f1.doStartTag();
                  if (_jspx_eval_c_002drt_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    do {
                      out.write("\n");
                      out.write("            <ul>\n");
                      out.write("                ");
                      //  c-rt:forEach
                      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_002drt_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_002drt_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
                      _jspx_th_c_002drt_005fforEach_005f0.setPageContext(_jspx_page_context);
                      _jspx_th_c_002drt_005fforEach_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fif_005f1);
                      // /WEB-INF/pages/ajax_login.jsp(40,16) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                      _jspx_th_c_002drt_005fforEach_005f0.setVar("errorCode");
                      // /WEB-INF/pages/ajax_login.jsp(40,16) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                      _jspx_th_c_002drt_005fforEach_005f0.setItems(((BindingResult)request.getAttribute("bindingResult")).getAllErrors());
                      int[] _jspx_push_body_count_c_002drt_005fforEach_005f0 = new int[] { 0 };
                      try {
                        int _jspx_eval_c_002drt_005fforEach_005f0 = _jspx_th_c_002drt_005fforEach_005f0.doStartTag();
                        if (_jspx_eval_c_002drt_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          do {
                            out.write("\n");
                            out.write("                    <li>");
                            if (_jspx_meth_fmt_005fmessage_005f3(_jspx_th_c_002drt_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_002drt_005fforEach_005f0))
                              return;
                            out.write("</li>\n");
                            out.write("                ");
                            int evalDoAfterBody = _jspx_th_c_002drt_005fforEach_005f0.doAfterBody();
                            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                          } while (true);
                        }
                        if (_jspx_th_c_002drt_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          return;
                        }
                      } catch (Throwable _jspx_exception) {
                        while (_jspx_push_body_count_c_002drt_005fforEach_005f0[0]-- > 0)
                          out = _jspx_page_context.popBody();
                        _jspx_th_c_002drt_005fforEach_005f0.doCatch(_jspx_exception);
                      } finally {
                        _jspx_th_c_002drt_005fforEach_005f0.doFinally();
                        _005fjspx_005ftagPool_005fc_002drt_005fforEach_005fvar_005fitems.reuse(_jspx_th_c_002drt_005fforEach_005f0);
                      }
                      out.write("\n");
                      out.write("            </ul>\n");
                      out.write("        ");
                      int evalDoAfterBody = _jspx_th_c_002drt_005fif_005f1.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                  }
                  if (_jspx_th_c_002drt_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _005fjspx_005ftagPool_005fc_002drt_005fif_005ftest.reuse(_jspx_th_c_002drt_005fif_005f1);
                    return;
                  }
                  _005fjspx_005ftagPool_005fc_002drt_005fif_005ftest.reuse(_jspx_th_c_002drt_005fif_005f1);
                  out.write("\n");
                  out.write("    ");
                  int evalDoAfterBody = _jspx_th_c_002drt_005fif_005f0.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
              }
              if (_jspx_th_c_002drt_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fc_002drt_005fif_005ftest.reuse(_jspx_th_c_002drt_005fif_005f0);
                return;
              }
              _005fjspx_005ftagPool_005fc_002drt_005fif_005ftest.reuse(_jspx_th_c_002drt_005fif_005f0);
              out.write("\n");
              out.write("    \n");
              out.write("    ");
              out.write("\n");
              out.write("    ");
              //  c-rt:choose
              org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_002drt_005fchoose_005f1 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_002drt_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
              _jspx_th_c_002drt_005fchoose_005f1.setPageContext(_jspx_page_context);
              _jspx_th_c_002drt_005fchoose_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fotherwise_005f0);
              int _jspx_eval_c_002drt_005fchoose_005f1 = _jspx_th_c_002drt_005fchoose_005f1.doStartTag();
              if (_jspx_eval_c_002drt_005fchoose_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                do {
                  out.write("\n");
                  out.write("        ");
                  //  c-rt:when
                  org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_002drt_005fwhen_005f1 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_002drt_005fwhen_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
                  _jspx_th_c_002drt_005fwhen_005f1.setPageContext(_jspx_page_context);
                  _jspx_th_c_002drt_005fwhen_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fchoose_005f1);
                  // /WEB-INF/pages/ajax_login.jsp(49,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_c_002drt_005fwhen_005f1.setTest(((request.getAttribute(DefaultFormController.DEFAULT_COMMAND_NAME) != null) && (request.getAttribute(DefaultFormController.DEFAULT_COMMAND_NAME) instanceof LoginFormControllerCommand)));
                  int _jspx_eval_c_002drt_005fwhen_005f1 = _jspx_th_c_002drt_005fwhen_005f1.doStartTag();
                  if (_jspx_eval_c_002drt_005fwhen_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    do {
                      out.write("\n");
                      out.write("            ");
                      //  c-rt:set
                      org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_002drt_005fset_005f2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
                      _jspx_th_c_002drt_005fset_005f2.setPageContext(_jspx_page_context);
                      _jspx_th_c_002drt_005fset_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fwhen_005f1);
                      // /WEB-INF/pages/ajax_login.jsp(50,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                      _jspx_th_c_002drt_005fset_005f2.setVar("commandObject");
                      // /WEB-INF/pages/ajax_login.jsp(50,12) name = scope type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                      _jspx_th_c_002drt_005fset_005f2.setScope("request");
                      // /WEB-INF/pages/ajax_login.jsp(50,12) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                      _jspx_th_c_002drt_005fset_005f2.setValue(request.getAttribute(DefaultFormController.DEFAULT_COMMAND_NAME));
                      int _jspx_eval_c_002drt_005fset_005f2 = _jspx_th_c_002drt_005fset_005f2.doStartTag();
                      if (_jspx_th_c_002drt_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody.reuse(_jspx_th_c_002drt_005fset_005f2);
                        return;
                      }
                      _005fjspx_005ftagPool_005fc_002drt_005fset_005fvar_005fvalue_005fscope_005fnobody.reuse(_jspx_th_c_002drt_005fset_005f2);
                      out.write("\n");
                      out.write("            <input id=\"username\" type=\"text\" value=\"");
                      if (_jspx_meth_c_005fout_005f0(_jspx_th_c_002drt_005fwhen_005f1, _jspx_page_context))
                        return;
                      out.write("\" />\n");
                      out.write("            <input id=\"password\" name=\"password\" type=\"password\" value=\"");
                      if (_jspx_meth_c_005fout_005f1(_jspx_th_c_002drt_005fwhen_005f1, _jspx_page_context))
                        return;
                      out.write("\" />\n");
                      out.write("        ");
                      int evalDoAfterBody = _jspx_th_c_002drt_005fwhen_005f1.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                  }
                  if (_jspx_th_c_002drt_005fwhen_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _005fjspx_005ftagPool_005fc_002drt_005fwhen_005ftest.reuse(_jspx_th_c_002drt_005fwhen_005f1);
                    return;
                  }
                  _005fjspx_005ftagPool_005fc_002drt_005fwhen_005ftest.reuse(_jspx_th_c_002drt_005fwhen_005f1);
                  out.write("\n");
                  out.write("        ");
                  if (_jspx_meth_c_002drt_005fotherwise_005f1(_jspx_th_c_002drt_005fchoose_005f1, _jspx_page_context))
                    return;
                  out.write("\n");
                  out.write("    ");
                  int evalDoAfterBody = _jspx_th_c_002drt_005fchoose_005f1.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
              }
              if (_jspx_th_c_002drt_005fchoose_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fc_002drt_005fchoose.reuse(_jspx_th_c_002drt_005fchoose_005f1);
                return;
              }
              _005fjspx_005ftagPool_005fc_002drt_005fchoose.reuse(_jspx_th_c_002drt_005fchoose_005f1);
              out.write("\n");
              out.write("    <input id=\"loginButton\" type=\"button\" value=\"");
              if (_jspx_meth_fmt_005fmessage_005f5(_jspx_th_c_002drt_005fotherwise_005f0, _jspx_page_context))
                return;
              out.write("\" />\n");
              int evalDoAfterBody = _jspx_th_c_002drt_005fotherwise_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_c_002drt_005fotherwise_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fc_002drt_005fotherwise.reuse(_jspx_th_c_002drt_005fotherwise_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fc_002drt_005fotherwise.reuse(_jspx_th_c_002drt_005fotherwise_005f0);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_c_002drt_005fchoose_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_002drt_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_002drt_005fchoose.reuse(_jspx_th_c_002drt_005fchoose_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fc_002drt_005fchoose.reuse(_jspx_th_c_002drt_005fchoose_005f0);
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_fmt_005fmessage_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fwhen_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fwhen_005f0);
    // /WEB-INF/pages/ajax_login.jsp(13,4) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f0.setKey("header.welcome");
    int _jspx_eval_fmt_005fmessage_005f0 = _jspx_th_fmt_005fmessage_005f0.doStartTag();
    if (_jspx_eval_fmt_005fmessage_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_fmt_005fmessage_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_fmt_005fmessage_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_fmt_005fmessage_005f0.doInitBody();
      }
      do {
        out.write("\n");
        out.write("        ");
        if (_jspx_meth_fmt_005fparam_005f0(_jspx_th_fmt_005fmessage_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_fmt_005fmessage_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_fmt_005fmessage_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_fmt_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey.reuse(_jspx_th_fmt_005fmessage_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey.reuse(_jspx_th_fmt_005fmessage_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fparam_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_fmt_005fmessage_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:param
    org.apache.taglibs.standard.tag.rt.fmt.ParamTag _jspx_th_fmt_005fparam_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.ParamTag) _005fjspx_005ftagPool_005ffmt_005fparam_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.ParamTag.class);
    _jspx_th_fmt_005fparam_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fparam_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_fmt_005fmessage_005f0);
    // /WEB-INF/pages/ajax_login.jsp(14,8) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fparam_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['accessSession'].username}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_fmt_005fparam_005f0 = _jspx_th_fmt_005fparam_005f0.doStartTag();
    if (_jspx_th_fmt_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fparam_005fvalue_005fnobody.reuse(_jspx_th_fmt_005fparam_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fparam_005fvalue_005fnobody.reuse(_jspx_th_fmt_005fparam_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fwhen_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fwhen_005f0);
    // /WEB-INF/pages/ajax_login.jsp(16,15) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("/manageUser.view");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fwhen_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f1 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f1.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fwhen_005f0);
    // /WEB-INF/pages/ajax_login.jsp(16,50) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f1.setKey("header.administration");
    int _jspx_eval_fmt_005fmessage_005f1 = _jspx_th_fmt_005fmessage_005f1.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fwhen_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f1 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fwhen_005f0);
    // /WEB-INF/pages/ajax_login.jsp(17,15) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setValue("/logout.view");
    int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
    if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fwhen_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f2 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f2.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fwhen_005f0);
    // /WEB-INF/pages/ajax_login.jsp(17,46) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f2.setKey("header.logout");
    int _jspx_eval_fmt_005fmessage_005f2 = _jspx_th_fmt_005fmessage_005f2.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fotherwise_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f2 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fotherwise_005f0);
    // /WEB-INF/pages/ajax_login.jsp(28,25) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setValue("login.ajax");
    int _jspx_eval_c_005furl_005f2 = _jspx_th_c_005furl_005f2.doStartTag();
    if (_jspx_th_c_005furl_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_002drt_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f3 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f3.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fforEach_005f0);
    // /WEB-INF/pages/ajax_login.jsp(41,24) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f3.setKey((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${errorCode.code}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_fmt_005fmessage_005f3 = _jspx_th_fmt_005fmessage_005f3.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fwhen_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fwhen_005f1);
    // /WEB-INF/pages/ajax_login.jsp(51,52) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${commandObject.username}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fwhen_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fwhen_005f1);
    // /WEB-INF/pages/ajax_login.jsp(52,72) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${commandObject.password}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }

  private boolean _jspx_meth_c_002drt_005fotherwise_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fchoose_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c-rt:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_002drt_005fotherwise_005f1 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_002drt_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_002drt_005fotherwise_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_002drt_005fotherwise_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fchoose_005f1);
    int _jspx_eval_c_002drt_005fotherwise_005f1 = _jspx_th_c_002drt_005fotherwise_005f1.doStartTag();
    if (_jspx_eval_c_002drt_005fotherwise_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("            <input id=\"username\" type=\"text\" value=\"");
        if (_jspx_meth_fmt_005fmessage_005f4(_jspx_th_c_002drt_005fotherwise_005f1, _jspx_page_context))
          return true;
        out.write("\" />\n");
        out.write("            <input id=\"password\" name=\"password\" type=\"password\" />\n");
        out.write("        ");
        int evalDoAfterBody = _jspx_th_c_002drt_005fotherwise_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_002drt_005fotherwise_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_002drt_005fotherwise.reuse(_jspx_th_c_002drt_005fotherwise_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_002drt_005fotherwise.reuse(_jspx_th_c_002drt_005fotherwise_005f1);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fotherwise_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f4 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f4.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fotherwise_005f1);
    // /WEB-INF/pages/ajax_login.jsp(55,52) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f4.setKey("header.textfield.username");
    int _jspx_eval_fmt_005fmessage_005f4 = _jspx_th_fmt_005fmessage_005f4.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f4);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_002drt_005fotherwise_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f5 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f5.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_002drt_005fotherwise_005f0);
    // /WEB-INF/pages/ajax_login.jsp(59,49) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f5.setKey("header.button.login");
    int _jspx_eval_fmt_005fmessage_005f5 = _jspx_th_fmt_005fmessage_005f5.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f5);
    return false;
  }
}
