<%@include file="includes/taglibs.jsp"%>

<script type="text/javascript">
    $(document).ready(function() {
        $("#addUserForm").ajaxForm({
            target: $("#content"),
            beforeSubmit: function() {},
            success: function() {}
        });
    });
</script>


<%@include file="includes/displayError.jsp"%>
<form id="addUserForm" method="POST" action="<c:url value="/addUser.ajax"/>">
<table class="formTable">
    <tr>
        <td class="title"><fmt:message key="manageAddUser.form.username"/></td>
        <td><input type="text" name="username" value="<c:out value="${requestScope.command.username}"/>" /></td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="manageAddUser.form.password"/></td>
        <td><input type="password" name="password" value="<c:out value="${requestScope.command.password}"/>"/></td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="manageAddUser.form.confirmPassword"/></td>
        <td><input type="password" name="confirmPassword" value="<c:out value="${requestScope.command.confirmPassword}"/>"/></td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit" value="<fmt:message key="manageAddUser.form.submit"/>"/></td>
    </tr>
</table>
</form>
