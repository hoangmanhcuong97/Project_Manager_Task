package com.amela.managertaskamela.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TaskAspect {
    @Before(value = "executeController()")
    public void beforeExecuteController() {
        System.out.println("Before controller");
    }

    @After(value = "executeController()")
    public void afterExecuteController() {
        System.out.println("After Controller");
    }

    @Pointcut(value = "within(com.amela.managertaskamela.controller.*)")
    public void executeController() {}
}
