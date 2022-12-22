package by.itstep.stpnbelko.homework.model;

import lombok.Data;

@Data
public class User {
    int id;
    String name;
    String email;
    String password;
    boolean is_active;
    String created_ts;
    String updated_ts;
}
