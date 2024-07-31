package io.purgil.sharedlib.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import java.security.Key
import io.jsonwebtoken.security.Keys
import io.purgil.sharedlib.dto.LoginUser
import io.purgil.sharedlib.vo.RoleType
import io.purgil.sharedlib.vo.TokenType
import org.springframework.web.server.ServerWebExchange
import java.util.Date
import java.util.UUID

object JwtUtil {
    private const val JWT_SECRET: String = "a__WBdpzzf3opdx5DXwwv8bAueMVk-5AAFNDFAcGrgo"
    private val key: Key = Keys.hmacShaKeyFor(JWT_SECRET.toByteArray())

    fun generateTokenPair(loginUser: LoginUser): Pair<String, String> {
        val accessToken = createToken(loginUser, TokenType.ACCESS)
        val refreshToken = createToken(loginUser, TokenType.REFRESH)

        return accessToken to refreshToken
    }

    fun createToken(loginUser: LoginUser, tokenType: TokenType): String {
        val now = Date()
        val expiryDate = Date(now.time + tokenType.getExpiry())

        return Jwts.builder()
                .setSubject(loginUser.id.toString())
                .claim("email", loginUser.email)
                .claim("name", loginUser.name)
                .claim("roles", loginUser.roles.map { it.name })
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact()
    }

    fun getLoginUserFromToken(token: String): LoginUser {
        val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body

        val id = UUID.fromString(claims.subject)
        val email = claims["email"].toString()
        val name = claims["name"].toString()
        val roles = claims["roles"] as List<*>
        val roleTypes = roles.map { RoleType.valueOf(it.toString()) }

        return LoginUser(id, email, name, roleTypes)
    }

    fun extractToken(exchange: ServerWebExchange): String? {
        val authHeader = exchange.request.headers.getFirst("Authorization")
        return authHeader?.substringAfter("Bearer ")
    }
}
