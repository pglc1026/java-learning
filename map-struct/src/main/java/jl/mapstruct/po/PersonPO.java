package jl.mapstruct.po;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author Liu Chang
 * @date 2020/8/24
 */
@Data
public class PersonPO {

    private Integer id;

    private String name;

    private int age;

    private Date birthday;

    private String gender;

    private HomeAddress homeAddress;

    private CustomPO custom;

    private String detailName;

}