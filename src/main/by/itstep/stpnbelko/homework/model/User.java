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

    @Override
    public String toString() {
//        String result = String.format("id = %3d, name = %5s, email = %20s, password = %15s, \n" +
//                "active = %b, created = %19s, last_upd = %19s",
//                id, name, email,password, is_active, created_ts, updated_ts) ;
//
//
//        return result;
        return "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", is_active=" + is_active +
                ", created_ts='" + created_ts + '\'' +
                ", updated_ts='" + updated_ts + '\'';
    }
}
