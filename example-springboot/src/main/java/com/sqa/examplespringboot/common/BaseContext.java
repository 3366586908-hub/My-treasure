package com.sqa.examplespringboot.common;

public class BaseContext {
    public static ThreadLocal<Long> userIDThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<String> nickNameThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<String> avatarThreadLocal = new ThreadLocal<>();

    public static long getUserID(){
        return userIDThreadLocal.get();
    }

    public static void setUserID(long userID){
        BaseContext.userIDThreadLocal.set(userID);
    }

    public static String getNickName(){
        return nickNameThreadLocal.get();
    }

    public static void setNickName(String nickName){
        BaseContext.nickNameThreadLocal.set(nickName);
    }

    public static String getAvatar(){
        return avatarThreadLocal.get();
    }

    public static void setAvatar(String avatar){
        BaseContext.avatarThreadLocal.set(avatar);
    }
}
