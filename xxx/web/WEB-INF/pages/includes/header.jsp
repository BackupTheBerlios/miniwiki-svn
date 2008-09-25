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

<!DOCTYPE html PUBLIC
        "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Company's Site</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="<c:url value="/styles/style.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="container">
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