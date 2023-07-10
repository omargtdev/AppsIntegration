package pe.isil.microservicios_2978.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.isil.microservicios_2978.model.utils.Audit;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = true)
    private String phone;

    @Embedded // Audit fields
    private Audit audit = new Audit();

    public String getFullName(){
        return name + " " + lastname;
    }

}
