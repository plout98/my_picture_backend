package com.plout.mypicture.model.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

@Getter
public enum PictureReviewStausEnum {

    REVIEWING("待审核",0),
    PASS("通过",1),
    REJECT("拒绝",2);

    private final String text;

    private final Integer value;

    PictureReviewStausEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }


    public static PictureReviewStausEnum getEnumByValue(Integer value) {

        if (ObjectUtil.isEmpty(value)) {
            return null;
        }

        for (PictureReviewStausEnum userRoleEnum : PictureReviewStausEnum.values()) {
            if (userRoleEnum.getValue() == value) {
                return userRoleEnum;
            }
        }
        return null;
    }


}
