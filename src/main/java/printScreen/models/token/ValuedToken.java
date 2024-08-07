package printScreen.models.token;

import org.jetbrains.annotations.NotNull;
import printScreen.models.token.Token;
import printScreen.models.token.TokenType;

public record ValuedToken(TokenType type, String value, Integer line, Integer column) implements Token {


    @NotNull
    @Override
    public TokenType getType() {
        return type;
    }

    @NotNull
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getLine() {
        return line;

    }
    public String toString() {
        return type + " with value: " + '`' + value  + '`' + " at " + line + ":" + column;
    }
}
