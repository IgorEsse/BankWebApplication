package ru.esse.bankapplication.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.esse.bankapplication.beans.Account;
import ru.esse.bankapplication.beans.Client;
import ru.esse.bankapplication.utils.DBUtils;
import ru.esse.bankapplication.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/createUser" })
public class СreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public СreateUserServlet() {
        super();
    }
 
    // Отобразить страницу создания клиента.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createUserView.jsp");
        dispatcher.forward(request, response);
    }
 
    // Когда пользователь вводит информацию продукта, и нажимает Submit.
    // Этот метод будет вызван.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//HttpSession session = request.getSession();
        Connection conn = MyUtils.getStoredConnection(request);
 
        //Client loginedClient = MyUtils.getUsername(session);
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String birth_date = (String) request.getParameter("birth_date");
        String name = (String) request.getParameter("name");
        String surname = (String) request.getParameter("surname");
        /*
        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
        }*/
        Client client = new Client(username, password, birth_date, name, surname);
 
        String errorString = null;
 
        /*Логин должен начинаться с латинской буквы, 
        может состоять из латинских букв, цифр, точек, минуса, 
        а заканчиваться буквой или цифрой
        */
        
        String regex = "^[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])$";
 
        if (username == null || !username.matches(regex)) {
            errorString = "Логин некорректен!";
        }
 
        if (errorString == null) {
            try {
                DBUtils.insertUser(conn, client);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
 
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("client", client);
 
        // Если имеется ошибка forward (перенаправления) к странице 'edit'.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createAccountView.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect (перенаправить) к странице с авторизацией.
        else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
 
}

