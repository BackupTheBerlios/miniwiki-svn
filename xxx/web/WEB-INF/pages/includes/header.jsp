<!--
   1 - Home
   2 - About us
   3 - Services
   4 - FAQs
   5 - Privacy
   6 - Apply Now
   7 - Contact Us
-->

<%@include file="taglibs.jsp"%>
    <div id="top">
    <ul id="navPyra">
      <li><a <c:if test="${'1' eq param.menuId}">class="active"</c:if> href="http://www.free-css.com/">Home</a></li>
      <li><a <c:if test="${'2' eq param.menuId}">class="active"</c:if> href="http://www.free-css.com/">About Us</a></li>
      <li><a <c:if test="${'2' eq param.menuId}">class="active"</c:if> href="http://www.free-css.com/">Services</a></li>
      <li><a <c:if test="${'2' eq param.menuId}">class="active"</c:if> href="http://www.free-css.com/">FAQ</a></li>
      <li><a <c:if test="${'2' eq param.menuId}">class="active"</c:if> href="http://www.free-css.com/">Privacy</a></li>
      <li><a <c:if test="${'2' eq param.menuId}">class="active"</c:if> href="http://www.free-css.com/">Apply Now</a></li>
      <li><a <c:if test="${'2' eq param.menuId}">class="active"</c:if> href="http://www.free-css.com/">Contact Us</a></li>
    </ul>
  </div>
  <div id="banner">
    <h1>Company's Site</h1>
  </div>