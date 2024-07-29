package io.purgil.sharedlib.config

import io.purgil.sharedlib.util.JwtUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.ReactiveAuditorAware
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import reactor.util.context.Context
import java.util.UUID

@Configuration
@EnableR2dbcAuditing
class AuditingConfig {
    @Bean
    fun auditorAware(): ReactiveAuditorAware<String> = ReactiveAuditorAware {
        Mono.deferContextual { ctx ->
            val userId = ctx.get<String>("userId")
            Mono.just(userId)
        }
    }

    @Bean
    fun authenticationWebFilter(): WebFilter {
        return WebFilter { exchange: ServerWebExchange, chain: WebFilterChain ->
            val token = JwtUtil.extractToken(exchange)
            val userId = token?.let { JwtUtil.getLoginUserFromToken(it) }?.id?.toString() ?: "anonymous"
            chain.filter(exchange)
                    .contextWrite(Context.of("userId", userId))
        }
    }
}
