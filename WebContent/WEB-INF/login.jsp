<%@ page import="application.Login,application.User"%>
<%
	String result = "";
	if (request.getParameter("logout") != null) {
		result = Login.logout(request);
		;
	} else {
		if (request.getParameter("login") != null) {
			result = Login.login(request,
					request.getParameter("login"),
					request.getParameter("password"));
		}
	}
	User user = Login.getSessionUser(request);
%>
<%if (user!=null) { %>
	<form action="" method="post">
	<span style='color:#FFFFFF'>Hello, <%=user.getLogin()%> &nbsp;&nbsp;&nbsp;</span>
	<input type="submit" name="logout"
		style="background-color: #000000; color: #FFFFFF" id='logout-button'
		value="wyloguj" />
		<span style='color:#00FF00'><%= user.getRole()%> </span>
	</form>
<%} else { %>
<form action="" method="post" id="login-form"
	style='padding-bottom: 10px; color: #FFFFFF'>
	<% if(!result.equals("")){ %>
		<span style='color: red' id="error"> <%=result%> &nbsp;&nbsp;&nbsp;&nbsp;</span>
	<% } %>
	<label>login</label> <input
		style='color: #000000' class="input-small"
		style="padding:0px 6px 0px 6px" type="text" name="login" id="login" /><label>password</label>
	<input style='color: #000000' class="input-small"
		style="padding:0px 6px 0px 6px" type="password" name="password"
		id="password" /> <input type="submit"
		style="background-color: #000000; color: #FFFFFF" id='login-button'
		value="zaloguj" />
</form>
<% }%>