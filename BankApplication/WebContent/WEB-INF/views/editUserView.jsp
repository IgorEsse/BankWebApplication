<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Обновление информации о клиенте</title>
   </head>
   <body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Обновление информации о клиенте</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty client}">
         <form method="POST" action="${pageContext.request.contextPath}/editUser">
            <table border="0">
               <tr>
                  <td>Id</td>
                  <td><input type="text" readonly name="id" value="${client.id}" /></td>
               </tr>
               <tr>
                  <td>Логин</td>
                  <td><input type="text" name="username" value="${client.username}" /></td>
               </tr>
               <tr>
                  <td>Пароль</td>
                  <td><input type="text" name="password" value="${client.password}" /></td>
               </tr>
               <tr>
                  <td>Дата рождения</td>
                  <td><input type="date" name="birth_date" value="${client.birth_date}" /></td>
               </tr>
               <tr>
                  <td>Имя</td>
                  <td><input type="text" name="name" value="${client.name}" /></td>
               </tr>
               <tr>
                  <td>Фамилия</td>
                  <td><input type="text" name="surname" value="${client.surname}" /></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Отправить" />
                      <a href="${pageContext.request.contextPath}/userInfo">Отмена</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body>
</html>