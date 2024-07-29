package io.purgil.sharedlib.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import java.security.Key
import io.jsonwebtoken.security.Keys
import io.purgil.sharedlib.resorver.dto.LoginUser
import io.purgil.sharedlib.vo.RoleType
import org.springframework.web.server.ServerWebExchange
import java.util.UUID

object JwtUtil {
    private const val JWT_SECRET: String = "a__WBdpzzf3opdx5DXwwv8bAueMVk-5AAFNDFAcGrgo"
    private val key: Key = Keys.hmacShaKeyFor(JWT_SECRET.toByteArray())

    fun getLoginUserFromToken(token: String): LoginUser {
        val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body

        val id = UUID.fromString(claims.subject)
        val email = claims["email"].toString()
        val name = claims["name"].toString()
        val roles = claims["roles"] as List<String>
        val roleTypes = roles.map { RoleType.valueOf(it) }

        return LoginUser(id, email, name, roleTypes)
    }

    fun extractToken(exchange: ServerWebExchange): String? {
        val authHeader = exchange.request.headers.getFirst("Authorization")
        return authHeader?.substringAfter("Bearer ")
    }
}
