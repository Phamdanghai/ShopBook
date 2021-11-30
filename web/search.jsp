<%-- 
    Document   : search
    Created on : Oct 4, 2021, 1:06:18 PM
    Author     : haiph
--%>

<%--<%@page import="haipd.login.LoginDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color ="red" >
        Wellcome, ${sessionScope.USERNAME.fullname}
        </font>
        <form action="processSearchLastname">
            Search value <input type="text" name="txtSearchValue"
                                value="" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Fullname</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="processUpdateAccount">
                            <tr>
                                <td>
                                    ${counter.count}
                                    .</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />

                                </td>
                                <td>
                                    ${dto.fullname}
                                </td>
                                <td>
                                    <input type="checkbox" name="checkRole" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="processDeleteAccount">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}" onclick="return confirm('Are you sure delete?')">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" onclick="return confirm('Are you sure update?')"/>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                no record is matched!!!!
            </h2>
        </c:if>
    </c:if>
    <%--<%//1.
        Cookie[] cookie = request.getCookies();
        if (cookie != null) {
            //2.get last cookie
            Cookie lastcookie = cookie[cookie.length - 1];

        %>
        <font color ="red">
        Welcome, <%= lastcookie.getName() %>
        </font>
        <%
            }//end if
%>
        <form action="DispatcherController">
            Search value <input type="text" name="txtSearchValue"
                                value="<%= request.getParameter("txtSearchValue")%>" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<LoginDTO> result
                        = (List<LoginDTO>) request.getAttribute("SEARCHRESULT");
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Fullname</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (LoginDTO dto : result) {
                        String urlRewrting = "DispatcherController"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatcherController">
                <tr>
                    <td>
                        <%= ++count%>
                        .</td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" 
                               value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getFullname()%>
                    </td>
                    <td>
                        <input type="checkbox" name="checkRole" value="ON"  
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }
                               %>

                               />
                    </td>
                    <td>
                        <a href="<%= urlRewrting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue%>" />
                    </td>
                </tr>
            </form>
            <%
                }//end for tra
            %>
        </tbody>
    </table>

    <%
    } else {
    %>
    <h2>
        No record is matched!!!
    </h2>
    <%
            }
        }//end if saerch value had inputted
    %>--%>

    <form action="ProcessLogout">
        <input type="submit" value="Logout" name="btAction" />
    </form>
</body>
</html>
