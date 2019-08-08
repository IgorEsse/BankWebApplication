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
 
import ru.esse.bankapplication.beans.Client;
import ru.esse.bankapplication.utils.DBUtils;
import ru.esse.bankapplication.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/editUser" })
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditUserServlet() {
        super();
    }
 
    // ���������� �������� �������������� ���������� � �������.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String username = (String) request.getParameter("username");
 
        Client client = null;
 
        String errorString = null;
 
        try {
        	client = DBUtils.findUser(conn, username);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // ������ �� �������.
        // ������ �� ���������� ��� �������������� (edit).
        // ������������� �� �������� � ���������� � �������.
        if (errorString != null && client == null) {
            response.sendRedirect(request.getServletPath() + "/userInfo");
            return;
        }
 
        // ��������� ���������� � request attribute ����� ��� ��� forward � views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("client", client);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // ����� ����, ��� ������������ �������������� ���������� � ����� �� Submit.
    // ������ ����� ����� ��������.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("id");
    	String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String name = (String) request.getParameter("name");
        String surname = (String) request.getParameter("surname");
        String birth_date = (String) request.getParameter("birth_date");
        /*float price = 0;
        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
        }*/
        Client client = new Client(id, username, password, birth_date, name, surname);
 
        String errorString = null;
 
        try {
            DBUtils.updateUser(conn, client);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // ��������� ���������� � request attribute ����� ��� ��� forward � views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("client", client);
 
        // ���� ������� ������, forward � �������� edit.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
            dispatcher.forward(request, response);
        }
        // ���� ��� ������.
        // Redirect � �������� �����������.
        else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
 
}
