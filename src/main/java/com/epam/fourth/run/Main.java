package com.epam.fourth.run;

import com.epam.fourth.chains.*;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.type.TextType;

public class Main {
    public static void main(String[] args) {
        String str = "\tIt has survived not only five centuries, but also the leap into 13+(3++) electronic --3 typesetting, remaining 3+5 - essentially 6+9*(3-4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5-4)-3)-2)-1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (0-(2*2*(3*(2-1/2*2)-2)-10/2))*(++5) Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.\n" +
                "\tIt is a (-5+1/2*(2+5*2))*1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "\tHello.\n";

        TextComposite text = new TextComposite(TextType.TEXT);

        ParseParagraphChainHandler paragraph = new ParseParagraphChainHandler();
        ParseSentenceChainHandler sentence = new ParseSentenceChainHandler();
        ParseLexemeChainHandler lexeme = new ParseLexemeChainHandler();
        ParseWordChainHandler word = new ParseWordChainHandler();
        ParseCharacterChainHandler character = new ParseCharacterChainHandler();

        paragraph.setSuccessor(sentence);
        sentence.setSuccessor(lexeme);
        lexeme.setSuccessor(word);
        word.setSuccessor(character);

        paragraph.chain(text, str);

        System.out.println("---------------------------------------------------------------");
        System.out.println(text.toString());
        System.out.println("---------------------------------------------------------------");
    }
}
