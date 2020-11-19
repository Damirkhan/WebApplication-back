package kz.project.Blog.models.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import kz.project.Blog.models.audits.AuditModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class Image extends AuditModel {


    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "picByte", length = 100000)
    private byte[] picByte;
}
