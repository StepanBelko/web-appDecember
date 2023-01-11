package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.dao.impl.UsersDAO;
import by.itstep.stpnbelko.homework.model.User;
import by.itstep.stpnbelko.homework.util.IOUtils;
import by.itstep.stpnbelko.homework.util.MailUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

import static by.itstep.stpnbelko.homework.util.EncryptDecrypt.*;


public class RegistrationServlet extends HttpServlet {
    static int i = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("registrationPage.html");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("registrationPage.html");
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pwd1 = encrypt(req.getParameter("password1"));
        String pwd2 = encrypt(req.getParameter("password2"));

        User user = new UsersDAO().getByEmail(email);
        System.out.println(new UsersDAO().getByEmail(email));

        if (user == null) {

            if (pwd1.equals(pwd2)) {
                user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(pwd1);
                new UsersDAO().insert(user);

                System.out.println(email);

                // send message
                String content = IOUtils.readFileBuff("/Users/skynet/IdeaProjects/web-appDecember/src/main/webapp/templates/activate.html");


                //Ссылка в кнопке активации. Должна быть одноразовая!!!!
                content = content.replace("{*}", "http://localhost:8080/web-app/activate?email=" + email);


                ///!!!!!!Проверить!!!!!!!
/*
Rambler.ru - 554 5.7.1 Spam message rejected; If this is not spam contact abuse
Если при отправке письма с сервера на ящик @rambler.ru - приходит обратное письмо
с ошибкой или в логах появляется такая запись:

This message was created automatically by mail delivery software.

A message that you sent could not be delivered to one or more of its
recipients. This is a permanent error. The following address(es) failed:
box@rambler.ru
SMTP error from remote mail server after end of data:
host inmx.rambler.ru [81.19.78.65]: 554 5.7.1 Spam message rejected; If this is not spam contact abuse
------ This is a copy of the message, including all the headers. ------

то Вам нужно написать письмо на abuse@rambler-co.ru,
сообщить какой IP адрес нужно разблокировать и меры
которые были приняты для устранения СПАМа с сервера.
*/
                MailUtils.send(email, "Crazy users activation", content, null);
//                requestDispatcher = req.getRequestDispatcher("welcome");
                resp.getWriter().println("<b>Successfully added. Check your email </b>" + " " + user.getEmail() + "<br>");
                requestDispatcher = req.getRequestDispatcher("login");
                requestDispatcher.include(req, resp);
            } else {
                resp.getWriter().println("<a>Passwords do not match</a>");
                requestDispatcher.include(req, resp);
            }

        } else {
            System.out.println("User already exist " + user.getEmail());
            resp.getWriter().println("<b> User already exist </b > <br>");
            requestDispatcher.include(req, resp);
        }

    }
}
