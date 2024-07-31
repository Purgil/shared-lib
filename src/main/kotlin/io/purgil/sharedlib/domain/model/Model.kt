package io.purgil.sharedlib.domain.model

import java.util.UUID

data class User(
        val id: UUID,
        val name: String,
        val email: String,
)

data class Image(
        val id: UUID,
        val filePath: String,
        val fileName: String,
)
