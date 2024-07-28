package io.purgil.sharedlib.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import reactor.core.publisher.Mono
import java.util.*

@Configuration
@EnableR2dbcAuditing
class AuditingConfig {
    @Bean
    fun auditorAware(): AuditorAware<String> {
        return AuditorAware { Optional.of("system") }  // 시스템 사용자나 실제 사용자를 반환하는 로직을 여기에 추가
    }
}
