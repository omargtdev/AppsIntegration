package pe.isil.cliente_2978.model;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;

    public String getFullName() {
        return name + " " + lastname;
    }

}
