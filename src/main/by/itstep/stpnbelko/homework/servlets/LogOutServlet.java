package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.util.ServletUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet(name = "LogOut", urlPatterns = "logOut")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (ServletUtils.getSessionUser(req) != null)




//        HttpSession session = req.getSession();
//        System.out.println(session.getId() + "session is active");
    }
}
