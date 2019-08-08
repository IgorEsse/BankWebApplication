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
 
import ru.esse.bankapplication.beans.Account;
import ru.esse.bankapplication.utils.DBUtils;
import ru.esse.bankapplication.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/editAccount" })
public class EditAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditAccountServlet() {
        super();
    }
 
    // Отобразить страницу редактирования счета.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("id");
 
        Account account = null;
 
        String errorString = null;
 
        try {
        	account = DBUtils.findAccount(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // Ошибки не имеются.
        // Счет не существует для редактирования (edit).
        // Перенаправить на страницу со списком счетов.
        if (errorString != null && account == null) {
            response.sendRedirect(request.getServletPath() + "/AccountList");
            return;
        }
 
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("account", account);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editAccountView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // После того, как пользователь отредактировал информацию счета и нажал на Submit.
    // Данный метод будет выполнен.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("id");
        String balance = (String) request.getParameter("balance");
        String open_date = (String) request.getParameter("open_date");
        String close_date = (String) request.getParameter("close_date");
        String status = (String) request.getParameter("status");
        String client_id = (String) request.getParameter("client_id");
        /*float price = 0;
        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
        }*/
        Account account = new Account(id, balance, open_date, close_date, status, client_id);
 
        String errorString = null;
 
        try {
            DBUtils.updateAccount(conn, account);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("account", account);
 
        // Если имеется ошибка, forward к странице edit.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editAccountView.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect к странице со списком счетов.
        else {
            response.sendRedirect(request.getContextPath() + "/accountList");
        }
    }
 
}
