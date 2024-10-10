package com.akinc.crud_module.infrastructure.persistence.annotation;

import com.akinc.crud_module.application.service.task.TaskService;
import com.akinc.crud_module.domain.exception.AuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class OwnershipCheckAspect {

    private final TaskService taskService;

    private final HttpServletRequest request;

    @Transactional
    @Before("@annotation(OwnershipCheck) && args(id,..)")
    public void checkOwnership(JoinPoint joinPoint, Long id) {

        var memberId = request.getHeader("X-Member-Id");

        var optionalEntity = taskService.getById(id);

        if ( optionalEntity.isEmpty() || !optionalEntity.get().getMemberEntity().getId().equals(UUID.fromString(memberId))) {
            throw new AuthorizationException("This record does not belong to you.");
        }
    }
}
