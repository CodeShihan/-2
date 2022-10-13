package org.jeecg.modules.message.enums;

public enum PushPlanStatusEnum {
    /**
     *
     */
    WAIT(1, "等待中"),

    PUSH(2, "推送完毕"),

    TERMINATED(3, "已终止");

    private Integer code;

    private String value;

    PushPlanStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
