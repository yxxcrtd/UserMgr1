<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title><s:text name="usermgr.login.title" /></title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
		<script type="text/javascript">
		<!-- Begin
		function refresh() {
			document.getElementById("authImg").src = 'authimg?now=' + new Date();
		}
		// End -->
		</script>
	</head>

	<body onLoad="javascript:loginForm.username.focus();">
		<table width="100%" height="100%">
			<tr>
				<td>
					&nbsp;
				</td>
				<td align="center" width="55%">
					<div style="border-width: 1px; border-style: solid; border-color: #CCCCCC">
						<s:form action="login" name="loginForm">
							<s:if test="redUrl==null">
							<%
								String ru = request.getParameter("RedUrl");
								if (ru == null) {
									ru = "welcome.jsp";
								}
							%>
								<input type="hidden" id="1" name="redUrl" value="<%=ru%>" />
							</s:if>
							<s:else>
								<input type="hidden" id="2" name="redUrl" value="<s:property value='redUrl' />" />
							</s:else>
							<table border="0">
								<tr>
									<td colspan="3" align="center" height="50">
										<h3>
											<s:text name="usermgr.login.title" />
										</h3>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center">
										<s:actionerror cssStyle="color:#FF0000; font-weight:bold;" />
									</td>
								</tr>
								<tr>
									<td align="right" width="32%" height="30">
										<s:text name="usermgr.login.username" />
									</td>
									<td>
										<s:textfield id="username" name="username" />
									</td>
									<td>
										<s:if test="username==''">
											<s:fielderror cssStyle="color:#FF0000; font-weight:bold;">
												<s:param>username</s:param>
											</s:fielderror>
										</s:if>
									</td>
								</tr>
								<tr>
									<td align="right" height="30">
										<s:text name="usermgr.login.password" />
									</td>
									<td>
										<s:password id="password" name="password" />
									</td>
									<td>
										<s:if test="password==''">
											<s:fielderror cssStyle="color:#FF0000; font-weight:bold;">
												<s:param>password</s:param>
											</s:fielderror>
										</s:if>
									</td>
								</tr>
								<tr>
									<td align="right" height="30">
										<s:text name="usermgr.login.vercode" />
									</td>
									<td>
										<input type="text" name="vercode" maxlength="4" value="" autocomplete="off" id="vercode" />
									</td>
									<td>
										<s:if test="vercode==''">
											<s:fielderror cssStyle="color:#FF0000; font-weight:bold;">
												<s:param>vercode</s:param>
											</s:fielderror>
										</s:if>
									</td>
								</tr>
								<tr>
									<td></td>
									<td colspan="2">
										<a href="#" onClick="refresh()"><img id="authImg" src="authimg" border="0" title="<s:text name="usermgr.public.refresh" />" /></a>
										<a href="#" onclick="this.href='find.action?username=' + document.getElementById('login_username').value"><s:text name="usermgr.find.title" /></a>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center" height="50">
										<s:submit cssClass="input" cssStyle="cursor: pointer;" value="%{getText('usermgr.login.button.login')}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<s:reset cssClass="input" cssStyle="cursor: pointer;" value="%{getText('usermgr.login.button.reset')}" />
									</td>
								</tr>
							</table>
						</s:form>
					</div>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>
