<jsp:useBean id="userBean" class="mms.com.beans.User" scope="session" />
<html>
<head>
	<title>MMS - Home</title>
</head>
<body>
	<%@ include file="MasterPageTopSection.jsp" %>
	<br/>
	<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
		<tr>
			<td width="100%" colspan="2">
				<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF">
					<tr>
						<td width="34%" bgcolor="#000080">
							<b>
								<span lang="en">
									<font color="#00FFFF">WEL COME USER: ${userBean.name}</font>
								</span>
							</b>
						</td>

						<td width="7%" bgcolor="#000080">
							<p align="center">
								<b>
									<font color="#FFFFFF"><a target="_parent" href="logout.jsp">
										<span lang="en">
											<font color="#FFFFFF">SIGN OUT</font>
										</span>
									</font>
								</b>
							</p>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br/>
	<div id="linkHolder" name="linkHolder" align="center">
		<b>EHVSS[   <a href="EHVSSDetails.jsp">Add</a>, <a href="ViewEhvss.jsp">View</a>       ]</b>
		<br/>
		<b>33KVFeeder[   <a href="KV33FeederDetails.jsp">Add</a>, <a href="ViewKV33Feeders.jsp">View</a>   ]</b>
		<br/>
		<b>Substation[   <a href="Substation.jsp">Add</a>, <a href="ViewSubstations.jsp">View</a>   ]</b>
		<br/>
		<b>11KVFeeder[   <a href="KV11FeederDetails.jsp">Add</a>, <a href="ViewKV11Feeders.jsp">View</a>   ]</b>
	</div>
	<br/>
	<br/>
	<br/>
	<%@ include file="MasterPageBottomSection.jsp" %>
</body>
</html>