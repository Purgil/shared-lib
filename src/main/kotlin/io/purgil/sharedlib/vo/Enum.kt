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
