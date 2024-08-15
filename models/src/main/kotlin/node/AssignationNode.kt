package node

class AssignationNode(val variable: String?, val expression: ASTNode) : ASTNode {
    override fun accept(visitor: ASTVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return "AssignationNode(variable=$variable, expression=$expression)"
    }
}