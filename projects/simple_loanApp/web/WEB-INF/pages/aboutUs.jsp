<%@ include file="includes/taglibs.jsp"%>

  <jsp:include page="includes/header.jsp">
      <jsp:param name="menuId" value="2" />
  </jsp:include>

  <jsp:include page="includes/aboutUsSideMenu.jsp"/>

  <div id="content">
    <h2><fmt:message key="aboutUs.description.t1" /></h2>
    <p><fmt:message key="aboutUs.description.t1.p1" /></p>
    <p><fmt:message key="aboutUs.description.t1.p2" /></p>
    <p><fmt:message key="aboutUs.description.t1.p3" /></p>

        <center>
        <table class="imageTable" width="100%">
            <tr>
                <td>
                    <h2><a href="<c:url value="/contactUs.view"/>"><fmt:message key="home.label.aboutUs"/></a></h2>
                    <p style="margin-bottom: 0pt;"><a href="<c:url value="/aboutUs.view"/>">
                        <fmt:message key="home.description.aboutUs"/>
                    </a></p><br/><br/>
                </td>
                <td>
                    <h2><a href="<c:url value="/referrer.view"/>"><fmt:message key="home.label.referrer"/></a></h2>
                    <p><a href="<c:url value='/referrer.view'/>">
                        <fmt:message key="home.description.referrer"/>
                    </a><p/><br/><br/>
                </td>
            </tr>
        </table>
        </center>
  </div>


  <jsp:include page="includes/footer.jsp" />



