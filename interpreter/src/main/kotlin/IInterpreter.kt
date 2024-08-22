import node.ASTNode

/**
 * Interface for the interpreter.
 * No se ni que escribo, es solo para commitear.
 */
interface IInterpreter {
  infix fun interpret(nodes: List<ASTNode>)
}