package printScreen.models.ast.ast

interface ExecutableAST {

    fun variable () : Pair<String, String?>?
    fun type () : DataType
    fun node () : Node

}