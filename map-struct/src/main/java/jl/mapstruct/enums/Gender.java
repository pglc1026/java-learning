package jl.mapstruct.enums;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Liu Chang
 * @date 2020/8/24
 */
@RequiredArgsConstructor
public enum Gender {

    MALE    (1, "男"),
    FEMALE  (2, "女");

    @Getter
    private final int code;

    @Getter
    private final String desc;

}
