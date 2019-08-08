package ru.esse.bankapplication.utils;

import java.sql.Connection;
 
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import ru.esse.bankapplication.beans.Client;
 
public class MyUtils {
 
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
 
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
 
    // Сохранить Connection в attribute в request.
    // Данная информация хранения существует только во время запроса (request)
    // до тех пор, пока данные возвращаются приложению пользователя.
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
 
    // Получить объект Connection сохраненный в attribute в request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }
 
    // Сохранить информацию пользователя, который вошел в систему (username) в Session.
    public static void storeUsername(HttpSession session, Client username) {
        // В JSP можно получить доступ через ${username}
        session.setAttribute("username", username);
    }
 
    // Получить информацию пользователя, сохраненная в Session.
    public static Client getUsername(HttpSession session) {
    	Client username = (Client) session.getAttribute("username");
        return username;
    }
 
    // Сохранить информацию пользователя в Cookie.
    public static void storeUserCookie(HttpServletResponse response, Client user) {
        System.out.println("Store user cookie");
        Cookie cookieUsername = new Cookie(ATT_NAME_USER_NAME, user.getUsername());
        // 1 день (Конвертированный в секунды)
        cookieUsername.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUsername);
    }
 
    public static String getUsernameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    // Удалить Cookie пользователя
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUsername = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 секунд. (Данный Cookie будет сразу недействителен)
        cookieUsername.setMaxAge(0);
        response.addCookie(cookieUsername);
    }
 
}