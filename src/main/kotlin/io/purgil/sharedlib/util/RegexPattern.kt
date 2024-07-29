package io.purgil.sharedlib.util

object RegexPattern {
    const val PASSWORD = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?]).{6,}\$"
}
