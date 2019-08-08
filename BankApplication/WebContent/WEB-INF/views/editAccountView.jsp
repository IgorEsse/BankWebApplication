<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Обновление счета</title>
   </head>
   <body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Обновление счета</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty account}">
         <form method="POST" action="${pageContext.request.contextPath}/editAccount">
            <table border="0">
               <tr>
                  <td>Id</td>
                  <td><input type="text" readonly name="id" value="${account.id}" /></td>
               </tr>
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
  				  </select />
                  </td>
               </tr>
               <tr>
               <tr>
                  <td>Id клиента</td>
                  <td><input type="text" readonly name="client_id" value="${account.client_id}" /></td>
               </tr>
                  <td colspan = "2">
                      <input type="submit" value="Отправить" />
                      <a href="${pageContext.request.contextPath}/accountList">Отмена</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body>
</html>