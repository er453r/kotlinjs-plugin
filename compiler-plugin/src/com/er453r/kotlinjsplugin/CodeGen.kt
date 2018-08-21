package com.er453r.kotlinjsplugin

import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.codegen.ImplementationBodyCodegen
import org.jetbrains.kotlin.codegen.extensions.ExpressionCodegenExtension

class CodeGen(private val messageCollector: MessageCollector) : ExpressionCodegenExtension {
    private fun log(message: String) {
        messageCollector.report(
                CompilerMessageSeverity.WARNING,
                "*** REDACTED: $message",
                CompilerMessageLocation.create(null))
    }

    override fun generateClassSyntheticParts(codegen: ImplementationBodyCodegen) {
        val targetClass = codegen.descriptor
        log("Reading ${targetClass.name}")
    }
}
