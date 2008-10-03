<%@ page import="foo.bar.site.interceptor.AccessInterceptor" %>
<%@ page import="foo.bar.site.controller.DefaultFormController" %>
<%@ page import="org.springframework.validation.BindingResult" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.springframework.validation.FieldError" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglibs.jsp"%>

    <c-rt:if test="<%=request.getAttribute(DefaultFormController.BINDING_RESULT_REQUEST_ID) != null%>">
        <c-rt:set var="bindingResult" scope="request" value="<%=request.getAttribute(DefaultFormController.BINDING_RESULT_REQUEST_ID)%>"/>
        <c-rt:if test="<%=((BindingResult)request.getAttribute("bindingResult")).hasErrors()%>">
            <ul>
                <c-rt:forEach var="errorCode" items="<%=((BindingResult)request.getAttribute("bindingResult")).getAllErrors()%>">
                    <li><fmt:message key="${errorCode.code}" /></li>
                </c-rt:forEach>
            </ul>
        </c-rt:if>
    </c-rt:if>