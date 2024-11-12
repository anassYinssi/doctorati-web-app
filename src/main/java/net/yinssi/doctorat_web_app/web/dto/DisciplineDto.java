// DisciplineDto.java
package net.yinssi.doctorat_web_app.web.dto;

import lombok.Getter;
import lombok.Setter;
import net.yinssi.doctorat_web_app.entity.Speciality;

import java.util.Set;

public class DisciplineDto {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Set<Speciality> specialities;
}
