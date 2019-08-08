package ru.esse.bankapplication.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
 
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

@WebServlet(urlPatterns = { "/accountList" })
public class AccountListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public AccountListServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
        Connection conn = MyUtils.getStoredConnection(request);
 
        // Проверить, вошел ли пользователь в систему (login) или нет.
        Client loginedClient = MyUtils.getUsername(session);
 
        // Если еще не вошел в систему (login).
        if (loginedClient == null) {
            // Redirect (Перенаправить) к странице login.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // Сохранить информацию в request attribute перед тем как forward (перенаправить).
        request.setAttribute("user", loginedClient);
 
        // Если пользователь уже вошел в систему (login), то forward (перенаправить) к странице
        // /WEB-INF/views/userInfoView.jsp
        
        String errorString = null;
        List<Account> list = null;
        try {
            list = DBUtils.queryAccount(conn, loginedClient.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("accountList", list);
         
        // Forward к /WEB-INF/views/accountListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/accountListView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}