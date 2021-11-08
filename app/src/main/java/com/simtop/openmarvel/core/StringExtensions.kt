package com.simtop.openmarvel.core

import java.math.BigInteger
import java.security.MessageDigest

private const val MD5 = "MD5"

/*
A hash function
 */
fun String.md5(): String {
    val md = MessageDigest.getInstance(MD5)
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}