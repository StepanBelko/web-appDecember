package by.itstep.stpnbelko.homework.util;

import by.itstep.stpnbelko.homework.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Date;

public class ServletUtils {

    public static final String USER = "user";

    public static void saveSessionUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();

        System.out.println("User in session " + user.getId() + " " + user.getName());
        System.out.println("LOGIN -> session ID " + session.getId() + " : " +new Date());


        //save user to current session
        session.setAttribute(USER, user);
    }

    public static User getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Object userObject = session.getAttribute(USER);

        if (userObject == null) {
            System.out.println("No User in session" + session.getId());
            return null;
        } else {
            return (User) userObject;
        }
    }
}
