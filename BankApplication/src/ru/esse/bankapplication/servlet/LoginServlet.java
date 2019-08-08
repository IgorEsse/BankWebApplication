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
 
import ru.esse.bankapplication.beans.Client;
import ru.esse.bankapplication.utils.DBUtils;
import ru.esse.bankapplication.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
    }
 
    // �������� �������� Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Forward (�������������) � �������� /WEB-INF/views/loginView.jsp
        // (������������ �� ����� ����� �������� ������
        // � ��������� JSP ������������� � ����� WEB-INF).
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
        dispatcher.forward(request, response);
 
    }
 
    // ����� ������������ ������ userName & password, � �������� Submit.
    // ���� ����� ����� ��������.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
 
        Client client = null;
        boolean hasError = false;
        String errorString = null;
 
        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // ����� client � DB.
            	client = DBUtils.findUser(conn, username, password);
 
                if (client == null) {
                    hasError = true;
                    errorString = "Login or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // � ������, ���� ���� ������,
        // forward (�������������) � /WEB-INF/views/login.jsp
        if (hasError) {
        	client = new Client();
        	client.setUsername(username);
        	client.setPassword(password);
 
            // ��������� ���������� � request attribute ����� forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("client", client);
 
            // Forward (�������������) � �������� /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }
        // � ������, ���� ��� ������.
        // ��������� ���������� ������������ � Session.
        // � ������������� � �������� userInfo.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeUsername(session, client);
 
            // ���� ������������ �������� ������� "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, client);
            }
            // ��������, ������� Cookie
            else {
                MyUtils.deleteUserCookie(response);
            }
 
            // Redirect (�������������) �� �������� /userInfo.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
 
}