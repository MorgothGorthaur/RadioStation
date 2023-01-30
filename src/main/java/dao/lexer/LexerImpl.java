package dao.lexer;

import personality.Broadcaster;

public class LexerImpl implements Lexer {
    @Override
    public Broadcaster interpret(String inputString) {
        var arr = inputString.split("[|]");
        for (var s: arr) System.out.println(s);
        System.out.println(inputString);
        return null;
    }
}
