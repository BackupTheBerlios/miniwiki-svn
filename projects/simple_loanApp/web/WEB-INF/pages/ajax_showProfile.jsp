<%@include file="includes/taglibs.jsp"%>


<script type="text/javascript">
        $(document).ready(function() {
            $("#updateProfileForm").ajaxForm({
                target: $("#content"),
                beforeSubmit: function(){},
                success: function(){}
            });
        });
    </script>


<%@include file="includes/displayError.jsp"%>
<form id="updateProfileForm" method="POST" action="<c:url value="/updateProfile.ajax"/>">
    <table class="formTable">
        <tr>
            <td class="title"><fmt:message key="manageProfile.form.id"/></td>
            <td><c:out value="${requestScope.command.id}"/>
            <input name="id" type="hidden" value="${requestScope.command.id}"/> </td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="manageProfile.form.username"/></td>
            <td><c:out value="${requestScope.command.username}"/>
            <input name="username" type="hidden" value="${requestScope.command.username}"/></td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="manageProfile.form.password"/></td>
            <td><input name="password" type="password" value="<c:out value="${requestScope.command.password}"/>"/></td>
        </tr>
        <tr>
            <td class="title"><fmt:message key="manageProfile.form.confirmPassword"/></td>
            <td><input name="confirmPassword" type="password" value="<c:out value="${requestScope.command.confirmPassword}"/>"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="<fmt:message key="manageProfile.form.submit"/>"/></td>
        </tr>
    </table>
</form>
