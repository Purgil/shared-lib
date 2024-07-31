package io.purgil.sharedlib.vo

enum class RoleType(private val label: String) {
    SUPER_ADMIN("슈퍼 관리자"),
    ADMIN("관리자"),
    SUBSCRIBER("구독 회원"),
    MEMBER("회원"),
    ANONYMOUS("비회원")
    ;
    fun getLabel(): String = label
}

enum class TokenType(private val expiry: Long) {
    ACCESS(3600000L), // 1 hour
    REFRESH(3600000 * 24 * 30L) // 30 days
    ;
    fun getExpiry(): Long = expiry
}

enum class ImageType {
    ACTIVITY,
    ROUTE,
    CLUB,
    PROFILE
    ;
}
