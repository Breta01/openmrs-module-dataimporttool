<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
<%@ page autoFlush="true" buffer="1094kb"%>


<html>
<head>
    <openmrs:htmlInclude file="/moduleResources/dataimporttool/css/component.css"/>
    <openmrs:htmlInclude file="/moduleResources/dataimporttool/css/font-awesome.min.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><h1 align="center"> <spring:message code="dataimporttool.migrationProgress" /></h1></title>
</head>

<body>       
<h2 align="center">
<% for(int k = 1; k < 101; k+=1) { %>
    Result: <%= k %><br />
    <% int percent = k; %>
    <%= percent %>%
  
</h2>

<table width="60%" align="center"
        border="1" cellpadding="0" cellspacing="2">
    <TR>
        <% for (int i = 10; i <= percent; i += 10) { %>
            <td width="10%" bgcolor="#000080">&nbsp;</td>
        <% } %>
        <% for (int j = 100; j > percent; j -= 10) { %>
            <td width="10%">&nbsp;</TD>
        <% } %>
    </tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <% if ( percent < 100) { %>
                <spring:message code="dataimporttool.running" />
            <% } else { %>
                <% if ( percent == 100) { %>
                <%	Thread.sleep(10000); %>
      				<jsp:forward page="status.jsp" />
                <% } else { %>
                	<% Thread.sleep(10000); %>
                   <jsp:forward page="error.jsp"/>
                <% } %>
            <% } %>
        </td>
    </tr>
  </table>
  
<% } %>

</body>
</html>


<%@ include file="/WEB-INF/template/footer.jsp"%>
