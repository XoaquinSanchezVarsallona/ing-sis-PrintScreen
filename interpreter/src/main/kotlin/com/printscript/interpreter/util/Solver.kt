package com.printscript.interpreter.util

import com.printscript.interpreter.OperationException
import com.printscript.interpreter.ReferenceException

internal object Solver {
  @Throws(Exception::class)
  fun getValue(context: Context, node: com.printscript.models.node.ASTNode): Any {
    return when (node) {
      is com.printscript.models.node.LiteralNode<*> -> {
        when {
          node.value is String && (node.value as String).startsWith("\"") ->
            (node.value as String).replace("\"", "")

          node.value is String && (node.value as String).startsWith("'") ->
            (node.value as String).replace("'", "")

          node.value is String -> (context get node.value as String)?.getValue()
            ?: throw ReferenceException("Variable ${node.value} not declared.")

          else -> node.value!!
        }
      }

      is com.printscript.models.node.DoubleExpressionNode -> {
        val a = getValue(context, node.left)
        val b = getValue(context, node.right)
        when (node.operator) {
          "+" -> add(a, b)
          "-" -> subtract(a, b)
          "*" -> multiply(a, b)
          "/" -> divide(a, b)
          else -> throw OperationException("Operator ${node.operator} not supported.")
        }
      }

      else -> throw OperationException("Operation not supported.")
    }
  }

  private fun round(number: Number): Number {
    return when {
      number.toDouble() % 1 == 0.0 -> number.toInt()
      else -> number
    }
  }

  private fun add(a: Any, b: Any): Any {
    return when {
      a is Number && b is Number -> round(a.toDouble() + b.toDouble())
      a is Number && b is String -> a.toString() + b
      a is String && b is Number -> a + b.toString()
      a is String && b is String -> a + b
      else -> throw OperationException("Operation $a + $b not supported.")
    }
  }

  private fun subtract(a: Any, b: Any): Any {
    return when {
      a is Number && b is Number -> round(a.toDouble() - b.toDouble())
      else -> throw OperationException("Operation $a - $b not supported.")
    }
  }

  private fun multiply(a: Any, b: Any): Any {
    return when {
      a is Number && b is Number -> round(a.toDouble() * b.toDouble())
      else -> throw OperationException("Operation $a * $b not supported.")
    }
  }

  private fun divide(a: Any, b: Any): Any {
    return when {
      a is Number && b is Number -> round(a.toDouble() / b.toDouble())
      else -> throw OperationException("Operation $a / $b not supported.")
    }
  }
}