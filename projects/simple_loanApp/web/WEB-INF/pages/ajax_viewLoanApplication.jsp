<%@include file="includes/taglibs.jsp"%>
<%@include file="includes/displayError.jsp"%>



<table>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.id"/></td>
        <td><c:out value="${requestScope.command.loanApplication.id}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.surName"/></td>
        <td><c:out value="${requestScope.command.loanApplication.surName}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.firstName"/></td>
        <td><c:out value="${requestScope.command.loanApplication.firstName}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.age"/></td>
        <td><c:out value="${requestScope.command.loanApplication.age}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.address"/></td>
        <td><c:out value="${requestScope.command.loanApplication.address}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.icNumber"/></td>
        <td><c:out value="${requestScope.command.loanApplication.icNumber}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.email"/></td>
        <td><c:out value="${requestScope.command.loanApplication.email}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.telephoneNumber"/></td>
        <td><c:out value="${requestScope.command.loanApplication.telephoneNumber}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.handphoneNumber"/> </td>
        <td><c:out value="${requestScope.command.loanApplication.handphoneNumber}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.anualIncome"/></td>
        <td><c:out value="${requestScope.command.loanApplication.anualIncome}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.loanAmount"/></td>
        <td><c:out value="${requestScope.command.loanApplication.loanAmount}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.referralHpNumber"/></td>
        <td><c:out value="${requestScope.command.loanApplication.referralHpNumber}"/></td>
    </tr>
    <tr>
        <td><fmt:message key="manageLoanApplication.view.applicationDate"/> </td>
        <td><fmt:formatDate value="${rquestScope.command.loanApplication.applicationDate}"/></td>
    </tr>
</table>