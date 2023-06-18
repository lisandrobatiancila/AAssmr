package com.example.a_assmr

import kotlin.random.Random

class Common {
    var IP: String = ""
    var baseURI: String = "http://192.168.43.222:7777"
    companion object {
        @JvmField
        var IP: String = "http://192.168.43.222:7777"
    }
    fun getApiURI(): String {
        baseURI = "http://192.168.43.222:7777"

        return baseURI
    }
    fun setCurrIP(ip: String) {
        IP = "http://${ip}:7777"
    }
    fun getCurrIP(): String {
        return IP
    }
}