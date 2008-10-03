<%@ page import="foo.bar.site.controller.DefaultCommandController" %>
<%@ include file="includes/taglibs.jsp"%>

  <jsp:include page="includes/header.jsp">
      <jsp:param name="menuId" value="-1" />
  </jsp:include>

  <jsp:include page="includes/adminSideMenu.jsp"/>

    <script type="text/javascript">
        function viewLoanDetails(loanId) {
            $.post(
                "<c:url value="/viewLoanApplication.ajax"/>",
                { loanApplicationId: loanId },
                function(data) {
                    $("#content").html(data);
                });
        }
    </script>


    
  <div id="content">
       <c:import url="/listLoanApplication.ajax" />
  </div>


  <jsp:include page="includes/footer.jsp" />