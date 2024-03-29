package com.nirvana.habits.journal.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.cdi.Eager;

@Configuration
@Eager
public class TenantContextFactory {
    @Autowired
    private static TenantContext tenantContext;

    public static TenantContext getTenantContext() {
        return tenantContext;
    }
}
