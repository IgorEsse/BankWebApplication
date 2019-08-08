<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Авторизация</title>
   </head>
   <body>
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Страница авторизации</h3>
      <p style="color: red;">${errorString}</p>
 
 
      <form method="POST" action="${pageContext.request.contextPath}/login">
         <table border="0">
            <tr>
               <td>Логин</td>
               <td><input type="text" name="username" value= "${user.username}" /> </td>
            </tr>
            <tr>
               <td>Пароль</td>
               <td><input type="text" name="password" value= "${user.password}" /> </td>
            </tr>
            <tr>
               <td>Запомнить меня</td>
               <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Отправить" />
                  <a href="${pageContext.request.contextPath}/">Отмена</a>
               </td>
            </tr>
            <tr>
               <td>
                  <a href="createUser">Зарегистрироваться</a>
               </td>
            </tr>
         </table>
      </form>
 
      <p style="color:blue;">Логин: test, Пароль: test</p>
 
      <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>