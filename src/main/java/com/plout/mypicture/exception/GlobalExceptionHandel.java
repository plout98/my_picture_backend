package com.plout.mypicture.exception;

import com.plout.mypicture.common.BaseResponse;
import com.plout.mypicture.common.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* *
 *
 * 全局异常处理器
 * @author plout
 **/
@RestControllerAdvice
public class GlobalExceptionHandel {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandel.class);

    @ExceptionHandler
    public BaseResponse<?> businessExceptionHandler(BusinessException e){
        log.error("businessException",e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e){
        log.error("runtimeException",e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }
}
