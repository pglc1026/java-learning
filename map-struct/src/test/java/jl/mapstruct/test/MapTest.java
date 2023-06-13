package jl.mapstruct.test;

import jl.mapstruct.dto.PersonDTO;
import jl.mapstruct.enums.Gender;
import jl.mapstruct.mapper.normal.PersonMapper;
import jl.mapstruct.po.CustomPO;
import jl.mapstruct.po.HomeAddress;
import jl.mapstruct.po.PersonPO;
import org.junit.Test;

import java.util.Date;

/**
 * @author Liu Chang
 * @date 2020/8/25
 */
public class MapTest {

    private static PersonDTO personDTO;

    static {
        personDTO = new PersonDTO();
        personDTO.setUserName("Will");
        personDTO.setAge(26);
        personDTO.setBirthday("2020-08-25 16:33:53");
        personDTO.setGender(Gender.MALE);
        personDTO.setAddress("{'province': '北京', 'city': '海淀', 'street': '后厂村路'}");
    }

    @Test
    public void testPoToDto() {
        PersonPO personPO = new PersonPO();
        personPO.setName("Will");
        personPO.setAge(26);
        personPO.setBirthday(new Date());
        personPO.setId(1);
        personPO.setGender(Gender.MALE.name());
        CustomPO customPO = new CustomPO();
        customPO.setName("customName");
        customPO.setValue("customValue");
        personPO.setCustom(customPO);
        personPO.setDetailName("detailName");

        HomeAddress homeAddress = new HomeAddress();
        homeAddress.setProvince("北京");
        homeAddress.setCity("海淀");
        homeAddress.setStreet("后厂村路");
        personPO.setHomeAddress(homeAddress);

        PersonDTO personDTO = PersonMapper.INSTANCE.convertToDto(personPO);

        System.out.println(personDTO);
    }

    @Test
    public void testDtoToDo() {
        PersonDTO dto = new PersonDTO();
        dto.setUserName("Will");
        dto.setAge(26);
        dto.setBirthday("2020-08-25 16:33:53");
        dto.setGender(Gender.MALE);
        dto.setAddress("{'province': '北京', 'city': '海淀', 'street': '后厂村路'}");

        PersonPO personPO = PersonMapper.INSTANCE.convertToDo(dto);

        System.out.println(personPO);
    }

    @Test
    public void testDtoToDoNew() {
        PersonDTO dto = new PersonDTO();
        dto.setUserName("Will");
        dto.setAge(26);
        dto.setBirthday("2020-08-25 16:33:53");
        dto.setGender(Gender.MALE);
        dto.setAddress("{'province': '北京', 'city': '海淀', 'street': '后厂村路'}");

        PersonPO personPO = PersonMapper.INSTANCE.convertToDoNew(dto);

        System.out.println(personPO);
    }

    @Test
    public void testUpdate() {
        PersonPO personPO = new PersonPO();
        personPO.setId(123);

        System.out.println(">>>>>>>>>> before: \t" + personPO);
        PersonMapper.INSTANCE.updatePersonDO(personPO, personDTO);

        System.out.println(">>>>>>>>>> after: \t" + personPO);
    }

}
