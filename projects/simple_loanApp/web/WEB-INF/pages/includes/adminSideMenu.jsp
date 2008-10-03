<%@include file="taglibs.jsp"%>
    <div id="side">
    <div id="navcontainer">
      <h2><fmt:message key="admin.sideMenu.link"/></h2>
      <ul>
        <li><a href="<c:url value="/manageUser.view" />"><fmt:message key="admin.sideMenu.manageUser"/></a></li>
        <li><a href="<c:url value="/manageAddUser.view"/>"><fmt:message key="admin.sideMenu.addUser"/></a></li>
        <li><a href="<c:url value='/manageLoanApplication.view'/>"><fmt:message key="admin.sideMenu.manageLoanApplication"/></a></li>
        <li><a href="<c:url value='/manageProfile.view'/>"><fmt:message key="admin.sideMenu.manageProfile"/></a></li>
      </ul>
    </div>
    </div>