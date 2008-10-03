<%@ include file="includes/taglibs.jsp"%>

  <jsp:include page="includes/header.jsp">
      <jsp:param name="menuId" value="7" />
  </jsp:include>

  <jsp:include page="includes/contactUsSideMenu.jsp"/>

  <div id="content">
    <h2><fmt:message key="contactUs.description.t1"/></h2>
    <p><fmt:message key="contactUs.description.t1.p1"/></p>
      <p><fmt:message key="contactUs.description.t1.p2"/></p>
      <ul>
          <li><fmt:message key="static.contact.email1"/></li>
          <li><fmt:message key="static.contact.email2"/></li>
          <li><fmt:message key="static.contact.email3"/></li>
      </ul>
      <p><fmt:message key="contactUs.description.t1.p3"/></p>
      <ul>
          <li><fmt:message key="static.contact.phone1"/></li>
          <li><fmt:message key="static.contact.phone2"/></li>
          <li><fmt:message key="static.contact.phone3"/></li>
      </ul>
  </div>


  <jsp:include page="includes/footer.jsp" />



