package com.jc.subject.common.enums;

import lombok.Getter;

@Getter
public enum SubjectTypeEnum {

    RADIO(1,"单选"),
    MULTIPLE(2,"多选"),
    JUDGE(3,"判断"),
    BRIEF(4,"简答"),
    ;

    public int code;

    public String desc;

    SubjectTypeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static SubjectTypeEnum getByCode(int codeVal){
        for(SubjectTypeEnum resultCodeEnum : SubjectTypeEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }

}
