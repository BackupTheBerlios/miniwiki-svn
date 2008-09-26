<%@ page import="foo.bar.site.interceptor.AccessInterceptor" %>
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
    <title><fmt:message key="static.companyName" /></title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <link href="<c:url value="/styles/style.css"/>" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<c:url value="/scripts/jquery.js" />"></script>
</head>
<body>

<div id="container">
    <div id="login">
        <c:import url="/WEB-INF/pages/ajax_login.jsp" />
    </div>

    <div id="top">
    <ul id="navPyra">
      <li><a <c:if test="${'1' eq param.menuId}">class="active"</c:if> href="<c:url value="/home.view" />"><fmt:message key="header.menu.home"/> </a></li>
      <li><a <c:if test="${'2' eq param.menuId}">class="active"</c:if> href="<c:url value="/aboutUs.view" />"><fmt:message key="header.menu.aboutUs"/></a></li>
      <li><a <c:if test="${'3' eq param.menuId}">class="active"</c:if> href="<c:url value="/services.view"/>"><fmt:message key="header.menu.services"/></a></li>
      <li><a <c:if test="${'4' eq param.menuId}">class="active"</c:if> href="<c:url value="/faq.view"/> "><fmt:message key="header.menu.faq"/></a></li>
      <li><a <c:if test="${'5' eq param.menuId}">class="active"</c:if> href="<c:url value="/privacy.view"/>"><fmt:message key="header.menu.privacy"/> </a></li>
      <li><a <c:if test="${'6' eq param.menuId}">class="active"</c:if> href="<c:url value="/applyNow.view"/>"><fmt:message key="header.menu.applyNow"/></a></li>
      <li><a <c:if test="${'7' eq param.menuId}">class="active"</c:if> href="<c:url value="/contactUs.view"/>"><fmt:message key="header.menu.contactUs"/></a></li>
    </ul>
    </div>
    <div id="banner">
        <h1><fmt:message key="static.companyName"/></h1>
    </div>
    