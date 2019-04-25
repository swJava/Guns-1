package com.stylefeng.guns.rest.config;

import com.alibaba.fastjson.JSON;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.AccessibleObject;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/11 20:22
 * @Version 1.0
 */
@Aspect
@Configuration
public class ControllerAspectConfig {
    private static final Logger log = LoggerFactory.getLogger(ControllerAspectConfig.class);
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.stylefeng.guns.rest..*Controller.*(..))";

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

    @Bean
    public MethodInterceptor txAdvice() {
        return new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {

                try {
                    Object controller = invocation.getThis();
                    AccessibleObject accessObject = invocation.getStaticPart();
                    Object[] arguments = invocation.getArguments();

                    RequestMapping requestMappingAnnotation = accessObject.getAnnotation(RequestMapping.class);

                    if (null == requestMappingAnnotation) {
                        // 不是Controller接口
                        return invocation.proceed();
                    }

                    StringBuilder logBuilder = new StringBuilder();
                    logBuilder.append("接口").append(JSON.toJSONString(requestMappingAnnotation.value()))
                            .append("请求参数: \n");
                    for (Object argument : arguments) {
                        if (null == argument)
                            continue;

                        if (argument instanceof HttpServletRequest
                                || argument instanceof HttpServletResponse
                                || argument instanceof Model)
                            continue;

                        logBuilder.append(argument.getClass().getSimpleName()).append(" = ");
                        logBuilder.append(JSON.toJSONString(argument)).append("\n");
                    }

                    log.debug(logBuilder.toString());
                }catch(Exception e){
                    e.printStackTrace();
                }

                return invocation.proceed();
            }
        };
    }
}
