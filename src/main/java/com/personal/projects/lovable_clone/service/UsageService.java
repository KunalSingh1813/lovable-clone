package com.personal.projects.lovable_clone.service;

import com.personal.projects.lovable_clone.dto.subscription.PlanLimitResponse;
import com.personal.projects.lovable_clone.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitResponse getCurrentSubscriptionLimitOfUser(Long userId);
}
