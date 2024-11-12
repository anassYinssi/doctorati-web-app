package net.yinssi.doctorat_web_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(name = "file_name")
    @Getter @Setter
    private String fileName;

    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB")
    @Getter @Setter
    private byte[] data;
}


