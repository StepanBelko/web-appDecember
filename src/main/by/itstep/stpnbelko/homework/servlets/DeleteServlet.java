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

public class DeleteServlet extends HttpServlet {

    private UsersDAO dao = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet DeleteServlet");
        HttpSession session = req.getSession();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/deleteWindow.jsp");
        int id = Integer.parseInt(req.getParameter("deleteUserId"));
        System.out.println("User for delete : " + id);
        User userToDelete = dao.getById(id);
        System.out.println(userToDelete);
        req.setAttribute("userToDelete", userToDelete);

        if (dao.delete(id)) {
            requestDispatcher.forward(req, resp);
        }         else {
            requestDispatcher = req.getRequestDispatcher("login");
            requestDispatcher.forward(req, resp);
        }
    }
}
