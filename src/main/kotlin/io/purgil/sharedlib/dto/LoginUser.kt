package io.purgil.sharedlib.dto

import io.purgil.sharedlib.vo.RoleType
import java.util.*

data class LoginUser(
        val id: UUID,
        val email: String,
        val name: String,
        val roles: List<RoleType>
)
