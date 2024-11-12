package net.yinssi.doctorat_web_app.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Time;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "applications")
public class Application {

    @Getter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    private long num_inscription;

    @Getter
    @Setter
    @Column(length = 1000)
    private String motivation ;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="id_doccv")
    private Document cv;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="id_docnotes")
    private Document notes;

    @Getter
    @Setter
    @Column(length = 1000)
    private String cursus;

    @Getter
    @Setter
    private String status;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name="id_student")
    private User student;

    @Getter
    @Setter
    private Date applicationDate;

    @Getter
    @Setter
    private Time applicationTime;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name="id_proposal")
    Proposal proposal;

    public Application(long num_inscription,String motivation, String cursus, String status, User student, Proposal proposal,Date applicationDate, Time applicationTime) {
        super();
        this.num_inscription = num_inscription;
        this.motivation = motivation;
        this.cursus = cursus;
        this.status = status;
        this.student= student;
        this.proposal= proposal;
        this.applicationDate = applicationDate;
        this.applicationTime = applicationTime;
    }

    public Application() {

    }


}
