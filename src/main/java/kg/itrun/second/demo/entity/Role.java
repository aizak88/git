package kg.itrun.second.demo.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
@RequiredArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleid")
    private int id;

    private String roleName;
    private boolean active;

    public Role(String roleName, boolean active) {
        this.roleName = roleName;
        this.active = active;
    }
}
