package ru.esse.bankapplication.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import ru.esse.bankapplication.beans.Client;
import ru.esse.bankapplication.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public UserInfoServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
 
        // ѕроверить, вошел ли пользователь в систему (login) или нет.
        Client loginedClient = MyUtils.getUsername(session);
 
        // ≈сли еще не вошел в систему (login).
        if (loginedClient == null) {
            // Redirect (ѕеренаправить) к странице login.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // —охранить информацию в request attribute перед тем как forward (перенаправить).
        request.setAttribute("user", loginedClient);
 
        // ≈сли пользователь уже вошел в систему (login), то forward (перенаправить) к странице
        // /WEB-INF/views/userInfoView.jsp
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
