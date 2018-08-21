package com.er453r.plugintest

class TestClass{
    constructor(){
        println("derp ${TestClass::class}")
    }
}

fun main(args:Array<String>){
    println("started")

    TestClass()
}
