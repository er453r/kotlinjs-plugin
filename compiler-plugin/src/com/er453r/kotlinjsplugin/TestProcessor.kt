package com.er453r.kotlinjsplugin

import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.config.CompilerConfiguration

class TestProcessor : CommandLineProcessor {
    override val pluginId = TestSubplugin.ID
    override val pluginOptions = emptyList<CliOption>()

    override fun processOption(option: CliOption, value: String, configuration: CompilerConfiguration) {
        println("TestProcessor loaded for plugin $pluginId")
    }
}
