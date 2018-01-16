package com.jino.baselibrary.utils;

/**
 * 检查各种参数的工具类
 * Created by JINO on 2018/1/14.
 */

public class ConditionUtils {

    /**
     * 检查表达式是否正确
     * @param expression 表达式
     * @param errorMsg 错误信息
     */
    public static void checkIntParams(boolean expression,String errorMsg){
        if(!expression){
            throw new IllegalArgumentException(errorMsg);
        }
    }
}
