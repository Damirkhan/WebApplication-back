package kz.project.Blog.models.entities;

import kz.project.Blog.models.audits.AuditModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends AuditModel{

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "first_name", length = 200)
    private String firstName;

    @Column(name = "last_name", length = 200)
    private String lastName;

    @Column(name = "phone", length = 200)
    private String phone;

    @Column(name = "imageSrc", length = 25500000)
    private String imageSrc;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "active")
    private Long active;

}
