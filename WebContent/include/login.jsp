<%
String rutalog = request.getParameter("ruta");
%>
<form name="login" method="post" action="<%=rutalog%>/login.do">
	<input name="uri" value="" type="hidden" /> <input
		name="url" value="#" type="hidden" />

	<table>
		<tbody>
			<tr>
				<td>User ID:</td>
				<td><input name="username" id="username" style="width: 52px" class="form"
					value="username" onfocus="nullify_if_equal(this, 'username')"
					onkeypress="return handle_keypress(this, event)" type="text" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input name="pwd" id="pwd" style="width: 52px" class="form"
					value="" onkeypress="return handle_keypress(this, event)"
					type="password" /></td>
				<td><a href="javascript:document.login.submit()" class="noNav"
					onclick="return validate_login(1)"><img
						src="<%=rutalog%>/images/button_go.gif" alt="Attempt to Login" name="sub_login"
						border="0" height="16" width="21" /></a></td>
			</tr>

		</tbody>
	</table>
</form>