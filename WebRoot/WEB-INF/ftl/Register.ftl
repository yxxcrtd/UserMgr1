<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title><@s.text name="usermgr.register.title" /></title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>

	<body onLoad="javascript:document.regForm.loginName.focus();">
		<table width="100%" height="100%">
			<tr>
				<td>
					&nbsp;
				</td>
				<td align="center" width="60%">
					<div style="border-width: 1px; border-style: solid; border-color: #CCCCCC">
						<@s.form name="regForm" action="register" method="post">
							<@s.hidden name="cmd" value="register" />
							<table border="0">
								<tr>
									<td colspan="3" align="center" height="50">
										<h3>
											<@s.text name="usermgr.register.title" />
										</h3>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center">
										<@s.actionerror cssStyle="color:#FF0000; font-weight:bold;" />
									</td>
								</tr>								
								<tr>
									<td align="right" width="38%" height="35">
										<@s.text name="usermgr.register.loginName" />
									</td>
									<td>									
										<@s.textfield name="loginName" maxLength="20" onfocus="this.select();" />
									</td>
									<td>
										<#if loginName??>
											<@s.fielderror cssStyle="color:#FF0000; font-weight:bold;">
												<@s.param>loginName</@s.param>
											</@s.fielderror>
										</#if>
									</td>
								</tr>								
								<tr>
									<td align="right" height="35">
										<@s.text name="usermgr.login.password"/>
									</td>
									<td>
										<@s.password name="userPassword" maxLength="25" />
									</td>
									<td>
										<#if userPassword??>
											<@s.fielderror cssStyle="color:#FF0000; font-weight:bold;">
												<@s.param>userPassword</@s.param>
											</@s.fielderror>
										</#if>
									</td>
								</tr>								
								<tr>
									<td align="right" height="35">
										<@s.text name="usermgr.register.rePassword" />
									</td>
									<td>
										<@s.password name="rePassword" maxLength="25" />
									</td>
									<td>
										<#if rePassword??>
											<@s.fielderror cssStyle="color:#FF0000; font-weight:bold;">
												<@s.param>rePassword</@s.param>
											</@s.fielderror>
										</#if>
									</td>
								</tr>
								<tr>
									<td align="right" height="35">
										<@s.text name="usermgr.register.email" />
									</td>
									<td>
										<@s.textfield name="email" maxLength="100" />
									</td>
									<td>
										<#if email??>
											<@s.fielderror cssStyle="color:#FF0000; font-weight:bold;">
												<@s.param>email</@s.param>
											</@s.fielderror>
										</#if>
									</td>
								</tr>
								<tr>
									<td align="right" height="35">
										<@s.text name="usermgr.register.question" />
									</td>
									<td>
										<@s.textfield name="question" maxLength="50" />
									</td>
									<td>
										<@s.fielderror cssStyle="color:#FF0000; font-weight:bold;">
											<@s.param>question</@s.param>
										</@s.fielderror>
									</td>
								</tr>
								<tr>
									<td align="right" height="35">
										<@s.text name="usermgr.register.answer" />
									</td>
									<td>
										<@s.textfield name="answer" maxLength="50" />
									</td>
									<td>
										<@s.fielderror cssStyle="color:#FF0000; font-weight:bold;">
											<@s.param>answer</@s.param>
										</@s.fielderror>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center" height="50">									
										<input class="input" style="cursor: pointer;" type="submit" name="submit" value="  <@s.text name='usermgr.public.register' />  " />&nbsp;&nbsp;
										<input class="input" style="cursor: pointer;" type="reset" name="reset" value="<@s.text name='usermgr.login.button.reset' />" />
									</td>
								</tr>
							</table>
						</@s.form>
					</div>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>