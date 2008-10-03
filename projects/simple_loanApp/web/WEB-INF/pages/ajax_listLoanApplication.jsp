<%@include file="includes/taglibs.jsp"%>


<script type="text/javascript">
        $(document).ready(function() {
            $("#loanApplicationForm").ajaxForm({
                target: $("#content"),
                beforeSubmit: function(){},
                success: function(){}
            });
        });
    </script>


<%@include file="includes/displayError.jsp"%>

<form id="loanApplicationForm" action="<c:url value="/deleteLoanApplication.ajax"/>" method="POST">
<table class="dataTable">
    <tr class="header">
        <td><fmt:message key="manageLoanApplication.table.id"/></td>
        <td><fmt:message key="manageLoanApplication.table.surname"/></td>
        <td><fmt:message key="manageLoanApplication.table.firstname"/></td>
        <td><fmt:message key="manageLoanApplication.table.applicationDate"/></td>
        <td><fmt:message key="manageLoanApplication.table.referalHpNumber"/></td>
        <td>&nbsp;</td>
        <td><input type="submit" value="<fmt:message key="manageLoanApplication.table.delete"/>"/></td>
    </tr>
    <c:forEach var="loanApplication" varStatus="stat" items="${requestScope.command.loanApplications}">
        <tr>
            <td><c:out value="${loanApplication.id}"/></td>
            <td><c:out value="${loanApplication.surName}"/></td>
            <td><c:out value="${loanApplication.firstName}"/></td>
            <td><fmt:formatDate value="${loanApplication.applicationDate}" /></td>
            <td><c:out value="${loanApplication.referralHpNumber}"/></td>
            <td><input name="loanIds" type="checkbox" value="${loanApplication.id}" /></td>
            <td>
                <script type="text/javascript">
                    $(document).ready(function(){
                        $("#viewButton<c:out value='${stat.index}'/>").click(function(){
                            viewLoanDetails('<c:out value="${loanApplication.id}"/>');
                            //alert('<c:out value="${loanApplication.id}"/>');
                        });
                    });
                </script>
                <input id="viewButton<c:out value='${stat.index}'/>" type="button" value="<fmt:message key="manageLoanApplication.table.details"/>"/>
            </td>
        </tr>
    </c:forEach>
</table>
</form>
