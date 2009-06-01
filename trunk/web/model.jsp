<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@page import="DBViewer.models.*" %>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@page import="DBViewer.objects.model.*" %>
<%@page import="DBViewer.objects.view.*" %>
<%@page import="DBViewer.controllers.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DB-SVG | Data Model Explorer</title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/jquery.svg.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/jq-ui.css" />
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.svg.js"></script>
        <script type="text/javascript" src="js/jquery.svganim.js"></script>
<script type="text/javascript">
var svg;
var onTable = false;
var currentTable;
var currentDragx;
var currentDragy;

<%
    String dbi = request.getParameter("dbi");
    String pageid = request.getParameter("page");
    if (pageid==null) {
        pageid = (String)request.getSession().getAttribute("pageid");
    }
    
    SortedSchema currentSchema = new SortedSchema();
    if (request.getSession().getAttribute("CurrentSchema")==null || request.getSession().getAttribute("CurrentSchema").getClass()!=currentSchema.getClass()) {
        request.getSession().setAttribute("CurrentSchema",currentSchema);
    } else {
        currentSchema = (SortedSchema)request.getSession().getAttribute("CurrentSchema");
    }
    SchemaController sc = SchemaController.getInstance();
    SchemaPage sPage = sc.prepareSchema(currentSchema,request.getSession(), dbi, pageid);
%>

  var transx = (-1*<%= sPage.getTransx() %>)-30;
  var transy = (-1*<%= sPage.getTransy() %>)-100;

$(function() {

      svg = $('#svgwindow').svg({loadURL: 'schema.svg.jsp'});

      svg.mousemove(function (e){
           if (onTable) {
              var ex = e.clientX+f_scrollLeft();
              var ey = e.clientY+f_scrollTop();

              currentDragx = (ex+transx);
              currentDragy = (ey+transy);
              $('#coord').html('x:'+(currentDragx)+' y:'+(currentDragy)+' '+f_scrollLeft()+','+f_scrollTop()+' '+window.pageYOffset+'.'+document.documentElement.scrollTop+'.'+document.body.scrollTop);
              currentTable.setAttribute('transform','translate('+(currentDragx)+','+(currentDragy)+')');
          } 
      });
      $('#saver').click(function(){
           $.post("ajaxFunctions.jsp", { "save": "true" },
           function(data){
             alert(data);
           });
           return false;
      });

      $('#sorter').click(function(){
           $.post("ajaxFunctions.jsp", { "refresh": "true" },
           function(data){
            // alert("Data Loaded: " + data);
            location.reload(true);
           });
           return false;
      });
});

    function tableDown(evt) {
        currentTable = evt.parentNode;
        onTable = true;
    }
    
    function tableUp(evt) {
        onTable = false;
        $.post("ajaxFunctions.jsp", { "name": evt.id, "x": currentDragx, "y": currentDragy },
           function(data){
            // alert("Data Loaded: " + data);
            location.reload(true);
           });
    }

    function tableMove(evt) {
    }

    function isWebKit(){
        return RegExp(" AppleWebKit/").test(navigator.userAgent);
    }
    function f_scrollLeft() {
          return f_filterResults (
                window.pageXOffset ? window.pageXOffset : 0,
                document.documentElement ? document.documentElement.scrollLeft : 0,
                document.body ? document.body.scrollLeft : 0
          );
    }
    function f_scrollTop() {
          return f_filterResults (
                window.pageYOffset ? window.pageYOffset : 0,
                document.documentElement ? document.documentElement.scrollTop : 0,
                document.body ? document.body.scrollTop : 0
          );
    }
    function f_filterResults(n_win, n_docel, n_body) {
          var n_result = n_win ? n_win : 0;
          if (n_docel && (!n_result || (n_result > n_docel)))
                n_result = n_docel;
          return n_body && (!n_result || (n_result > n_body)) ? n_body : n_result;
    }



</script>
    </head>
    <body>
    <div class="titleHeader" >
            <div class="titleLeft"></div>
            <div class="titleRight"></div>
            <div class="titleBody">
            <h1>DB-SVG Data Model Explorer</h1>
        </div>
    </div>
    <div id="content" class="modelContentBox">
        <div class="tablayer">
<a href="#"><div class="tab">Page 2</div></a>
<a href="#"><div class="tab">Page 1</div></a>
        </div>
        <div class="menu">
            <a href="Menu">Back to Menu</a><a href="setup.jsp?dbi=<%= dbi %>">Setup</a><a id="sorter" href="#ajaxFunctions.jsp?refresh=true">Re-sort</a><a id="saver" href="#ajaxFunctions.jsp?save=true">Save</a><a href="schema.svg.jsp" target="_blank">Download</a><span class="coord" id="coord">x,y</span>
        </div>
        <div class="svgwrapper" style="width:<%= sPage.getWidth() %>px;height:<%= sPage.getHeight() %>px;">
            <div id="svgwindow" class="svgwindow"></div>
        </div>
    </div>
    <div style="padding-left:200px; display:none;"><a href="javascript:alert(f_scrollLeft()+','+f_scrollTop()+' '+window.pageYOffset+'.'+document.documentElement.scrollTop+'.'+document.body.scrollTop);">get the scrolling offsets</a></div>
    </body>
</html>
