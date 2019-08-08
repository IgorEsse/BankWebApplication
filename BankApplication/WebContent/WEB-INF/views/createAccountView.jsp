<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Создать счет</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
       
      <h3>Создать счет</h3>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/createAccount">
         <table border="0">
            <tr>
               <td>Баланс</td>
               <td><input type="text" name="balance" value="${account.balance}" /></td>
            </tr>
            <tr>
               <td>Дата открытия</td>
               <td><input type="date" name="open_date" value="${account.open_date}" /></td>
            </tr>
            <tr>
               <td>Дата закрытия</td>
               <td><input type="date" name="close_date" value="${account.close_date}" /></td>
            </tr>
            <tr>
               <td>Статус</td>
               <td>
				  <select name="status">
				  <option selected disabled>Выберите статус</option>
  				  <option value="open">open</option>
  				  <option value="closed">closed</option>
  				  <option value="suspend">suspend</option>
  				  </select>
  			   </td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Отправить" />
                   <a href="accountList">Отмена</a>
               </td>
            </tr>
         </table>
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
       
   </body>
</html>