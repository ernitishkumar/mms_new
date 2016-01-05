<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View 33KV Feeders</title>
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

       $('#region').change(function(event) {
        var region = $("select#region").val();
        $.get('GetEhvssNames', {
            regionName : region
        }, function(response) {
            var select = $('#ehvss');
            select.find('option').remove();
            $('<option>').val("-1").text("select EHVSS").appendTo(select);
            $.each(response, function(index, value) {
                $('<option>').val(value.id).text(value.name).appendTo(select);
            });
        });

        $.get('GetCircles',{
            regionName : region
        }, function(response) {
            var select = $('#circle');
            select.find('option').remove();
            $('<option>').val("-1").text("select circle").appendTo(select);
            $.each(response, function(index, value) {
                $('<option>').val(value).text(value).appendTo(select);
            });
             $('#circle').change();
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
    });
        //initialize jTable
        $('#KV33TableContainer').jtable({
            title: 'Table of 33KV Feeders',
            paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10)   
            actions: {
                listAction: 'KV33FeederController?action=list',
                createAction:'KV33FeederController?action=create',
                updateAction: 'KV33FeederController?action=update',
                deleteAction: 'KV33FeederController?action=delete'
            },
            fields:{
                id: {
                  title: '33KV Feeder ID',
                  key: true,
                  list: false,
                  create:false,
                  update:true,
                  width: 'auto'
              },
              name: {
                title: '33KV Feeder Name',
                width: 'auto'
            },
            code: {
                title: '33KV Feeder Code',
                width: 'auto'
            },
            region: {
                title: '33KV Feeder Region',
                width: 'auto',
                options: ['INDORE','UJJAIN']
            },
            circle: {
                title: '33KV Feeder Circle',
                width: 'auto',
                list:true,
                edit:true,
                dependsOn:'region',
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
                title: '33KV Feeder Division',
                width: 'auto',
                dependsOn:'circle',
                options: function(data){
                    if(data.source=='edit'||data.source=='create'||data.source=='update'){
                        return 'GetDivisions?source=jtable&circleName='+data.dependedValues.circle;
                    }else if(data.source=='list'){
                        //return ['INDORECITY','INDOREO&M','BARWANI','KHANDWA','BURHANPUR','KHARGONE','DHAR','JHABUA','SHAJAPUR','NEEMUCH','MANDSAUR','DEWAS','RATLAM','UJJAIN','AGAR'];
                        return [data.record.division];
                    }
                }
            },
            ehvssID: {
                title: 'EHVSS Name',
                width: 'auto',
                dependsOn: 'region',
                options: function(data){
                    if(data.source=='edit'||data.source=='create'||data.source=='update'){
                        return 'GetEhvssNames?source=jtable&regionName='+data.dependedValues.region;
                    }else if(data.source=='list'){
                     return [data.record.ehvssID];
                 }
             }
         }
     },
     deleteConfirmation: function(data) {
        data.deleteConfirmMessage = 'Are you sure to delete 33KVFeeder: ' + data.record.name + '?';
    }
});
$('#KV33TableContainer').jtable('load');
$("#region").change();
$('#regionButton').click(function (e) {
    $('#KV33TableContainer').jtable('load', {
        region: $('#region').val()
    });
});

$('#circleButton').click(function (e) {
    $('#KV33TableContainer').jtable('load', {
        circle: $('#circle').val()
    });
});

$('#divisionButton').click(function (e) {
    $('#KV33TableContainer').jtable('load', {
        division: $('#division').val()
    });
});

$('#ehvssButton').click(function (e) {
    $('#KV33TableContainer').jtable('load', {
        ehvssID: $('#ehvss').val()
    });
});

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
        <h1>All 33 KV Feeders </h1>
        <br/>
        <label>
            <span>Select Region</span>
            <select name="region" id="region">
                <option selected='true'>ALL</option>
                <option >INDORE</option>
                <option >UJJAIN</option>
            </select>
            <button id="regionButton">Load feeders by Region</button>
        </label>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <label>
            <span>Select Circle</span>
            <select name="circle" id="circle">
                <option >Select circle</option>
            </select>
            <button id="circleButton">Load feeders by Circle</button>
        </label>
        <br/>
        <br/> 
        <label>
            <span>Select Division</span>
            <select name="division" id="division">
                <option >Select division</option>
            </select>
            <button id="divisionButton">Load 11KVFeeders by Division</button>
        </label>
        <br/>
        <br/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;
        <label>
            <span>Select EHVSS Name</span>
            <select name="ehvss" id="ehvss">
                <option >Select ehvss</option>
            </select>
            <button id="ehvssButton">Load feeders by EHVSS</button>
        </label>
        <br/>
        <br/>
        <div id="KV33TableContainer"></div>
    </div>
    <br/>
    <br/>
    <br/>
    <%@ include file="MasterPageBottomSection.jsp" %>
</body>
</html>
