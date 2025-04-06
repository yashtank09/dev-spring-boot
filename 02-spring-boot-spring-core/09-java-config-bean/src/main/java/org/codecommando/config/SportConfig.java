package org.codecommando.config;

import org.codecommando.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SportConfig {
    @Bean("aquatic")
    public SwimCoach swimCoach() {
        return new SwimCoach();
    }
}
