package com.pipi.exception;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pipi.entity.Result;

/**
 * 全局异常处理器
 * @author yuaojin
 *
 */
@CrossOrigin
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler
	public Result processException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		ex.printStackTrace();
		LOGGER.error(ex.toString());
		if(ex instanceof NumberFormatException) {
			return Result.error("参数异常!");
		}
		if(ex instanceof ParseException) {
			return Result.error("日期格式错误!");
		}
		return Result.error(ex.getMessage());
	}

}
