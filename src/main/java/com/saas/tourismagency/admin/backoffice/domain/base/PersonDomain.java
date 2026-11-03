package com.saas.tourismagency.admin.backoffice.domain.base;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.GenderEnum;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class PersonDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;

    private String identify;

    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate dateBirthday;
    private String identifyPerson;

    private String gender;

    @Getter(AccessLevel.NONE)
    private String dateBirthdayStr;

    @Getter(AccessLevel.NONE)
    private String fullName;

    @Getter(AccessLevel.NONE)
    private String abrevGender;

    public String getDateBirthdayStr(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getDateBirthday());
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public String getAbrevGender(){
        if (gender.equals(GenderEnum.FEMALE.getName())){
            return "F";
        }else if (gender.equals(GenderEnum.MALE.getName())){
            return "M";
        }
        return "";
    }
}
