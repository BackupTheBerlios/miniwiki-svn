<%@include file="taglibs.jsp"%>
    <div id="side">
    <div id="navcontainer">
      <h2>Links</h2>
      <ul>
        <li><a href="<c:url value="/manageUser.view" />"><fmt:message key="admin.sideMenu.manageUser"/></a></li>
        <li><a href="<c:url value="/addUser.view"/>"><fmt:message key="admin.sideMenu.addUser"/></a></li>
        <li><a href="<c:url value='/manageLoanApplication.view'/>"><fmt:message key="admin.sideMenu.manageLoanApplication"/></a></li>
        <li><a href="<c:url value='/manageProfile.view'/>"><fmt:message key="admin.sideMenu.manageProfile"/></a></li>
      </ul>
    </div>
    </div>