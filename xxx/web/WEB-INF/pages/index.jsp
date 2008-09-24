<%@ include file="includes/taglibs.jsp"%>

<!DOCTYPE html PUBLIC
        "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Company's Site</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="container">

  <jsp:include page="includes/header.jsp">
      <jsp:param name="menu" value="1" />
  </jsp:include>

  <jsp:include page="includes/sideMenu.jsp"/>

  <div id="content">
    <h2>Welcome to <span style="font-weight: bold;">BUSINESS 2</span> Template</h2>
    <blockquote>This template has been tested in Mozilla and IE7. The page validates as XHTML 1.0 Transitional using valid CSS. The images used in this template are courtesy of <a href="http://www.sxc.hu/" title="free images">stock xchng</a>. <br />
      For more FREE templates visit <a href="http://www.mitchinson.net">my website</a>.</blockquote>
    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Duis et arcu. Sed blandit lacus a erat hendrerit accumsan. Suspendisse lacus. Praesent aliquet. Quisque faucibus sem vel eros. Quisque faucibus sem vel eros. Nunc et enim. Donec sem nulla, vehicula eu, euismod sed, pulvinar in, velit. Cras eu purus quis urna pulvinar aliquet.</p>
    <p class="big"><a href="http://www.free-css.com/"><img class="imgright" src="images/image.jpg" alt="template" /> 'Web Design is not an exact science, it is an art.'</a></p>
    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Duis et arcu. Sed blandit lacus a erat hendrerit accumsan. Suspendisse lacus. Praesent aliquet.</p>
    <h2>Web Design at its best</h2>
    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Duis et arcu. Sed blandit lacus a erat hendrerit accumsan. Suspendisse lacus. Praesent aliquet. Quisque faucibus sem vel eros. Nunc et enim. Donec sem nulla, vehicula eu, euismod sed, pulvinar in, velit. Cras eu purus quis urna pulvinar aliquet.</p>
    <p><a href="http://www.free-css.com/"> &raquo; Continue ...</a></p>
  </div>
  
  <jsp:include page="includes/footer.jsp" />

</div>
</body>
</html>

