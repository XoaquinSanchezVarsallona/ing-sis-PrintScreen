package builder

import Result
import commands.Format

class FormatBuilder : CommandBuilder {
  override fun build(command: List<String>): Result {
    if (command.size <= 1) {
      return Result("Missing Arguments", emptyList())
    } else if (command.size > 2) {
      return Result("Too Many Arguments", emptyList())
    }
    return Format().execute(command[0], command[1])
  }
}