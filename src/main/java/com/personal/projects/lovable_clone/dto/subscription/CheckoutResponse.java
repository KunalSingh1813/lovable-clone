package com.personal.projects.lovable_clone.dto.subscription;

import org.springframework.web.bind.annotation.RequestBody;

public record CheckoutResponse(
        @RequestBody CheckoutRequest request
) {
}
