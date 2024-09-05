package violation

import node.Position

data class ExpressionInsideReadInputViolation(private val position: Position) : Violation {
  override fun toString(): String {
    return "Expression inside read input statement at $position"
  }
}