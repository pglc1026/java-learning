package jl.mapstruct.mapper.component;

import com.google.gson.Gson;
import jl.mapstruct.dto.PersonDTO;
import jl.mapstruct.mapper.normal.CustomMapper;
import jl.mapstruct.po.HomeAddress;
import jl.mapstruct.po.PersonPO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Liu Chang
 * @date 2020/8/24
 */
@Mapper(componentModel = "spring", uses = CustomMapper.class)
@DecoratedWith(PersonMapperDecorator.class)
public interface PersonMapper {

    Gson GSON = new Gson();

    /**
     * 将PersonDO转换为PersonDTO
     *
     * @param personPO the personDO
     * @return
     */
    @Mapping(source = "name", target = "userName")
    @Mapping(target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "address", expression = "java(homeAddressToAddressString(personPO.getHomeAddress()))")
    PersonDTO convertToDto(PersonPO personPO);

    default String homeAddressToAddressString(HomeAddress homeAddress) {
        return GSON.toJson(homeAddress);
    }

    /**
     * 将personDTO转换为personDO
     *
     * @param dto the dto
     * @return
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "userName", target = "name")
    @Mapping(target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "homeAddress", expression = "java(stringToHomeAddress(dto.getAddress()))")
    PersonPO convertToDo(PersonDTO dto);

    /**
     * 使用personDTO补全personDO
     *
     * @param personPO the personDO
     * @param dto the dto
     */
    @InheritConfiguration(name = "convertToDo")
    void updatePersonDO(@MappingTarget PersonPO personPO, PersonDTO dto);

    default HomeAddress stringToHomeAddress(String addrString) {
        return GSON.fromJson(addrString, HomeAddress.class);
    }

    PersonPO convertToDoNew(PersonDTO dto);

}
