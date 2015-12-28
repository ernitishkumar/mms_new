<html>
<head>
	<title>MMS - EHVSS Details</title>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="css/guardian.css">
	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>-->
	<script src="js/jquery.guardian-1.0.min.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function() {
		/*$('#region').change(function(event) {
			var region = $("select#region").val();
			$.get('GetCircles', {
				regionName : region
			}, function(response) {
				var select = $('#circle');
				select.find('option').remove();
				$('<option>').val("-1").text("select circle").appendTo(select);
				$.each(response, function(index, value) {
					$('<option>').val(value).text(value).appendTo(select);
				});
			});
		});

		$('#circle').change(function(event) {
			var circle = $("select#circle").val();
			$.get('GetDivisions', {
				circleName : circle
			}, function(response) {
				var select = $('#division');
				select.find('option').remove();
				$('<option>').val("-1").text("select division").appendTo(select);
				$.each(response, function(index, value) {
					$('<option>').val(value).text(value).appendTo(select);
				});
			});
});*/
});
	</script>
	<style>
	form{
		background: -webkit-gradient(linear, bottom, left 175px, from(#CCCCCC), to(#EEEEEE));
		background: -moz-linear-gradient(bottom, #CCCCCC, #EEEEEE 175px);
		margin:auto;
		position:relative;
		width:550px;
		height:auto;
		font-family: Tahoma, Geneva, sans-serif;
		font-size: 14px;
		font-style: bold;
		line-height: 24px;
		font-weight: bold;
		color: #09C;
		text-decoration: none;
		-webkit-border-radius: 10px;
		-moz-border-radius: 10px;
		border-radius: 10px;
		padding:10px;
		border: 1px solid #999;
		border: inset 1px solid #333;
		-webkit-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
		-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
		box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
	}

	input{
		width:375px;
		display:block;
		border: 1px solid #999;
		height: 25px;
		-webkit-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
		-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
		box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
	}

	mark {
		background-color: white;
		color: red;
	}
	</style>
</head>
<body>
	<jsp:useBean id="user" class="mms.com.beans.UserLogin" scope="session" />
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
									<a href="home.jsp"><font color="#00FFFF">HOME</font></a>
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
		<h1>Enter EHVSS Details</h1>
		<br>
		<h4 align="center"><mark>${errorBean.errorMessage}</mark></h4>
		<form action="AddEHVSSDetails.jsp">
			<div>
				<label>
					<span>Enter EHVSS Name</span><input id="name" type="text" name="name" required="true"/>
					<p class="ui-error-message" id="error-message-phone"></p>
				</label>

				<label>
					<span>Enter EHVSS Code</span><input id="code" type="text" name="code" required="true"/>
				</label>
				<br/>
				<label>
					<span>Select EHVSS Region</span>
					<select name="region" id="region" required="true">
						<option >Select Region</option>
						<option >INDORE</option>
						<option >UJJAIN</option>
					</select>
				</label>
				<br/>
				<br/>

				<!-- <label>
					<span>Select EHVSS Circle</span>
					<select name="circle" id="circle">
						<option >Select circle</option>
					</select>
				</label>
				<br/>
				<br/>
				<label>
					<span>Select EHVSS Division</span>
					<select name="division" id="division">
						<option>Select Division</option>
					</select>
				</label>
				<br/>
				<br/> -->
				<label>
					<input type="submit" value="Add EHVSS" />
				</label>
				<br/>
				<label>
					<input type="reset" value="Reset" />
				</label>

			</div>
		</form>
	</div>
	<br/>
	<br/>
	<br/>
	<%@ include file="MasterPageBottomSection.jsp" %>
</body>
</html>