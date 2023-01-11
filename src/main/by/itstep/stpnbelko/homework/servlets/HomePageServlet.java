package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!HomePage servlet doGet");
        HttpSession currentSession = req.getSession();
        User user = (User) currentSession.getAttribute("user");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!HomePage servlet doPost");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/templates/userHomePage.jsp");
        resp.setContentType("text/html");
//        PrintWriter printWriter = resp.getWriter();
//        User user = new UsersDAO().getByEmail(req.getParameter("email"));
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        Set<User> userSet = new UsersDAO().getAll();
//
//        for (User users : userSet) {
//            stringBuilder.append(users).append("<br>");
//        }
//
//        String content = IOUtils.readFileBuff("/Users/skynet/IdeaProjects/web-appDecember/src/main/webapp/templates/userTable.html");
//
//        content = content.replace("UserName", user.getName());
//        content = content.replace("UserInfo", user.toString());
//        content = content.replace("AllUsersInfo", stringBuilder);
//
//        printWriter.println(content);
        requestDispatcher.forward(req,resp);
    }

}
