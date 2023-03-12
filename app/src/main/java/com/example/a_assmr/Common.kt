package com.example.a_assmr

import kotlin.random.Random

class Common {
    var baseURI: String = ""

    fun getApiURI(): String {
        baseURI = "http://192.168.43.37:7777"

        return baseURI
    }
}