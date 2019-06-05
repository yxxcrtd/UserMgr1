<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title><@s.text name="usermgr.find.title" /></title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>

	<body onLoad="javascript:findForm.answer.focus();">
		<table width="100%" height="100%">
			<tr>
				<td>
					&nbsp;
				</td>
				<td align="center" width="55%">
					<div style="border-width: 1px; border-style: solid; border-color: #CCCCCC">
						<@s.form action="find" name="findForm">
							<@s.hidden name="cmd" value="three" />
							<@s.hidden name="username" value="${username}" />
							<table border="0">
								<tr>
									<td colspan="3" align="center" height="60">
										<h3>
											<@s.text name="usermgr.find.step.two" />
										</h3>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center">
										<@s.actionerror cssStyle="color:#FF0000; font-weight:bold;" />
									</td>
								</tr>								
								<tr>
									<td align="right" height="40" width="32%">
										<@s.text name="usermgr.login.username" />
									</td>
									<td>
										<font style="font-weight: bold; font-size: 13px;">&nbsp;${username}</font>
									</td>
								</tr>
								<tr>
									<td align="right" height="30">
										<@s.text name="usermgr.find.question" />
									</td>
									<td>
										<font style="font-weight: bold; font-size: 13px;">&nbsp;${question}</font>
									</td>
								</tr>
								
								<tr>
									<td align="right" height="40">
										<@s.text name="usermgr.register.answer" />
									</td>
									<td>
										<@s.textfield name="answer" onfocus="this.select();" />
									</td>
									<td>
										<@s.if test="username==''">
											<@s.fielderror cssStyle="color:#FF0000; font-weight:bold;">
												<@s.param>username</@s.param>
											</@s.fielderror>
										</@s.if>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="right" height="50">
										<@s.submit cssClass="input" cssStyle="cursor: pointer;" value="%{getText('usermgr.find.step.next')}" />
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
