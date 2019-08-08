<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Информация о клиенте</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Привет: ${user.username}</h3>
 
    Логин: <b>${user.username}</b>
    <br />
    Имя: <b>${user.name }</b> 
    <br />
    Дата рождения: <b>${user.birth_date } </b>
    <br />
    <a href="editUser?username=${user.username}">Обновить</a>
    <br />
    <a href="deleteUser?id=${user.id}">Удалить</a>
    <br />
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>