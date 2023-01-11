package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.dao.impl.UsersDAO;
import by.itstep.stpnbelko.homework.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class ResetPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!Reset servlet doGet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("resetPage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!Reset servlet doPost");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resetPage.jsp");
        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String oldPass = req.getParameter("oldPassword");
        String newPass = req.getParameter("newPassword");
        String newPass2 = req.getParameter("newPassword2");

        User user = new UsersDAO().getByEmail(email);

        if (user != null) {
            if (oldPass.equals(user.getPassword())) {
                if (newPass.equals(newPass2)) {
                    if (new UsersDAO().changePassWord(email, newPass)) {
                        resp.getWriter().println("Password successfully changed");
                        requestDispatcher = req.getRequestDispatcher("loginPage.html");
                    } else {
                        resp.getWriter().println("Something wrong");
                    }
                } else {
                    resp.getWriter().println("New passwords do not match");
                }
            } else {
                resp.getWriter().println("Old password is incorrect");
            }
        } else {
            resp.getWriter().println("User is not exist");
        }
        
        requestDispatcher.include(req, resp);
    }
}
