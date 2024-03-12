package com.example.webblog.config;

import com.example.webblog.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditing {
    @Bean
    public AuditorAware<UserEntity> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
