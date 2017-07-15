package com.hanbit.spring.core.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.reflection.ArrayUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hanbit.spring.core.annotation.SigninRequired;

@Aspect
@Order(20)
@Component
public class SecurityAspect {
	// 어노테이션이 선언된 메소드일때만
	@Around("@annotation(com.hanbit.spring.core.annotation.SigninRequired)")
	public Object checkSignIn(ProceedingJoinPoint pjp) throws Throwable{
		
		//요청이 있을경우 스프링에서 자동으로 저장하는기능임
		/*
		 *  web.xml 에서 
		 *  <listener>
			  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
			 </listener>
		 */
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		// 요청읽어오기
		HttpServletRequest req = requestAttributes.getRequest();
		// 응답읽어오기
		HttpServletResponse res = requestAttributes.getResponse();
		
		// AOP에세 클레스 읽어오기
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Class returnType = signature.getReturnType();
		
		HttpSession session = req.getSession();
		Boolean signedIn = (Boolean) session.getAttribute("signedIn");
		if(signedIn == null || !signedIn) {

//			if(returnType == String.class){
//				res.sendRedirect("/signin");
//				res.flushBuffer();
//			}else{
//				String json = "{\"error\":\"로그인이 필요합니다.\"}";
//				res.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
//				res.getOutputStream().write(json.getBytes("UTF-8"));
//				res.sendError(401, "로그인이 필요합니다.");
//				return null;
//			}
			
			throw new SecurityException("로그인이 필요합니다.");
		}
		else{ //로그인시 권한체크
			SigninRequired signinRequired = signature.getMethod().getAnnotation(SigninRequired.class);
			String[] roles = signinRequired.value();
			String role = (String) session.getAttribute("role");
			
			if(roles.length > 0 && !ArrayUtils.contains(roles, role)){
//				res.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
//				res.sendError(401, "권한이 없습니다.");
				
				throw new SecurityException("권한이 없습니다.");
			}
			
		}
		return pjp.proceed();
	}
}
