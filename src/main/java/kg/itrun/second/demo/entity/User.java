package kg.itrun.second.demo.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
@RequiredArgsConstructor
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
    /*private String email; */
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "roleid" )
    private Role role;


    public User(String name, String phone, String login, String pwd) {
        this.name = name;
        this.phone = phone;
        this.login = login;
        this.pwd = pwd;
    }

}
