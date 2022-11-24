package cz.vsb.swi.vea2022.utilities.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* cz.vsb.swi.vea2022.controllers.PersonController.*(..))")
    public void logPerson(JoinPoint point) {
        logger.info("Volam metodu v Person controlleru: " + point.getSignature().getName());
    }

}
