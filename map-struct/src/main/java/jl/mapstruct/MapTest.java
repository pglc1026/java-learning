package jl.mapstruct;

import jl.mapstruct.dto.PersonDTO;
import jl.mapstruct.enums.Gender;
import jl.mapstruct.mapper.normal.PersonMapper;
import jl.mapstruct.po.HomeAddress;
import jl.mapstruct.po.PersonPO;

import java.util.Date;

/**
 * @author Liu Chang
 * @date 2020/8/24
 */
public class MapTest {

    public static void main(String[] args) {
        PersonPO personPO = new PersonPO();
        personPO.setName("Hollis");
        personPO.setAge(26);
        personPO.setBirthday(new Date());
        personPO.setId(1);
        personPO.setGender(Gender.MALE.name());

        HomeAddress homeAddress = new HomeAddress();
        homeAddress.setProvince("北京");
        homeAddress.setCity("海淀");
        homeAddress.setStreet("后厂村路");
        personPO.setHomeAddress(homeAddress);

        PersonDTO personDTO = PersonMapper.INSTANCE.convertToDto(personPO);
        System.out.println(personDTO);

        PersonPO personPO1 = PersonMapper.INSTANCE.convertToDo(personDTO);
        System.out.println(personPO1);
    }

}
