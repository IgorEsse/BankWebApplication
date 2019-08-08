<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Список счетов</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Список счетов</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Баланс</th>
          <th>Дата открытия</th>
          <th>Дата закрытия</th>
          <th>Статус</th>
          <th>ID клиента</th>
       </tr>
       <c:forEach items="${accountList}" var="account" >
          <tr>
             <td>${account.id}</td>
             <td>${account.balance}</td>
             <td>${account.open_date}</td>
             <td>${account.close_date}</td>
             <td>${account.status}</td>
             <td>${account.client_id}</td>
             <td>
                <a href="editAccount?id=${account.id}">Обновить</a>
             </td>
             <td>
                <a href="deleteAccount?id=${account.id}">Удалить</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="createAccount" >Создать счет</a>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>