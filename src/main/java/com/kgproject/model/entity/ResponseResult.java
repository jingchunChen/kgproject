package com.kgproject.model.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 */
// 实体类与json互转的时候 属性值为null的不参与序列化
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */

    private String msg;
    /**
     * 查询到的结果数据，
     */
    private Map<String, Object> data = new HashMap<>();

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseResult success(String message) {
        ResponseResult result = new ResponseResult();
        result.setCode(200);
        result.setMsg(message);
        return result;
    }
    public static ResponseResult fail(String message) {
        ResponseResult result = new ResponseResult();
        result.setCode(500);
        result.setMsg(message);
        return result;
    }
    public ResponseResult add(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }
    public ResponseResult addAll(Map<? extends String, ?> map) {
        this.getData().putAll(map);
        return this;
    }
}