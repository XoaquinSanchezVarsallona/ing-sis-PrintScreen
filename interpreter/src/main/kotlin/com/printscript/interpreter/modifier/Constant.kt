package com.printscript.interpreter.modifier

internal data class Constant(override val type: String, override val value: Any?) : Modifier
