package kz.project.Blog.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import kz.project.Blog.models.audits.AuditModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class Post extends AuditModel{

    @Column(name = "text", length = 250)
    private String text;

    @Column(name = "imageSrc", length = 25500000)
    private String imageSrc;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty("author")
    private User author;

}
