<%@ page import="foo.bar.site.interceptor.AccessInterceptor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/taglibs.jsp"%>




<% if (session.getAttribute(AccessInterceptor.ACCESS_SESSION_ID) != null) {
    request.setAttribute("accessSession", session.getAttribute(AccessInterceptor.ACCESS_SESSION_ID)); %>
    <fmt:message key="header.welcome">
        <fmt:param value="${requestScope['accessSession'].username}" />
    </fmt:message>
<% } else { %>
    <script type="text/javascript">
    $(document).ready(
        function() {
            $("#loginButton").click(function(){
                var p = $("#username").val();
                alert('var='+p);
                <%--$.post(
                        "<c:url value='login.ajax'/>",
                        {username: $("#username").text(), password:$("#password").text()},
                        function(data){
                            $("#login").html(data);
                        });--%>
                //alert('ok');
                <%--$.get("<c:url value="/time.jsp"/>", function(data) {
                    alert(data);
                });--%>
            });
        });
    </script>

    <input id="username" type="text" value="<fmt:message key="header.textfield.username" /> " />
    <input id="password" name="password" type="password" />
    <input id="loginButton" type="button" value="<fmt:message key="header.button.login"/>" />
<% } %>




