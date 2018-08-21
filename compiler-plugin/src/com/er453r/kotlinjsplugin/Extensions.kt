package com.er453r.kotlinjsplugin

import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector

fun MessageCollector.log(message: String) {
    this.report(CompilerMessageSeverity.WARNING,
            "[${TestSubplugin.ID}]: $message",
            CompilerMessageLocation.create(null))
}
