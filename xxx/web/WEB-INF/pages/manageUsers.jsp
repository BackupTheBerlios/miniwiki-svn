<%@ page import="foo.bar.site.controller.DefaultCommandController" %>
<%@ include file="includes/taglibs.jsp"%>

  <jsp:include page="includes/header.jsp">
      <jsp:param name="menuId" value="6" />
  </jsp:include>

  <jsp:include page="includes/adminSideMenu.jsp"/>

  <div id="content">
       <spring:errors path="*" />
       <form action="<c:url value='/deleteUser.view'/>" method="POST">
       <table>
       <tr>
           <td><fmt:message key="manageUser.table.username"/></td>
           <td><input type="submit" value="<fmt:message key="manageUser.table.delete"/>"/></td>
       </tr>
       <c:forEach var="user" items="<%=request.getAttribute(DefaultCommandController.DEFAULT_COMMAND_NAME)%>">
            <tr>
                <td><c:out value="${user.username}"/></td>
                <td><input type="checkbox" name="userIds" value="${user.id}"/></td>
            </tr>
       </c:forEach>
       </table>
       </form>
  </div>


  <jsp:include page="includes/footer.jsp" />



