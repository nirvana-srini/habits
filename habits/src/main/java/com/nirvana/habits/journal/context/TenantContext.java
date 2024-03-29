package com.nirvana.habits.journal.context;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TenantContext implements NContext {
    private ThreadLocal<Tenant> CURRENT_TENANT = new ThreadLocal<>();

    public Tenant getTenant() {
        return CURRENT_TENANT.get();
    }

    public void setTenant(Tenant tenant) {
        CURRENT_TENANT.set(tenant);
    }
}
