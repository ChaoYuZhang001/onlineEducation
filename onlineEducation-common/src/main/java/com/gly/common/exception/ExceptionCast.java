package com.gly.common.exception;


import com.gly.common.model.response.ResultCode;

/**
 * @author zcy
 * @Date: 2019/8/26 17:53
 **/
public class ExceptionCast {

    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
