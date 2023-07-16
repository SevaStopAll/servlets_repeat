<%--
  Created by IntelliJ IDEA.
  User: School_Laptop  3
  Date: 16.07.2023
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div>
        <span>CONTENT. Русский</span>
        <p>Size : ${requestScope.flights.size()}</p>
        <p>Id : ${requestScope.flights[1].id}</p>
        <p>Id2 : ${sessionScope.flightsMap[1]}</p>
        <p>JSESSION id : ${cookie["JSESSIONID"].value}, unique identifier</p>
        <p>Param id : ${param.id}</p>
        <p>Param id : ${param.test}</p>
    </div>
    <%@ include file="footer.jsp"%>
</body>
</html>
