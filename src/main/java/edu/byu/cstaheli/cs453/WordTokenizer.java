package edu.byu.cstaheli.cs453;

import edu.byu.cstaheli.cs453.util.Dictionary;

import java.util.*;

/**
 * Created by cstaheli on 5/11/2017.
 */
public class WordTokenizer
{
    private List<String> tokens;

    public WordTokenizer(String line)
    {
        tokens = new ArrayList<>();
        parseLine(line);
    }

    private void parseLine(String line)
    {
        String[] words = line.split("\\s+");
        for (String word : words)
        {
            if (word.indexOf('-') != -1)
            {
                parseHyphenatedWord(word);
            }
            else
            {
                parseWord(word);
            }
        }
    }

    private void parseWord(String word)
    {
        word = stripExtraCharacters(word);
        if (!word.matches(".*\\d+.*"))
        {
            if (Dictionary.getInstance().wordExists(word))
            {
                tokens.add(word.toLowerCase());
            }
        }
    }

    private String stripExtraCharacters(String word)
    {
        word = word.replaceAll("[^\\w]+", "");
        return word;
    }

    private void parseHyphenatedWord(String word)
    {
        String[] hyphenatedWords = word.split("-");
        assert hyphenatedWords.length == 2;
        for (int i = 0; i < hyphenatedWords.length; ++i)
        {
            hyphenatedWords[i] = stripExtraCharacters(hyphenatedWords[i]);
        }
        String concatenatedWord = hyphenatedWords[0] + hyphenatedWords[1];
        if (Dictionary.getInstance().wordExists(concatenatedWord))
        {
            parseWord(concatenatedWord);
        }
        else
        {
            parseWord(hyphenatedWords[0]);
            parseWord(hyphenatedWords[1]);
        }
    }

    public List<String> getWords()
    {
        return tokens;
    }
}
