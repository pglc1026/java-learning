package jl.mapstruct.mapper.normal;

import com.google.gson.Gson;
import jl.mapstruct.po.CustomPO;
import org.mapstruct.Mapper;

/**
 * @author Liu Chang
 * @date 2020/9/1
 */
@Mapper
public interface CustomMapper {

    default String customToString(CustomPO po) {
        return new Gson().toJson(po);
    }

    default CustomPO stringToCustomPO(String value) {
        return new Gson().fromJson(value, CustomPO.class);
    }

}
