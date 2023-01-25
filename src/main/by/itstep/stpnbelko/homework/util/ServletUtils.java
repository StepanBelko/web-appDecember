package by.itstep.stpnbelko.homework.util;

import by.itstep.stpnbelko.homework.dao.impl.RolesDAO;
import by.itstep.stpnbelko.homework.model.Role;
import by.itstep.stpnbelko.homework.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ServletUtils {

    public static final String USER = "user";

    public static RolesDAO dao = new RolesDAO();

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


    public static Map<Role, List<String>> getRolesMapping() {
        Map<Role, List<String>> map = new HashMap<>();
        Properties props = new Properties();
        try {
            props.load(new FileReader(new String("/Users/skynet/IdeaProjects/web-appDecember/src/main/resources/auth.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry<Object, Object> pair : props.entrySet()) {
            Role role = dao.getByName((String) pair.getKey());
            List<String> urls = Arrays.asList(((String)pair.getValue()).split(","));
            map.put(role, urls);
        }



        return map;
    }

    public static void main(String[] args) {
    }
}
