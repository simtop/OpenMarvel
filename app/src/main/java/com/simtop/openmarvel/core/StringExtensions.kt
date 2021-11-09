package com.simtop.openmarvel.core

import java.math.BigInteger
import java.security.MessageDigest

private const val MD5 = "MD5"
private const val HTTP = "http"
private const val HTTPS = "https"

/*
A hash function
 */
fun String.md5(): String {
    val md = MessageDigest.getInstance(MD5)
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}

fun String.convertUrlToHTTPS(): String {
    if (this.contains(HTTP)) return this.replace(HTTP, HTTPS)
    return this
}