package com.printscript.engine.integral

import java.io.Reader

internal class Loader(private val name: String) {
  fun loadInput(): Reader {
    val resource = this::class.java.getResourceAsStream("/integral/$name.ps")
    return resource?.reader() ?: throw IllegalArgumentException("Input file not found")
  }

  fun loadOutput(): List<String> {
    val resource = this::class.java.getResource("/integral/$name.txt")
    val output = resource?.readText()?.replace("\n", "")?.split("\r")
    return output ?: throw IllegalArgumentException("Output file not found")
  }
}
