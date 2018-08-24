package com.er453r.plugintest

import com.er453r.sythetic.*

class TestClass {
    companion object {
        private const val html:String = "STATIC VAL"
    }

    private val privateVar:String = "PrivateSTUFF"

    init {
        println("derp constructed ${TestClass::class} $html $privateVar myVar")
    }
}
