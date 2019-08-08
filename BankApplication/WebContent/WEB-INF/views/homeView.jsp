<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>Домашняя страница</title>
  </head>
  <body>
 
     <jsp:include page="_header.jsp"></jsp:include>
     <jsp:include page="_menu.jsp"></jsp:include>
    
      <h3>Домашняя страница</h3>
      
      Это веб-приложение для тестового задания по Java. <br><br>
      <b>Включает в себя следующие функции:</b>
      <ul>
         <li>Авторизация пользователей</li>
         <li>Хранение пользовательской информации в файлах cookie</li>
         <li>Отображение информации о клиенте</li>
         <li>Создание информации о клиенте</li>
         <li>Обновление информации о клиенте</li>
         <li>Удаление информации о клиенте</li>
         <li>Отображение списка клиентских счетов</li>
         <li>Создание клиентских счетов</li>
         <li>Обновление клиентских счетов</li>
         <li>Удаление клиентских счетов</li>
      </ul>
 
     <jsp:include page="_footer.jsp"></jsp:include>
 
  </body>
</html>