package net.yinssi.doctorat_web_app.web.dto;

import lombok.Getter;
import lombok.Setter;
import net.yinssi.doctorat_web_app.entity.Discipline;
import net.yinssi.doctorat_web_app.entity.Speciality;
import net.yinssi.doctorat_web_app.entity.User;

import java.sql.Date;
import java.sql.Time;

public class ProposalDto {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String topic;

    @Getter
    @Setter
    private Discipline discipline;

    @Getter
    @Setter
    private String disciplineName;

    @Getter
    @Setter
    private Speciality speciality;

    @Getter
    @Setter
    private String specialityName;

    @Getter
    @Setter
    private String skills;

    @Getter
    @Setter
    private String objectives;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int numberOfApplications;

    @Getter
    @Setter
    private boolean edited;

    @Getter
    @Setter
    private User professor;

    @Getter
    @Setter
    private String professorFullName;

    @Getter
    @Setter
    private Date proposalDate;

    @Getter
    @Setter
    private Time proposalTime;
}
