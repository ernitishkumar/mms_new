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
		<b><a href="EHVSSDetails.jsp">Add EHVSS Details</a></b>           <b><a href="ViewEhvss.jsp">View EHVSS Details</a></b>
		<br/>
		<b><a href="KV33FeederDetails.jsp">Add 33 KV Feeder Details</a></b>
		<br/>
		<b><a href="Substation.jsp">Add Substation Details</a></b>
		<br/>
		<b><a href="KV11FeederDetails.jsp">Add 11 KV Feeder Details</a></b>
	</div>
	<br/>
	<br/>
	<br/>
	<%@ include file="MasterPageBottomSection.jsp" %>
</body>
</html>