package kg.itrun.second.demo.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;
    private String name;
    private String phone;
    private String login;
    private String pwd;
    //Пока не нужно если понадобиться то нужно будет добавить его еще в HTML
    /*private String email;
    private int is_active;
    @ManyToOne
    @JoinColumn(name = "role_id" )
    private Role role; */

}
