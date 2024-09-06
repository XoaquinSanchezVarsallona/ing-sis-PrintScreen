package com.printscript.cli.commands

import com.printscript.cli.Result
import com.printscript.cli.TXTHandler
import com.printscript.formatter.Formatter
import printScreen.lexer.com.printscript.lexer.Lexer
import printScreen.parser.com.printscript.parser.CatchableParser
import java.io.File

class Format : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
      ?: return Result("File ${file[0]} not found", listOf())
    val lexer = Lexer()
    val parser = CatchableParser()
    val ast = parser.parse(lexer.lex(code))
    var lastNode: com.printscript.models.node.ASTNode?
    val configFile = File("cli/src/main/resources/config/${file[1]}")
    if (!configFile.exists()) return Result("Config file ${file[1]} not found", listOf())
    val res: MutableList<String> = mutableListOf()
    while (ast.hasNext()) {
      lastNode = ast.next()
      if (lastNode is com.printscript.models.node.ErrorNode) break
      res.add(Formatter.format(lastNode, configFile))
    }
    if (ast.hasException()) return Result(ast.getException()!!.message!!, emptyList())
    return Result("", res)
  }
}