package io.purgil.sharedlib.resorver.dto

import io.purgil.sharedlib.vo.RoleType
import java.util.UUID

data class LoginUser(
    val id: UUID,
    val email: String,
    val name: String,
    val roles: List<RoleType>
)
