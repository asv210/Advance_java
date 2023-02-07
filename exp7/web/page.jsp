<%@ page import="java.io.*,java.util.*,java.sql.*"%> 
<%@ page import="javax.servlet.http.*,javax.servlet.*" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html> 
    <head> 
        <title>sql :update Tag</title> 
    </head> 
    <body>
        <sql:setDataSource var="db" driver="org.apache.derby.jdbc.ClientDriver" 
                           url="jdbc:derby://localhost:1527/exp7" 
                           user="exp7" password="exp7"/> 
        <%--<c:set var="Userid" value= "${sessionScope.akshar}" />--%>
        <%
            if (request.getMethod().equalsIgnoreCase("POST")) {
        %>
        <c:set var="pass" value="${param.pass}"/>
        <c:set var="cpass" value="${param.cpass}"/>
        <c:set var="fname" value="${param.fname}"/>
        <c:set var="rno" value="${param.rno}"/>
        <c:set var="sem" value="${param.sem}"/>
        <c:set var="email" value="${param.email}"/>
        <c:set var="cnumber" value="${param.cnumber}"/>
        <sql:update dataSource="${db}" var="rs1"> 
            UPDATE Registration SET password = ? ,cpassword = ? ,fname = ?,semester = ?,rollno = ?,email = ?,cnumber = ? WHERE USERID=?
            <sql:param value="${pass}" />
            <sql:param value="${cpass}" />
            <sql:param value="${fname}" />
            <sql:param value="${sem}" />
            <sql:param value="${rno}" />
            <sql:param value="${email}" />
            <sql:param value="${cnumber}" />
            <sql:param value="${Userid}" />
        </sql:update>
        <c:if test="${rs1!=0}">
            <c:out value="Your Profile Data Updated"/>
        </c:if>
        <c:if test="${rs1==0}">
            <c:out value="Your Profile Data Not Updated"/>
        </c:if>
        
        <sql:query dataSource="${db}" var="rs"> 
            SELECT * from Registration WHERE USERID=?
            <sql:param value="${param.uid}" /> 
        </sql:query>
        <form action="page.jsp" method="Post">
            <table>
                <c:forEach var="table" items="${rs.rows}">
                    <tr>
                        <td>USERID:</td>
                        <td><input type="text" name="uid" value="${table.userid}" readonly ></td>
                    </tr>
                    <tr>
                        <td>PASSWORD:</td>
                        <td><input type="password" name="pass" value="${table.password}"></td>
                    </tr>
                    <tr>
                        <td>CONFORM PASSWORD:</td>
                        <td><input type="password" name="cpass" value="${table.cpassword}"></td>
                    </tr>
                    <tr>
                        <td>Full Name:</td>
                        <td><input type="text" name="fname" value="${table.fname}"></td>
                    </tr>
                    <tr>
                        <td>SEMESTER:</td>
                        <td>
                            <input type="text" name="sem" value="${table.semester}">
                        </td>
                    </tr>
                    <tr>
                        <td>ROLLNO:</td>
                        <td><input type="text" name="rno" value="${table.rollno}"></td>
                    </tr>
                    <tr>
                        <td>EMAIL:</td>
                        <td><input type="text" name="email" value="${table.email}"></td>
                    </tr>
                    <tr>
                        <td>CONTACT NO:</td>
                        <td><input type="text" name="cnumber" value="${table.cnumber}"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value = "Save Changes"></td>
                    </tr>
                </c:forEach>
            </table>
        </form>

    </body>
</html>
