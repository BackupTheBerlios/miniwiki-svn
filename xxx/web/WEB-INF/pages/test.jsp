<%@ include file="includes/taglibs.jsp"%>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <h1>1<%=request.getAttribute("command")%></h1>
        <h1>2<spring:bind path="command.name" /></h1>
        <h1>3<spring-form:errors path="*" /></h1>
    </body>
</html>