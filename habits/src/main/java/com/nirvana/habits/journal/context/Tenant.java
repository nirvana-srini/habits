package com.nirvana.habits.journal.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {
    private String tenantId;
    private String tenantName;
}
