package com.er453r.kotlinjsplugin

import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.config.CompilerConfiguration

class TestProcessor : CommandLineProcessor {
    companion object {
        const val PLUGIN_ID = "kotlinjsplugin"
    }

    override val pluginId = PLUGIN_ID
    override val pluginOptions = emptyList<CliOption>()

    override fun processOption(option: CliOption, value: String, configuration: CompilerConfiguration) {
        println("TestProcessor loaded for plugin $PLUGIN_ID")
    }
}
