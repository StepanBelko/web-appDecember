package by.itstep.stpnbelko.homework.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class User {
    int id;
    String name;
    String email;
    String password;
    int office_id;
    boolean is_active;
    Timestamp created_ts;
    Timestamp updated_ts;

    private Set<Role> role;

}
