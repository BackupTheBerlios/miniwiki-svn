<%@ page import="foo.bar.site.controller.DefaultFormController" %>
<%@ page import="org.springframework.validation.BindingResult" %>
<%@include file="includes/taglibs.jsp"%>
    

<script type="text/javascript">
        $(document).ready(function(){
            $("#listUserForm").ajaxForm(
                {
                    target: '#content',
                    beforeSubmit: function() {},
                    success: function() {}
                });
        });
    </script>

       <%@include file="/WEB-INF/pages/includes/displayError.jsp"%>   

       <form id="listUserForm" action="<c:url value="/deleteUser.ajax"/>" method="POST">
       <table class="dataTable">
       <tr class="header">
           <td><fmt:message key="manageUser.table.userId"/></td>
           <td><fmt:message key="manageUser.table.username"/></td>
           <td><input type="submit" value="<fmt:message key="manageUser.table.delete"/>"/></td>
       </tr>
       <c:forEach var="user" varStatus="stat" items="${requestScope.command.users}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.username}"/></td>
                <td><input type="checkbox" name="userIds" value="${user.id}"/></td>
            </tr>
       </c:forEach>
       </table>
       </form>
