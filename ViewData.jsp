<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Setup and Load Data to jTable using Servlets and JSP</title>
    <link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
    <link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
    <!-- Include jTable script file. -->
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="js/jquery.jtable.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function () {
        //initialize jTable
        $('#EhvssTableContainer').jtable({
            title: 'Table of EHVSS',
            actions: {
                listAction: 'EHVSSController?action=list',
                createAction:'CRUDController?action=create',
                updateAction: 'CRUDController?action=update',
                deleteAction: 'CRUDController?action=delete'
            },
            fields: {
                id: {
                    key: true,
                    list: false
                },
                name: {
                    title: 'EHVSS Name',
                    width: '30%'
                },
                code: {
                    title: 'Code',
                    width: '30%'
                },
                region: {
                    title: 'Region',
                    width: '20%'
                },
                circle: {
                    title: 'Circle',
                    width: '20%',
                    
                },
                division: {
                    title: 'Division',
                    width: '20%',
                    
                }
                
            }
        });
        $('#EhvssTableContainer').jtable('load');
});
</script>
</head>
<body>
    <jsp:useBean id="user" class="mms.com.beans.UserLogin" scope="session" />
    <%@ include file="MasterPageTopSection.jsp" %>
    <br/>
    <h4 align="center"><mark>${errorBean.errorMessage}</mark></h4>
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
    <div style="width:60%;margin-right:20%;margin-left:20%;text-align:center;">
        <h1>Setup and Load Data in jTable using Servlet and JSP</h1>
        <h4>Demo by Nitish Kumar</h4>
        <div id="EhvssTableContainer"></div>
    </div>
    <br/>
    <br/>
    <br/>
    <%@ include file="MasterPageBottomSection.jsp" %>
</body>
</html>
