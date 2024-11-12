package net.yinssi.doctorat_web_app.web.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.yinssi.doctorat_web_app.entity.Document;
import net.yinssi.doctorat_web_app.entity.Proposal;
import net.yinssi.doctorat_web_app.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Time;

public class ApplicationDto {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private long num_inscription;

    @Getter
    @Setter
    private String motivation ;

    @Getter
    @Setter
    private MultipartFile  cv;

    @Getter
    @Setter
    private MultipartFile  notes;

    @Getter
    @Setter
    private String cursus;


    @Getter
    @Setter
    private String status;

    @Setter
    @Getter
    private User student;

    @Setter
    @Getter
    private Proposal proposal;

    @Getter
    @Setter
    private Date applicationDate;

    @Getter
    @Setter
    private Time applicationTime;


}
