<%@include file="includes/taglibs.jsp"%>


<script type="text/javascript">
    $(document).ready(function() {
        $("#applyLoanForm").ajaxForm({
            target: $("#content"),
            beforeSubmit: function() {},
            success: function() {}
        });
    });
</script>

<%@include file="includes/displayError.jsp"%>
<form id="applyLoanForm" action="<c:url value="/submitLoan.ajax"/>" method="POST">
    <table class="formTable">
    <tr>
        <td class="title"><fmt:message key="applyLoanApplication.form.surName"/></td>
        <td>
            <input type="text" name="surName"
            <c:if test="${requestScope.command.surName != null}">
                value="<c:out value="${requestScope.command.surName}"/>"
            </c:if>
            />
        </td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="applyLoanApplication.form.firstName"/></td>
        <td>
            <input type="text" name="firstName"
            <c:if test="${requestScope.command.firstName != null}">
                value="<c:out value="${requestScope.command.firstName}"/>"
            </c:if>
            />
        </td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="applyLoanApplication.form.age" /></td>
        <td>
            <input type="text" name="age"
            <c:if test="${requestScope.command.age != null}">
                value="<c:out value="${requestScope.command.age}"/>"
            </c:if>
            />
        </td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="applyLoanApplication.form.address"/></td>
        <td>
            <input type="text" name="address"
            <c:if test="${requestScope.command.address != null}">
                value="<c:out value="${requestScope.command.address}"/>"
            </c:if>
            />
        </td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="applyLoanApplication.form.icNumber"/></td>
        <td>
            <input type="text" name="icNumber"
            <c:if test="${requestScope.command.icNumber != null}">
                value="<c:out value="${requestScope.command.icNumber}"/>"
            </c:if>
            />
        </td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="applyLoanApplication.form.email"/></td>
        <td>
            <input type="text" name="email"
            <c:if test="${requestScope.command.email != null}">
                value="<c:out value="${requestScope.command.email}"/>"
            </c:if>
            />
        </td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="applyLoanApplication.form.telephoneNumber"/></td>
        <td>
            <input type="text" name="telephoneNumber"
            <c:if test="${requestScope.command.telephoneNumber != null}">
                value="<c:out value="${requestScope.command.telephoneNumber}"/>"
            </c:if>
            />
        </td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="applyLoanApplication.form.handphoneNumber"/> </td>
        <td>
            <input type="text" name="handphoneNumber"
            <c:if test="${requestScope.command.handphoneNumber != null}">
                value="<c:out value="${requestScope.command.handphoneNumber}"/>"
            </c:if>
            />
        </td>
    </tr>
    <tr>
       <td class="title"><fmt:message key="applyLoanApplication.form.anualIncome"/></td>
        <td>
            <input type="text" name="anualIncome"
            <c:if test="${requestScope.command.anualIncome != null}">
                value="<c:out value="${requestScope.command.anualIncome}"/>"
            </c:if>
            />
        </td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="applyLoanApplication.form.loanAmount"/></td>
        <td>
            <input type="text" name="loanAmount"
            <c:if test="${requestScope.command.loanAmount != null}">
                value="<c:out value="${requestScope.command.loanAmount}"/>"
            </c:if>
            />
        </td>
    </tr>
    <tr>
        <td class="title"><fmt:message key="applyLoanApplication.form.referralHpNumber" /></td>
        <td>
            <input type="text" name="referralHpNumber"
            <c:if test="${requestScope.command.referralHpNumber != null}">
                value="<c:out value="${requestScope.command.referralHpNumber}"/>"
            </c:if>
            />
        </td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit" value="<fmt:message key="applyLoanApplication.form.Submit"/>" /></td>
    </tr>
    </table>
</form>
