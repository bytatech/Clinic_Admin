package com.lxisoft.byta.LogAspect;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


@Component
@Aspect

public class LogAspect {

	private Logger log= LogManager.getLogger(this.getClass());
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controller() throws Throwable {
		
	}
	
	
	@Before("controller()")
	public void logTimeMethod(JoinPoint joinPoint) throws Throwable {
		
		log.info("----BEFORE :");
		log.info("----Entering in Method :  " + joinPoint.getSignature().getName());
		log.info("----Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
		log.info("----Arguments :  " + Arrays.toString(joinPoint.getArgs()));
		log.info("----Target class : " + joinPoint.getTarget().getClass().getName());
		
	}
	
	@AfterReturning(pointcut = "controller()", returning = "result")
	public void logAfter(JoinPoint joinPoint, Object result) {
		String returnValue = this.getValue(result);
		log.info("++++Method Return value : " + returnValue);
	}


	



	private String getValue(Object result) {
		String returnValue = null;
		if (null != result) {
			if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
				returnValue = ReflectionToStringBuilder.toString(result);
			} else {
				returnValue = result.toString();
			}
		}
		return returnValue;
	}
	
}
