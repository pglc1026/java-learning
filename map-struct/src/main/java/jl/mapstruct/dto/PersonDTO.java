package jl.mapstruct.dto;

import jl.mapstruct.enums.Gender;
import lombok.Data;
import lombok.ToString;

/**
 * @author Liu Chang
 * @date 2020/8/24
 */
@Data
public class PersonDTO {

    private String userName;

    private Integer age;

    private String birthday;

    private Gender gender;

    private String address;

    private String custom;

    private Detail detail;

}