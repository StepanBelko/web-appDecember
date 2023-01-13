package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.dao.impl.UsersDAO;
import by.itstep.stpnbelko.homework.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;


public class HomePageServlet extends HttpServlet {

    private UsersDAO dao = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!HomePage servlet doGet");
        HttpSession currentSession = req.getSession();
        User user = (User) currentSession.getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("login").forward(req, resp);
        } else {
            doPost(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!HomePage servlet doPost");
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user"); //берём пользователя из сессии
        System.out.println("User in session : " + user.getName());

        Set<User> usersSet = dao.getAll();  //список всех users для передачи на домашнюю страницу
        req.setAttribute("users", usersSet);
        req.setAttribute("userInSession", user);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/userHomePage.jsp");
        resp.setContentType("text/html");
        requestDispatcher.forward(req, resp);
    }

}
