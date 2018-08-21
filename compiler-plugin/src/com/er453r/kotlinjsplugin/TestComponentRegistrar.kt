package com.er453r.kotlinjsplugin

import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.codegen.extensions.ClassBuilderInterceptorExtension
import org.jetbrains.kotlin.codegen.extensions.ExpressionCodegenExtension
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration

class TestComponentRegistrar : ComponentRegistrar {
    companion object {
        const val ID = "kotlinjsplugin"
    }

    override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
        println("TestComponentRegistrar loaded for plugin ${TestProcessor.PLUGIN_ID}")

        // see https://github.com/JetBrains/kotlin/blob/1.1.2/plugins/annotation-collector/src/org/jetbrains/kotlin/annotation/AnnotationCollectorPlugin.kt#L92
        val messageCollector = configuration.get(CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY, MessageCollector.NONE)

        ClassBuilderInterceptorExtension.registerExtension(project, TestExtension(messageCollector))
        ExpressionCodegenExtension.registerExtension(project, CodeGen(messageCollector))
    }
}