package com.sqa.examplespringboot.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <T>中的T代表的就是Type的意思
 *</T>表示泛型
 * Data不用再去写getter and setter
 * @param <T>
 */
@Data
public class Result <T>{
    //状态码，对应ResultCode每一个枚举类型的Code
    private Integer code;
    //提示信息，返回给前端的message信息，对应ResultCode每一个枚举类型的message
    private String message;
    //返回给用户的数据信息，类型是T，也就是Type，也就是可以是任何类型
    private T data;

    /**
     * success不带参数的方法
     * 当不需要给前端（html）返回数据的时候需要调用的方法
     *
     * @return
     * @param <T>
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    /**
     * success不带参数的方法
     *
     * 当需要给前端html返回数据的时候需要调用的方法
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * failed不带参数的方法,再失败的时候用的
     * 统一报错提示
     * @return
     * @param <T>
     */
    public static <T> Result<T> failed(){
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAILED.getCode());
        result.setMessage(ResultCode.FAILED.getMessage());
        return result;
    }

    /**
     * failed带参数的方法，在失败时候用的
     * ResultCode是选择需要给前端返回错误
     * @param resultCode
     * @return
     * @param <T>
     */
    public static <T> Result<T> failed(ResultCode resultCode){
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }
}
