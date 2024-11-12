// SpecialityDto.java
package net.yinssi.doctorat_web_app.web.dto;

import lombok.Getter;
import lombok.Setter;
import net.yinssi.doctorat_web_app.entity.Discipline;

public class SpecialityDto {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Discipline discipline;
}
