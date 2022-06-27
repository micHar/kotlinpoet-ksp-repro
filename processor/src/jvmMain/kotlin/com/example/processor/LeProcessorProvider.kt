package com.example.processor

import com.example.annotation.LeAnnotation
import com.example.annotation.Whatever
import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.ksp.KotlinPoetKspPreview
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo

class LeProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) =
        KspProcessor(environment.codeGenerator)
}

@OptIn(KotlinPoetKspPreview::class, KspExperimental::class)
class KspProcessor(private val codeGenerator: CodeGenerator) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {

        resolver.getSymbolsWithAnnotation(LeAnnotation::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .filter { it.validate() }
            .forEach {

                val func = FunSpec.builder(it.toClassName().simpleName.lowercase())
                    .returns(Whatever::class)
                    .addCode(
                        buildCodeBlock {
                            add("return %T()", Whatever::class)
                        }
                    )
                    .build()

                FileSpec.builder("com.example.repro", "example")
                    .addFunction(func)
                    .build()
                    .writeTo(codeGenerator, Dependencies(aggregating = false))
            }

        return emptyList()

    }

}
