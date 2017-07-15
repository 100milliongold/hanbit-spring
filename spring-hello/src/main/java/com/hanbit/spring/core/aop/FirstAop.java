package com.hanbit.spring.core.aop;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbit.spring.core.annotation.SampleAnnotation;
import com.hanbit.spring.core.vo.ArticleVO;



@Component //빈
@Aspect //AOP 선언
public class FirstAop {
	private static final Logger LOGGER = LoggerFactory.getLogger(FirstAop.class);
	
	//포인트컷 선언문
	@Pointcut("execution(public * com.hanbit..BoardService.*(..))")
	public void pointCutBoard(){
		
	}
	
	//포인트컷 선언문
	@Pointcut("execution(public * com.hanbit..HelloService.*(..))")
	public void pointCutHello(){
		
	}
	
//	@AfterThrowing(value="pointCutBoard()",
//			throwing="e"
//			)
//	public void logAfeterBoardError(Throwable e){
//		LOGGER.error(e.getMessage());
//	}
	
	/*@Around("pointCutBoard()")
	public Object arroundBoardService(ProceedingJoinPoint pjp){
		// 실행되는 메소드 읽어오기
		MethodSignature signature =(MethodSignature) pjp.getSignature();
		
		
		
		
		// 메소드이름
		String name = signature.getName();
		
		// 리턴타입
		Class returnType = signature.getReturnType();
		
		// 메소드 겍체읽어오기
		Method method = signature.getMethod();
		
		// 제네릭 읽어오기
		String seneric = method.getGenericReturnType().getTypeName();
		
		// 어노테이션 읽어오기
		SampleAnnotation annotation = method.getAnnotation(SampleAnnotation.class);
		
		// 로거에 출력
		//LOGGER.debug( "@".annotation.value().returnType.getSimpleName()+" <"+seneric+"> "+name);
		LOGGER.debug(annotation.value());
		// Before
		try {
			// AOP에 속하는 메서드는 정상실행
			Object ret = pjp.proceed();
			
			// 정보주입(가짜)
			if(ret instanceof List){
				List<ArticleVO> list = (List<ArticleVO>) ret;
				ArticleVO fakeArticle = new ArticleVO();
				fakeArticle.setTitle("가짜글");
				fakeArticle.setNo(0);
				fakeArticle.setViews(153);
				fakeArticle.setUpdateDt("지금");
				
				list.add(fakeArticle);
			}
			return ret;
		} catch (Throwable e) {
			// AfterThrow
			// 에러가 날 경우 정상오류
			LOGGER.error(e.getMessage());
			throw new RuntimeException("점검 시간입니다.");
		}
		
	}*/
	
	
	
	// 포인트컷 (실행전) returning 실행간에 실행된 메서드
//	@AfterReturning(
//			value = "pointCutHello() ||  pointCutBoard()",
//			returning="ret"
//			)
//	public void logAfterBoardService(Object ret){
//		ObjectMapper mapper = new ObjectMapper();
//		
//		try{
//			String json = mapper.writeValueAsString(ret);
//			LOGGER.debug(json);
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
////		LOGGER.debug(ret.toString());
//		LOGGER.debug("board or hello service called");
//	}
	
	
	
	// 포인트컷 (실행전)
	@Before("pointCutHello()")
	public void logAfterHelloService(){
		LOGGER.debug("hello service will be called");
	}
	
}
