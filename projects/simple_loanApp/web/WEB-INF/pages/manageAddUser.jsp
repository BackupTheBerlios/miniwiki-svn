<%@ page import="foo.bar.site.controller.DefaultCommandController" %>
<%@ include file="includes/taglibs.jsp"%>

  <jsp:include page="includes/header.jsp">
      <jsp:param name="menuId" value="-1" />
  </jsp:include>

  <jsp:include page="includes/adminSideMenu.jsp"/>






  <div id="content">
       <c:import url="/showAddUserForm.ajax" />
  </div>


  <jsp:include page="includes/footer.jsp" />