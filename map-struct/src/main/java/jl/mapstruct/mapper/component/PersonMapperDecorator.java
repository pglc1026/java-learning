package jl.mapstruct.mapper.component;

import jl.mapstruct.dto.PersonDTO;
import jl.mapstruct.po.HomeAddress;
import jl.mapstruct.po.PersonPO;

import java.util.Date;

/**
 * @author Liu Chang
 * @date 2020/8/25
 */
public abstract class PersonMapperDecorator implements PersonMapper {

    @Override
    public PersonPO convertToDoNew(PersonDTO dto) {
        PersonPO personPO = new PersonPO();
        personPO.setId(123);
        personPO.setName("will");
        personPO.setAge(111);
        personPO.setBirthday(new Date());
        HomeAddress homeAddress = stringToHomeAddress(dto.getAddress());
        personPO.setHomeAddress(homeAddress);
        return personPO;
    }
}
