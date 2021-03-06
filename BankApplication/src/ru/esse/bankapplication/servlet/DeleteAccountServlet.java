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
 
import ru.esse.bankapplication.utils.DBUtils;
import ru.esse.bankapplication.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/deleteAccount" })
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DeleteAccountServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("id");
 
        String errorString = null;
 
        try {
            DBUtils.deleteAccount(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } 
         
        // ���� ���������� ������, forward (�������������) � �������� ����������� ������.
        if (errorString != null) {
            // ��������� ���������� � request attribute ����� ��� ��� forward � views.
            request.setAttribute("errorString", errorString);
            // 
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/deleteAccountErrorView.jsp");
            dispatcher.forward(request, response);
        }
        // ���� ��� ������.
        // Redirect (�������������) � �������� �� ������� ���������.
        else {
            response.sendRedirect(request.getContextPath() + "/accountList");
        }
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}