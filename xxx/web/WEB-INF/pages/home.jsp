<%@ include file="includes/taglibs.jsp"%>

  <jsp:include page="includes/header.jsp">
      <jsp:param name="menuId" value="1" />
  </jsp:include>

  <div id="content_noside">
      <center>
      <table class="imageTable" width="100%" >
      <tr><td>
              <div class="downloads">
                  <div class="newsItem">
                      <h2><a href="<c:url value="/aboutUs.view"/>"><fmt:message key="home.label.aboutUs"/></a></h2>
                      <p style="margin-bottom: 0pt;"><a href="<c:url value="/aboutUs.view"/>">
                          <fmt:message key="home.description.aboutUs"/>
                      </a></p><br/><br/>
                  </div>
              </div>
      </td><td>
              <div class="downloads">
                  <div class="newsItem">
                    <h2><a href="<c:url value="/contactUs.view"/>"><fmt:message key="home.label.contactUs"/></a></h2>
                    <p style="margin-bottom: 0pt;"><a href="<c:url value='/contactUs.view'/>">
                        <fmt:message key="home.description.contactUs"/>
                    </a></p><br/><br/>
                  </div>
              </div>

      </td><td>
              <div class="downloads">
                  <div class="newsItem">
                    <h2><a href="<c:url value="/applyNow.view"/>"><fmt:message key="home.label.applyNow"/></a></h2>
                    <p style="margin-bottom: 0pt;"><a href="<c:url value='/applyNow.view' />">
                        <fmt:message key="home.description.applyNow"/>
                    </a><p/><br/><br/>
                  </div>
              </div>

      </td><td>
              <div class="downloads">
                  <div class="newsItem">
                    <h2><a href="<c:url value="/referrer.view" "><fmt:message key="home.label.referrer"/></a></h2>
                    <p style="margin-bottom: 0pt;"><a href="<c:url value='/referrer.view'/>">
                        <fmt:message key="home.description.referrer"/>
                    </a><p/><br/><br/>
                  </div>
              </div>
      </td></tr></table>
      </center>
  </div>

  <jsp:include page="includes/footer.jsp" />

