package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.dao.impl.UsersDAO;
import by.itstep.stpnbelko.homework.model.User;
import by.itstep.stpnbelko.homework.util.ServletUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static by.itstep.stpnbelko.homework.util.EncryptDecrypt.encrypt;


public class LogInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!Login servlet doGet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/loginPage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!Login servlet doPost");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/loginPage.jsp");
        resp.setContentType("text/html");

        String password = encrypt(req.getParameter("password"));
        String email = req.getParameter("email").trim();

        User user = new UsersDAO().getByEmail(email);

        if (user == null) {
            resp.getWriter().println("<b> User does not exist </b >");
        } else if (password.equals(user.getPassword()) && !user.is_active()) {
            resp.getWriter().println("<b>User is not activated. Check your email to complete the registration</b>");
        } else if (password.equals(user.getPassword()) && user.is_active()) {

            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(360);
            ServletUtils.saveSessionUser(req, user);
            
            requestDispatcher = req.getRequestDispatcher("homePage");
            requestDispatcher.forward(req, resp);
            return;
        } else {
            resp.getWriter().println("<b> Wrong email or password </b >");
        }
            requestDispatcher.include(req, resp);
    }
}

