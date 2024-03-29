package com.nirvana.habits.journal.tenant;

import com.nirvana.habits.journal.context.Tenant;
import com.nirvana.habits.journal.context.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static com.nirvana.habits.journal.context.NContext.DEFAULT_TENANT_ID;
import static com.nirvana.habits.journal.context.NContext.TENANT_X_TENANT_ID_HEADER;

@Slf4j
@Component
public class TenantInterceptor implements HandlerInterceptor {
    @Autowired
    private TenantContext tenantContext;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Add or check headers before the controller method is called
        log.info("Received request: {} {} from {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
        String tenantId = request.getHeader(TENANT_X_TENANT_ID_HEADER);
        if (StringUtils.hasLength(tenantId)) {
            log.info("Received Tenant in Header tenantId = {} Tenant Header", tenantId);
            tenantContext.setTenant(new Tenant(tenantId, tenantId));
        } else {
            tenantContext.setTenant(new Tenant(DEFAULT_TENANT_ID, DEFAULT_TENANT_ID));
        }
        if(request.getHeader("Authorization") == null) {
            //response.addHeader("Authorization", JwtUtil.generateToken("Srinivas"));
        }

        response.addHeader(TENANT_X_TENANT_ID_HEADER, tenantContext.getTenant().getTenantId());
        return true; // Continue with the execution chain
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // Manipulate response after controller method has been executed
        log.info("postHandle Tenant in Header tenantId = {} Tenant Header", tenantContext.getTenant());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Log response details
        log.info("Sent response: {} {} with status {} and exception {}", request.getMethod(), request.getRequestURI(), response.getStatus(), ex);
    }
}
