package com.er453r.plugintest

import org.w3c.dom.HTMLHeadingElement
import kotlin.browser.document

fun main(args: Array<String>) {
    println("hello derp!")

    val header = document.createElement("h1") as HTMLHeadingElement

    header.innerHTML = "dynamic lol"

    document.addEventListener("DOMContentLoaded", {
        document.body?.appendChild(header)

        val header = document.createElement("h1") as HTMLHeadingElement

        header.innerHTML = TestUtils().sayHello("DERP")

        document.body?.appendChild(header)
    })

    TestClass()
}
