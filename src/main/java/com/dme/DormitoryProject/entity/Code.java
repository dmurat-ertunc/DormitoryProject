package com.dme.DormitoryProject.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Code")
public class Code implements Serializable {
    @Id
    private Long id;
    private String code;
    private Object data;

    public Code(Long id, String code, Object data) {
        this.id = id;
        this.code = code;
        this.data = data;
    }

    public Code() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
