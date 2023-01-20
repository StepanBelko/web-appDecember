package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.dao.impl.RolesDAO;
import by.itstep.stpnbelko.homework.dao.impl.UsersDAO;
import by.itstep.stpnbelko.homework.model.Role;
import by.itstep.stpnbelko.homework.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;


public class RolesServlet extends HttpServlet {

    RolesDAO dao = new RolesDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Role> roles = null;

        if (req.getParameter("userId") != null) {
            User user = new UsersDAO().getById(Integer.parseInt(req.getParameter("userId")));
            roles = dao.getUserRoles(user);
        } else {
            roles = dao.getAll();
        }

        req.setAttribute("roles", roles);
        req.getRequestDispatcher("jsp/roles.jsp").forward(req, resp);
    }
}
