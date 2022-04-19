package com.thejing.utils.mythread;

import com.thejing.model.common.pojos.GameAdmin;
import com.thejing.model.common.pojos.GameStudent;

public class WmThreadLocalUtil {

    private final static ThreadLocal<GameAdmin> threadLocal = new ThreadLocal<>();

    /**
     * 将用户信息存入到线程中
     * @param gameAdmin
     */
    public static void setUser(GameAdmin gameAdmin){
        threadLocal.set(gameAdmin);
    }

    /**
     * 从线程中取出用户
     * @return
     */
    public static GameAdmin getUser(){
        GameAdmin gameAdmin = threadLocal.get();
        return gameAdmin;
    }

    /**
     * 清除线程中的数据
     */
    public static void clear(){
        threadLocal.remove();
    }

}
