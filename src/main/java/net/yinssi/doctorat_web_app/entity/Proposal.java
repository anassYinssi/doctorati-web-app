package net.yinssi.doctorat_web_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.sql.Date;
import java.sql.Time;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="proposals")
public class Proposal {

    @Getter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(length = 100)
    private String topic;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "id_discipline")
    private Discipline discipline;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "id_speciality")
    private Speciality speciality;

    @Getter
    @Setter
    private String skills;

    @Getter
    @Setter
    @Column(length = 700)
    private String objectives;

    @Getter
    @Setter
    @Column(length = 1000)
    private String description;

    @Getter
    @Setter
    @Column(nullable = false)
    private int numberOfApplications = 0;

    @Getter
    @Setter
    @Column(nullable = false)
    private boolean edited = false;

    @Getter
    @Setter
    private Date proposalDate;

    @Getter
    @Setter
    private Time proposalTime;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professor")
    private User professor;

    public Proposal(String topic, Discipline discipline, Speciality speciality, String skills, String objectives, String description, User professor, Date proposalDate, Time proposalTime) {
        super();
        this.topic = topic;
        this.discipline = discipline;
        this.speciality = speciality;
        this.skills = skills;
        this.objectives= objectives;
        this.description=description;
        this.professor= professor;
        this.proposalDate = proposalDate;
        this.proposalTime= proposalTime;
    }

    public Proposal() {

    }

}
