package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.dao.impl.OfficesDAO;
import by.itstep.stpnbelko.homework.dao.impl.RolesDAO;
import by.itstep.stpnbelko.homework.dao.impl.UsersDAO;
import by.itstep.stpnbelko.homework.model.Role;
import by.itstep.stpnbelko.homework.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import static by.itstep.stpnbelko.homework.util.EncryptDecrypt.decrypt;
import static by.itstep.stpnbelko.homework.util.EncryptDecrypt.encrypt;


public class HomePageServlet extends HttpServlet {

    private UsersDAO dao = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!HomePage servlet doGet");
        HttpSession currentSession = req.getSession();
        User user = (User) currentSession.getAttribute("user");

        String action = req.getParameter("action"); // Crt, Upd, Del  or  no parameter

        Set<User> usersSet = dao.getAll();  //список всех users для передачи на домашнюю страницу
        req.setAttribute("users", usersSet);

        if (action != null) {
            switch (action) {
                case "Crt":

                    req.setAttribute("offices", new OfficesDAO().getAll());
                    req.setAttribute("roles", new RolesDAO().getAll());
                    System.out.println("Create branch");
                    req.getRequestDispatcher("jsp/createUser.jsp").forward(req, resp);
                    return;

                case "Upd":

                    System.out.println("Update branch");
                    System.out.println("userId from parameter = " + req.getParameter("userId"));
                    User updatedUser = dao.getById(Integer.parseInt(req.getParameter("userId")));
                    updatedUser.setPassword(decrypt(updatedUser.getPassword()));
                    req.setAttribute("updatedUser", updatedUser);
                    System.out.println("Updated user : " + updatedUser);

                    req.setAttribute("offices", new OfficesDAO().getAll());
                    req.setAttribute("roles", new RolesDAO().getAll());
                    req.getRequestDispatcher("jsp/update.jsp").forward(req, resp);
                    return;

                case "Del":
                    System.out.println("Delete branch");
                    System.out.println("userId from parameter = " + req.getParameter("userId"));
                    User userToDelete = dao.getById(Integer.parseInt(req.getParameter("userId")));
                    req.setAttribute("userToDelete", userToDelete);
                    System.out.println("User to delete user : " + userToDelete);

                    req.getRequestDispatcher("jsp/confirmation.jsp").forward(req, resp);
                    return;
            }
        }

        req.getRequestDispatcher("jsp/userHomePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!HomePage servlet doPost");
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user"); //берём пользователя из сессии

        String action = req.getParameter("action");

        System.out.println("Action = " + action);


//        Создание
        if (action != null && action.equals("Crt")) {
            User newUser = new User();

            String[] roles = req.getParameterValues("role_id");

            Set<Role> roleSet = new LinkedHashSet<>();

            if (roles != null) {
                RolesDAO rolesDAO = new RolesDAO();
                for (int i = 0; i < roles.length; i++) {
                    roleSet.add(rolesDAO.getById(Integer.parseInt(roles[i])));
                }
            }

            int officeId = Integer.parseInt(req.getParameter("office_id"));
            newUser.setName(req.getParameter("name"));
            newUser.setEmail(req.getParameter("email"));
            newUser.setPassword(encrypt(req.getParameter("password")));
            newUser.setOffice(new OfficesDAO().getById(officeId));
            newUser.setIs_active(Boolean.parseBoolean(req.getParameter("is_active")));
            newUser.setRole(roleSet);

            dao.insert(newUser);
//            newUser = dao.getByEmail(newUser.getEmail());
//            костыль!!! приходится так делать, чтобы взять сгенерированный userId,
//            ибо пока мы не вставили в таблицу users пользователя мы не можем
//            вставлять данные в таблицу users_roles



//            Апдейт
        } else if (action != null && action.equals("Upd")) {
            String[] roles = req.getParameterValues("role_id");
            Set<Role> roleSet = new LinkedHashSet<>();
            int officeId = Integer.parseInt(req.getParameter("office_id"));

            if (roles != null) {
                RolesDAO rolesDAO = new RolesDAO();
                for (int i = 0; i < roles.length; i++) {
                    roleSet.add(rolesDAO.getById(Integer.parseInt(roles[i])));
                }
            }

            User newUser = new User();
            newUser.setId(Integer.parseInt(req.getParameter("userId")));
            newUser.setName(req.getParameter("name"));
            newUser.setEmail(req.getParameter("email"));
            newUser.setPassword(encrypt(req.getParameter("password")));
            newUser.setOffice(new OfficesDAO().getById(officeId));
            newUser.setIs_active(Boolean.parseBoolean(req.getParameter("is_active")));
            newUser.setRole(roleSet);
            dao.update(newUser);
/*//            как вообще правильно делать update когда используется many to many?
//            add roleSet to DB
            rolesDAO.setUserRoles(newUser, roleSet);
//            add roleSet to user*/



//            удаление
        } else if (action != null && action.equals("Del")) {
            System.out.println("delete branch doPost");
            int userId = Integer.parseInt(req.getParameter("userId"));
            User userToDelete = dao.getById(userId);
//            dao.delete(userToDelete);

            req.setAttribute("userToDelete", userToDelete);
            dao.delete(userId);
            req.getRequestDispatcher("jsp/deleteWindow.jsp").forward(req, resp);

        }

        Set<User> usersSet = dao.getAll();  //список всех users для передачи на домашнюю страницу
        req.setAttribute("users", usersSet);
        req.setAttribute("userInSession", user);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/userHomePage.jsp");
        resp.setContentType("text/html");
        requestDispatcher.forward(req, resp);
    }

}
