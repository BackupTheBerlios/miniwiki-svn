<%@ page import="foo.bar.site.interceptor.AccessInterceptor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/taglibs.jsp"%>

<% pageContext.setAttribute("ACCESS_SESSION_ID", AccessInterceptor.ACCESS_SESSION_ID) %>
<c:if test="${sessionScope[pageContext]}"></c:if>
    <input id="username" type="text" value="<fmt:message key="header.textfield.username" /> " />
    <input id="password" name="password" type="password" />
    <input id="loginButton" type="button" value="<fmt:message key="header.button.login"/>" />



