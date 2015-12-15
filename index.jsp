<html>
<head>
  <title>
    MMS - MPPKVVCL
  </title>
  <script type="text/javascript">
  function preventBack(){
    window.history.forward();
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
  }
  
  function validateform(){  
    var name=document.getElementById("login");  
    if (name==null || name==""){  
      alert("Name can't be blank");  
      return false;  
    }
  }  
  </script>
</head>
<body>
  <%@ include file="MasterPageTopSection.jsp" %>
  <br/>
  <center>
    <div class="panel panel-primary">
      <div class="panel-heading">
        <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" height="32">
          <tr>
            <td width="100%" height="32" bgcolor="#000080">
              <h3 class="panel-title text-center" align="center">
                <font size="6" color="#FFFFFF">
                  MAINTANANCE MONITORING SYSTEM 
                </font>
              </h3>
            </td>
          </tr>
        </table>
        <h3 class="panel-title text-center">MPPKVVCL User</h3>
      </div>
    </center>         
    <div class="panel-body">
      <form action="Login.jsp" method="post" name="login1" id="signin" class="form-horizontal" onsubmit="return validateform()">
        <div class="form-group">
          <p align="center">
            <label for="inputEmail3" class="col-lg-2 control-label">Login Id</label>
            <input type="text" class="form-control" id="login" placeholder="Enter Login Id" size="20" name="userLoginid">
          </p>
          <div class="col-lg-10">
            <p align="center">&nbsp;</p>
          </div>
          <div class="form-group">
            <p align="center">
              <label for="inputPassword3" class="col-lg-2 control-label">Password</label>
              <input type="password" class="form-control" id="Password" placeholder="Enter Password" size="20" name="userPassword">
            </p>
            <center>
              <div class="col-lg-10">
                &nbsp;
              </div>
              <center>
                <div class="form-group">
                  <div class="col-lg-offset-2 col-lg-5">
                    <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </center>         
      </center>
      <br/>
      <br/>
      <%@ include file="MasterPageBottomSection.jsp" %>         
    </body>
    </html>
