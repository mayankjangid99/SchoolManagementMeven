package com.school.common.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentFeeAspect {

	private static Logger LOG = LoggerFactory.getLogger(StudentFeeAspect.class);
	
	
}
