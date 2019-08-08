<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Регистрация клиента</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
       
      <h3>Зарегистрировать клиента</h3>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/createUser">
         <table border="0">
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
               <td colspan="2">                   
                   <input type="submit" value="Отправить" />
                   <a href="login">Отмена</a>
               </td>
            </tr>
         </table>
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
       
   </body>
</html>