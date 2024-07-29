package io.purgil.sharedlib.filter

import io.purgil.sharedlib.util.JwtUtil
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import reactor.util.context.Context

@Component
class LoginUserWebFilter : WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val token = JwtUtil.extractToken(exchange)
        val loginUser = token?.let { JwtUtil.getLoginUserFromToken(it) }

        return if (loginUser != null) {
            chain.filter(exchange)
                    .contextWrite(Context.of("loginUser", loginUser))
        } else {
            chain.filter(exchange)
        }
    }
}
