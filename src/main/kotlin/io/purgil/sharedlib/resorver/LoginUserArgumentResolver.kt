package io.purgil.sharedlib.resorver

import io.purgil.sharedlib.dto.LoginUser
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.security.Principal

@Component
class LoginUserArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == LoginUser::class.java
    }

    override fun resolveArgument(
            parameter: MethodParameter,
            bindingContext: BindingContext,
            exchange: ServerWebExchange
    ): Mono<Any> = exchange.getPrincipal<Principal>()
            .flatMap { Mono.deferContextual { ctx -> Mono.justOrEmpty(ctx.getOrEmpty<LoginUser>("loginUser")) } }
}
