<%@ page import="foo.bar.site.interceptor.AccessInterceptor" %>
<%@ page import="foo.bar.site.controller.DefaultFormController" %>
<%@ page import="org.springframework.validation.BindingResult" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.springframework.validation.FieldError" %>
<%@ page import="foo.bar.site.controller.LoginFormControllerCommand" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/taglibs.jsp"%>

<c-rt:choose>        <%-- User has logged in --%>
<c-rt:when test="<%=session.getAttribute(AccessInterceptor.USER_SESSION_ID) != null%>">
    <c-rt:set var="accessSession" scope="request" value="<%=session.getAttribute(AccessInterceptor.USER_SESSION_ID)%>" />
    <fmt:message key="header.welcome">
        <fmt:param value="${requestScope['accessSession'].username}" />
    </fmt:message>
    [ <a href="<c:url value='/manageUser.view'/>"><fmt:message key="header.administration" /></a> ]
    [ <a href="<c:url value='/logout.view'/>"><fmt:message key="header.logout"/></a> ]
</c-rt:when>    
<c-rt:otherwise>       <%-- User has not logged in --%>
    <script type="text/javascript">
    $(document).ready(
        function() {
            $("#loginButton").click(function(){
                var username = $("#username").val();
                var password = $("#password").val();
                //alert("username="+username+",password="+password);
                $.post(
                        "<c:url value='login.ajax'/>",
                        {username: $("#username").val(), password:$("#password").val()},
                        function(data){
                            $("#login").html(data);
                        });
            });
        });
    </script>
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
    
    <%-- login failed, we want to retain the username and password --%>
    <c-rt:choose>
        <c-rt:when test="<%=((request.getAttribute(DefaultFormController.DEFAULT_COMMAND_NAME) != null) && (request.getAttribute(DefaultFormController.DEFAULT_COMMAND_NAME) instanceof LoginFormControllerCommand))%>">
            <c-rt:set var="commandObject" scope="request" value="<%=request.getAttribute(DefaultFormController.DEFAULT_COMMAND_NAME)%>" />
            <input id="username" type="text" value="<c:out value='${commandObject.username}' />" />
            <input id="password" name="password" type="password" value="<c:out value='${commandObject.password}'/>" />
        </c-rt:when>
        <c-rt:otherwise>
            <input id="username" type="text" value="<fmt:message key="header.textfield.username" />" />
            <input id="password" name="password" type="password" />
        </c-rt:otherwise>
    </c-rt:choose>
    <input id="loginButton" type="button" value="<fmt:message key="header.button.login"/>" />
</c-rt:otherwise>
</c-rt:choose>



