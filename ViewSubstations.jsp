<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View Substations</title>
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
            $.get('GetCircles', {
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
            $.get('GetKV33FeederNames',{
                circleName : circle
            },function(response) {
                var select = $('#kv33FeederID');
                select.find('option').remove();
                $('<option>').val("-1").text("select 33KV Feeder").appendTo(select);
                $.each(response, function(index, value) {
                    $('<option>').val(value.id).text(value.name).appendTo(select);
                });
            });
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
        $('#SubstationTableContainer').jtable({
            title: 'Table of Substations',
            paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10)   
            actions: {
                listAction: 'SubstationController?action=list',
                createAction:'SubstationController?action=create',
                updateAction: 'SubstationController?action=update',
                deleteAction: 'SubstationController?action=delete'
            },
            fields:{
                id: {
                  title: '33/11 Substation ID',
                  key: true,
                  list: false,
                  create:false,
                  update:true,
                  width: 'auto'
              },
              code: {
                title: 'Code',
                width: 'auto'
            },
            name: {
                title: '33/11 Substation Name',
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
            kv33FeederID: {
                title: '33KV Feeder Name',
                width: 'auto',
                dependsOn: 'circle',
                options: function(data){
                    if(data.source=='edit'||data.source=='create'||data.source=='update'){
                        return 'GetKV33FeederNames?source=jtable&circleName='+data.dependedValues.circle;
                    }else if(data.source=='list'){
                     return [data.record.kv33FeederID];
                 }
             }
         }
     },
     deleteConfirmation: function(data) {
        data.deleteConfirmMessage = 'Are you sure to delete Substation: ' + data.record.name + '?';
    }
});
$('#SubstationTableContainer').jtable('load');
$("#region").change();
$('#regionButton').click(function (e) {
    $('#SubstationTableContainer').jtable('load', {
        region: $('#region').val()
    });
});

$('#circleButton').click(function (e) {
    $('#SubstationTableContainer').jtable('load', {
        circle: $('#circle').val()
    });
});

$('#divisionButton').click(function (e) {
    $('#SubstationTableContainer').jtable('load', {
        division: $('#division').val()
    });
});

$('#kv33FeederButton').click(function (e) {
    $('#SubstationTableContainer').jtable('load', {
        kv33FeederID: $('#kv33FeederID').val()
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
        <h1>All Substations </h1>
        <br/>
        <label>
            <span>Select Region</span>
            <select name="region" id="region">
                <option selected='true'>ALL</option>
                <option >INDORE</option>
                <option >UJJAIN</option>
            </select>
            <button id="regionButton">Load substations by Region</button>
        </label>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <label>
            <span>Select Circle</span>
            <select name="circle" id="circle">
                <option >Select circle</option>
            </select>
            <button id="circleButton">Load substations by Circle</button>
        </label>
        <br/>
        <br/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <label>
            <span>Select Division</span>
            <select name="division" id="division">
                <option >Select division</option>
            </select>
            <button id="divisionButton">Load substations by Division</button>
        </label>
        <br/>
        <br/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <label>
            <span>Select 33KVFeeder Name</span>
            <select name="kv33FeederID" id="kv33FeederID">
                <option >Select 33KV Feeder Name</option>
            </select>
            <button id="kv33FeederButton">Load substations by 33KVName</button>
        </label>
        <br/>
        <br/>
        <div id="SubstationTableContainer"></div>
    </div>
    <br/>
    <br/>
    <br/>
    <%@ include file="MasterPageBottomSection.jsp" %>
</body>
</html>
