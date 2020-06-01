<%@page import="com.school.common.generic.Base64"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String url = Base64.decode(request.getParameter("rd")); %>

<script>
$(document).ready(function () {
	$.sendRequest("<%=url %>", "post", null);
});
</script>