package com.gly.common.exception;

import com.gly.common.model.response.ResultCode;

/**
 * 自定义异常类型
 * @author zcy
 * @Date: 2019/8/26 17:53
 **/
public class CustomException extends RuntimeException {

    //错误代码
    ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        this.resultCode = resultCode;
    }
    public ResultCode getResultCode(){
        return resultCode;
    }


}
