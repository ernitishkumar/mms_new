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
        $('#KV11TableContainer').jtable({
            title: 'Table of 11KV Feeders',
            paging: true, //Enable paging
            pageSize: 20, //Set page size (default: 10)   
            actions: {
                listAction: 'KV11FeederController?action=list',
                createAction:'KV11FeederController?action=create',
                updateAction: 'KV11FeederController?action=update',
                deleteAction: 'KV11FeederController?action=delete'
            },
            fields:{
                id: {
                  title: 'ID',
                  key: true,
                  list: true,
                  create:false,
                  update:true,
                  width: 'auto'
              },
              code: {
                title: 'Code',
                width: 'auto',
                create: true
            },
            name: {
                title: 'Feeder Name',
                width: 'auto'
            },
            region: {
                title: 'Region',
                width: 'auto',
                options: ['INDORE','UJJAIN'],
            },
            circle: {
                title: 'Circle',
                width: 'auto',
                dependsOn: 'region',
                //options:['INDORECITY','INDOREO&M','BARWANI','KHANDWA','BURHANPUR','KHARGONE','DHAR','JHABUA','SHAJAPUR','NEEMUCH','MANDSAUR','DEWAS','RATLAM','UJJAIN','AGAR']
                options: function(data){
                    if(data.source=='edit'||data.source=='create'||data.source=='update'){
                        return 'GetCircles?source=jtable&regionName='+data.dependedValues.region;
                    }else if(data.source=='list'){
                        //return ['INDORECITY','INDOREO&M','BARWANI','KHANDWA','BURHANPUR','KHARGONE','DHAR','JHABUA','SHAJAPUR','NEEMUCH','MANDSAUR','DEWAS','RATLAM','UJJAIN','AGAR'];
                        return [data.record.circle];
                    }
                }
            },
            division: {
                title: 'Division',
                width: 'auto',
                dependsOn: 'circle',
                /*options:['INDORE-EAST','INDORE-SOUTH','INDORE-WEST','INDORE-NORTH','INDORE-CENTRAL','MHOW',
                'INDOREO&M','DEPALPUR','PITHAMPUR','BARWANI','SENDHWA','KHANDWA-I','PANDHANA','KHANDWACITY','KHANDWA-II','BURHANPURO&M','BURHANPURCITY','KHARGONE-I','MANDLESHWAR','KHARGONE-II',
                'BARWAHA','DHAR','RAJGARH-DHAR','MANAWAR','JHABUA','ALIRAJPUR','SHAJAPUR','SHUJALPUR','NEEMUCH','JAWAD','MANASA','MANDSAUR','MALHARGARH','SITAMAU','GAROTH','DEWASCITY','DEWASO&M',
                'SONKATCH','BAGLI','KANNOD','RATLAMO&M','RATLAMCITY','JAORA','ALOT','UJJAINEAST','UJJAINWEST','UJJAINO&M','MAHIDPUR','NAGDA','BARNAGAR','TARANA','AGAR','SUSNER']*/
                options: function(data){
                    if(data.source=='edit'||data.source=='create'||data.source=='update'){
                        return 'GetDivisions?source=jtable&circleName='+data.dependedValues.circle;
                    }else if(data.source=='list'){
                        //return ['INDORECITY','INDOREO&M','BARWANI','KHANDWA','BURHANPUR','KHARGONE','DHAR','JHABUA','SHAJAPUR','NEEMUCH','MANDSAUR','DEWAS','RATLAM','UJJAIN','AGAR'];
                        return [data.record.division];
                    }
                }
            },
            dc: {
                title: 'DC Name',
                width: 'auto',
            },
            feederType: {
                title: 'Feeder Category',
                width: 'auto',
                options:['INDUSTRIAL','CHQ','ESSENTIAL','FS-IRR','FS-DLF','RURAL-MIXED','TOWN-OTHER','THQ','RURAL- MIXED','DUMMY','FS-DL','DHQ','DLF','FS -IRR','IND']
            },
            substationID: {
                title: 'Substation Name',
                width: 'auto',
                dependsOn: 'division',
                options: function(data){
                    if(data.source=='edit'||data.source=='create'||data.source=='update'){
                        return 'GetSubstationNames?source=jtable&divisionName='+data.dependedValues.division;
                    }else if(data.source=='list'){
                       return [data.record.substationID];
                   }
               }
           }
       },
       deleteConfirmation: function(data) {
        data.deleteConfirmMessage = 'Are you sure to delete 11KVFeeder: ' + data.record.name + '?';
    }
});
$('#KV11TableContainer').jtable('load');
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
        <h1>All 11KV Feeders </h1>
        <div id="KV11TableContainer"></div>
    </div>
    <br/>
    <br/>
    <br/>
    <%@ include file="MasterPageBottomSection.jsp" %>
</body>
</html>
