package com.hanbit.spring.core.aop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Order(10)
@Component
public class ExceptionAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(FirstAop.class);
	
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object handleException(ProceedingJoinPoint pjp) throws Throwable{
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			// AOP에서 클레스 읽어오기
			MethodSignature signature = (MethodSignature) pjp.getSignature();
			Class returnType = signature.getReturnType();
			
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			// 응답읽어오기
			HttpServletResponse res = requestAttributes.getResponse();
			HttpServletRequest req = requestAttributes.getRequest();
			
			if(returnType == String.class){
//				res.setHeader("Content-Type", "text/html;charset=utf-8");
				int errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
				
				if(e instanceof SecurityException){
//					res.sendError(401, e.getMessage());
//					res.setStatus(401);
					errorCode = HttpStatus.UNAUTHORIZED.value();
				}else{
//					res.sendError(500, e.getMessage());
//					res.setStatus(500);
					
				}
				res.setStatus(errorCode);
				req.setAttribute("errorCode", errorCode);
				req.setAttribute("error", e.getMessage());
				return "error";
				
//				req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, res);
			}else{
				Map error = new HashMap();
				error.put("error", e.getMessage());
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(error);
				byte[] bytes = json.getBytes("UTF-8");
				
				res.setStatus(2500);
				res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				res.setContentLength(bytes.length);
				res.getOutputStream().write(bytes);
				
//				res.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
//				res.sendError(2500, json);
			}
		}
		return null;
	}
}
