<%-- 
    Document   : Shopping
    Created on : Nov 2, 2021, 11:51:08 PM
    Author     : haiph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>List Book</h1>
        <c:set var="listBook" value="${requestScope.LIST}"/>
        <c:if test="${not empty listBook}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Book ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Author</th>
                        <th>Add to car</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${listBook}" varStatus="counter">
                    <form action="processAddBookToCart">
                        <tr>
                            <td> ${counter.count}</td>
                            <td>
                                ${item.bookid}
                            </td>
                            <td>
                                ${item.bookname}
                                <input type="hidden" name="txtNameBook" value="${item.bookname}" />
                            </td>
                            <td>
                                ${item.price}
                            </td>
                            <td>
                                ${item.author}
                            </td>
                            <td>
                                <input type="submit" value="Add to your cart" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>

            </tbody>
        </table>

    </c:if>
    <c:if test="${empty listBook}">
        <h2>
            No Book
        </h2>
    </c:if>
    <br>
    <a href="processViewCart">View your cart</a>
</body>
</html>