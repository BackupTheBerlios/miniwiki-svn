<%@ include file="includes/taglibs.jsp"%>

  <jsp:include page="includes/header.jsp">
      <jsp:param name="menuId" value="6" />
  </jsp:include>


  <div id="content_noside">
    <center>
    <c:import url="/showLoanForm.ajax" />
    </center>    
  </div>


  <jsp:include page="includes/footer.jsp" />



