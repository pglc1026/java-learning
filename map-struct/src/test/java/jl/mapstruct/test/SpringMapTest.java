package jl.mapstruct.test;

import jl.mapstruct.MapApplication;
import jl.mapstruct.dto.PersonDTO;
import jl.mapstruct.enums.Gender;
import jl.mapstruct.mapper.component.PersonMapper;
import jl.mapstruct.po.HomeAddress;
import jl.mapstruct.po.PersonPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Liu Chang
 * @date 2020/9/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapApplication.class)
public class SpringMapTest {

    @Resource
    private PersonMapper mapper;

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
    public void testDoToDto() {
        PersonPO personPO = new PersonPO();
        personPO.setName("Will");
        personPO.setAge(26);
        personPO.setBirthday(new Date());
        personPO.setId(1);
        personPO.setGender(Gender.MALE.name());

        HomeAddress homeAddress = new HomeAddress();
        homeAddress.setProvince("北京");
        homeAddress.setCity("海淀");
        homeAddress.setStreet("后厂村路");
        personPO.setHomeAddress(homeAddress);

        PersonDTO personDTO = mapper.convertToDto(personPO);

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

        PersonPO personPO = mapper.convertToDo(dto);

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

        PersonPO personPO = mapper.convertToDoNew(dto);

        System.out.println(personPO);
    }

    @Test
    public void testUpdate() {
        PersonPO personPO = new PersonPO();
        personPO.setId(123);

        System.out.println(">>>>>>>>>> before: \t" + personPO);
        mapper.updatePersonDO(personPO, personDTO);

        System.out.println(">>>>>>>>>> after: \t" + personPO);
    }
}
