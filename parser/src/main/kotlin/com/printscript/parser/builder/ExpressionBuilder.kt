package com.printscript.parser.builder

import com.printscript.models.node.ASTNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.token.Token

class ExpressionBuilder(private val line: List<Token>) : Builder {

  override fun build(): ASTNode {
    val root = if (line.isEmpty()) LiteralNode("\"\"") else addNodes(line)
    return root
  }

  @Suppress("NestedBlockDepth")
  private fun addNodes(line: List<Token>): ASTNode {
    val operators = mutableListOf<Pair<Token, Int>>()
    line.forEachIndexed { index, token -> if (operatorsToCheck(token)) operators.add(Pair(token, index)) }

    if (line.size == 1) {
      val value = line[0].value
      @Suppress("SwallowedException", "TooGenericExceptionCaught")
      return try {
        LiteralNode(value.toInt())
      } catch (e: NumberFormatException) {
        try {
          LiteralNode(value.toDouble())
        } catch (e: NumberFormatException) {
          try {
            LiteralNode(value.toBooleanStrict())
          } catch (e: Exception) {
            LiteralNode(value)
          }
        }
      }
    }

    return astNode(operators, line)
  }

  private fun astNode(
    operators: MutableList<Pair<Token, Int>>,
    line: List<Token>
  ): ASTNode {
    val tuple = findLowestPrecedenceOperator(operators)
    val (operator, index) = tuple

    if (line.first().value == "(" && line.last().value == ")") {
      return addNodes(line.subList(1, line.size - 1))
      }

    val leftTokens = line.subList(0, index)
    val rightTokens = line.subList(index + 1, line.size)

    val leftNode = addNodes(leftTokens)
    val rightNode = addNodes(rightTokens)

    return DoubleExpressionNode(operator.value, leftNode, rightNode)
  }

  private fun findLowestPrecedenceOperator(operators: List<Pair<Token, Int>>): Pair<Token, Int> {
    var result: Pair<Token, Int>? = null
    var i = 0
    var parenCount = 0

    while (i < operators.size) {
      val (token, index) = operators[i]

      if (token.value == "(") {
        parenCount++
      } else if (token.value == ")") {
        parenCount--
      } else if (parenCount == 0) {
        if (result == null || getPrecedence(token.value) <= getPrecedence(result.first.value)) {
          result = Pair(token, index)
        }
      }

      i++
    }

    return result ?: operators[0]
  }

  private fun getPrecedence(operator: String): Int {
    return when (operator) {
      "+" -> 1
      "-" -> 1
      "*" -> 2
      "/" -> 2
      else -> Int.MAX_VALUE
    }
  }

  private fun operatorsToCheck(token: Token): Boolean {
    return listOf("/", "*", "(", ")", "+", "-").contains(token.value)
  }
}
