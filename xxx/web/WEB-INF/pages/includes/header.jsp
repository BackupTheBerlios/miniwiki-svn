<!--
   1 - Home
   2 - About us
   3 - Services
   4 - FAQs
   5 - Privacy
   6 - Apply Now
   7 - Contact Us
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglibs.jsp"%>

<!DOCTYPE html PUBLIC
        "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Company's Site</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link href="<c:url value="/styles/style.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="container">
    <div id="login">
        <input type="text" value="Username" />
        <input name="password" type="password" />
        <input type="button" value="Log In" />
    </div>

    <div id="top">
    <ul id="navPyra">
      <li><a <c:if test="${'1' eq param.menuId}">class="active"</c:if> href="<c:url value="/home.view" />">Home</a></li>
      <li><a <c:if test="${'2' eq param.menuId}">class="active"</c:if> href="<c:url value="/aboutUs.view" />">About Us</a></li>
      <li><a <c:if test="${'3' eq param.menuId}">class="active"</c:if> href="<c:url value="/services.view"/>">Services</a></li>
      <li><a <c:if test="${'4' eq param.menuId}">class="active"</c:if> href="<c:url value="/faq.view"/> ">FAQ</a></li>
      <li><a <c:if test="${'5' eq param.menuId}">class="active"</c:if> href="<c:url value="/privacy.view"/>">Privacy</a></li>
      <li><a <c:if test="${'6' eq param.menuId}">class="active"</c:if> href="<c:url value="/applyNow.view"/>">Apply Now</a></li>
      <li><a <c:if test="${'7' eq param.menuId}">class="active"</c:if> href="<c:url value="/contactUs.view"/>">Contact Us</a></li>
    </ul>
  </div>
  <div id="banner">
    <h1>Company's Site</h1>
  </div> 