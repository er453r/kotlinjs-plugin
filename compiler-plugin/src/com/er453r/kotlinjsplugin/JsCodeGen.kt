package com.er453r.kotlinjsplugin

import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.js.backend.ast.*
import org.jetbrains.kotlin.js.translate.context.TranslationContext
import org.jetbrains.kotlin.js.translate.declaration.DeclarationBodyVisitor
import org.jetbrains.kotlin.js.translate.extensions.JsSyntheticTranslateExtension
import org.jetbrains.kotlin.js.translate.utils.JsAstUtils
import org.jetbrains.kotlin.psi.KtPureClassOrObject

class JsCodeGen(private val messageCollector: MessageCollector) : JsSyntheticTranslateExtension{
    override fun generateClassSyntheticParts(declaration: KtPureClassOrObject, descriptor: ClassDescriptor, translator: DeclarationBodyVisitor, context: TranslationContext) {


        messageCollector.log("START")

        val targetClass = descriptor
//
//        context.declareConstantValue("DERP", "String", JsStringLiteral("12334"))
//
//        val jsName = context.scope().declareName("myVar")



        val statement:JsStatement = JsAstUtils.assignmentToThisField("myVar", JsStringLiteral("12334"))


        translator.addInitializerStatement(statement)


        messageCollector.log("JS DERP12 Reading ${targetClass.name}")
    }
}
