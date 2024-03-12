package com.example.webblog.config;

import com.example.webblog.entity.UserEntity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<UserEntity> {
//    @Autowired
//    private UserRepository userRepository;

    @Override
    public Optional<UserEntity> getCurrentAuditor() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (authentication == null || !authentication.isAuthenticated()) {
//                return null;
//            }
//            return userRepository.findByUsername(authentication.getName());
            return Optional.ofNullable((UserEntity) authentication.getPrincipal());
        }catch (Exception e){
            return null;
        }
    }
}
