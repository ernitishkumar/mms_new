<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Setup and Load Data to jTable using Servlets and JSP</title>
    <link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
    <link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
    <!-- Importing CSS file for jTable form validations-->
    <!-- <link href="css/validationEngine/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/validationEngine/jquery.validationEngine.js"></script>
    <script type="text/javascript" src="js/validationEngine/jquery.validationEngine-en.js"></script>-->

    <!-- Include jTable script file.
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>-->
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.jtable.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function () {
        //initialize jTable
        $('#EhvssTableContainer').jtable({
            title: 'Table of EHVSS',
            paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10)   
            actions: {
                listAction: 'EHVSSController?action=list',
                createAction:'EHVSSController?action=create',
                updateAction: 'EHVSSController?action=update',
                deleteAction: 'EHVSSController?action=delete'
            },
            fields:{
                id: {
                  title: 'EHVSS ID',
                  key: true,
                  list: false,
                  create:false
              },
              name: {
                title: 'EHVSS Name',
                width: '30%'
            },
            code: {
                title: 'Code',
                width: 'auto'
            },
            region: {
                title: 'Region',
                width: '15%',
                options: ['INDORE','UJJAIN'],
            }
        },
        deleteConfirmation: function(data) {
            data.deleteConfirmMessage = 'Are you sure to delete EHVSS: ' + data.record.name + '?';
        }
      /*  //Initialize validation logic when a form is created
        ,formCreated: function (event, data) {
            data.form.find('input[name="name"]').addClass('validate[required]');
            data.form.find('input[name="code"]').addClass('validate[required]');
            data.form.validationEngine();
        },
            //Validate form when it is being submitted
            formSubmitting: function (event, data) {
                return data.form.validationEngine('validate');
            }*/
            //Dispose validation logic when form is closed
            /*formClosed: function (event, data) {
                data.form.validationEngine('hide');
                data.form.validationEngine('detach');
            }*/
        });

     /*    //Re-load records when user click 'load records' button.
        $('#LoadRecordsButton').click(function (e) {
            e.preventDefault();
            $('#EhvssTableContainer').jtable('load', {
                region: $('#region').val()
            });
        });
 
        //Load all records when page is first shown
        $('#LoadRecordsButton').click();*/

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
        <h1>All EHVSS Records</h1><br/>
        <!--<div class="filtering">
                Select Region: 
                <select id="region" name="region">
                    <option selected="selected">All Regions</option>
                    <option>INDORE</option>
                    <option>UJJAIN</option>
                </select>
                <button type="submit" id="LoadRecordsButton">Load records</button>
            </div>-->
            <form>
                <div id="EhvssTableContainer"></div>
            </form>
        </div>
        <br/>
        <br/>
        <br/>
        <%@ include file="MasterPageBottomSection.jsp" %>
    </body>
    </html>
