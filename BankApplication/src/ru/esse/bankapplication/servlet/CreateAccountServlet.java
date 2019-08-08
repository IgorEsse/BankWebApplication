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
 
@WebServlet(urlPatterns = { "/createAccount" })
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateAccountServlet() {
        super();
    }
 
    // Отобразить страницу создания счета.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createAccountView.jsp");
        dispatcher.forward(request, response);
    }
 
    // Когда пользователь вводит информацию продукта, и нажимает Submit.
    // Этот метод будет вызван.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
        Connection conn = MyUtils.getStoredConnection(request);
 
        Client loginedClient = MyUtils.getUsername(session);
        String balance = (String) request.getParameter("balance");
        String open_date = (String) request.getParameter("open_date");
        String close_date = (String) request.getParameter("close_date");
        String status = (String) request.getParameter("status");
        String client_id = loginedClient.getId();
        /*
        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
        }*/
        Account account = new Account(balance, open_date, close_date, status, client_id);
 
        String errorString = null;
 
        // Балансом являются цифры [0-9]
        // Имеет минимум 1 цифру.
        String regex = "\\d+";
 
        if (balance == null || !balance.matches(regex)) {
            errorString = "Баланс некорректен!";
        }
 
        if (errorString == null) {
            try {
                DBUtils.insertAccount(conn, account);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
 
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("account", account);
 
        // Если имеется ошибка forward (перенаправления) к странице 'edit'.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createAccountView.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect (перенаправить) к странице со списком продуктов.
        else {
            response.sendRedirect(request.getContextPath() + "/accountList");
        }
    }
 
}
