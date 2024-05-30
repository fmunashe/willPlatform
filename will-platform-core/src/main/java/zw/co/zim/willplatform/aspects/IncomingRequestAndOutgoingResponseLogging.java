package zw.co.zim.willplatform.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class IncomingRequestAndOutgoingResponseLogging {
    @Pointcut("execution(* zw.co.zim.willplatform.processor..*(..))")
    public void processorMethodsExecution() {
    }

    @Before("processorMethodsExecution()")
    public void logActionBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        String payload = getPayload(joinPoint);
        log.info("========== Incoming Request On {} {} With Payload {} ==========", className, methodName, payload);
    }

    @AfterReturning(pointcut = "processorMethodsExecution()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("========== Outgoing Response On {} {} With Response {} ==========", className, methodName, result.toString());
    }

    private String getPayload(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String parameterName = signature.getParameterNames()[i];
            builder.append(parameterName);
            builder.append(": ");
            builder.append(joinPoint.getArgs()[i].toString());
            builder.append(", ");
        }
        return builder.toString();
    }
}
