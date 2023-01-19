package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.dao.impl.OfficesDAO;
import by.itstep.stpnbelko.homework.dao.impl.UsersDAO;
import by.itstep.stpnbelko.homework.model.Office;
import by.itstep.stpnbelko.homework.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itstep.stpnbelko.homework.util.EncryptDecrypt.encrypt;

public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("createNewUser.jsp");

        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("createNewUser.jsp");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pwd1 = req.getParameter("password");
        boolean is_active = Boolean.parseBoolean(req.getParameter("is_active"));
        System.out.println("IS ACTIVE : " + is_active);

//        User user = new UsersDAO().getByEmail(email);
//        System.out.println(new UsersDAO().getByEmail(email));

        User user = null;

        if (user == null) {
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(pwd1);
            user.setIs_active(is_active);
            user.setOffice_id(1);
            user.setOffice(new OfficesDAO().getById(user.getOffice_id()));
            System.out.println("CREATED USER " + user);
            new UsersDAO().insert(user);

            requestDispatcher = req.getRequestDispatcher("homePage");
            requestDispatcher.forward(req, resp);
        }

    }
}
