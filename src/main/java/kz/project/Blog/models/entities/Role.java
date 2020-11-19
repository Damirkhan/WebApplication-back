package kz.project.Blog.models.entities;

import kz.project.Blog.models.audits.AuditModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends AuditModel{
    public final static Long ROLE_ADMIN_ID = 1L;
    public final static Long ROLE_USER_ID = 2L;

    public final static String ROLE_ADMIN_NAME = "ROLE_ADMIN";
    public final static String ROLE_USER_NAME = "ROLE_USER";

    @Column(name = "name", length = 200)
    private String name;

}
