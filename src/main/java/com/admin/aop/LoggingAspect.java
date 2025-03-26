package com.admin.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
public class LoggingAspect {
	private static final Logger logger  = LoggerFactory.getLogger(LoggingAspect.class);
	  @Pointcut("execution(*com.admin.services.service.*.*(..))")
	    public void serviceMethods() {}
	@Before("serviceMethod()")
	public void logBeforeMethod(JoinPoint joinPoint) {
      logger.info("Entering method: {}", joinPoint.getSignature());
  }
 
  @AfterReturning(value = "serviceMethod()", returning = "result")
  public void logAfterReturning(JoinPoint joinPoint, Object result) {
      logger.info("Exiting method: {} | Return: {}", joinPoint.getSignature(), result);
  }
 
  @Around("serviceMethod()")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
      long start = System.currentTimeMillis();
      Object result = joinPoint.proceed();
      long executionTime = System.currentTimeMillis() - start;
      logger.info("Method {} executed in {} ms", joinPoint.getSignature(), executionTime);
      return result;
  }
 
  @AfterThrowing(value = "serviceMethod()", throwing = "exception")
  public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
      logger.error("Exception in method: {} | Message: {}", joinPoint.getSignature(), exception.getMessage());
  }
 
 
}