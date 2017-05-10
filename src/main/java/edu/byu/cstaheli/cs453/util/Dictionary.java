package edu.byu.cstaheli.cs453.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cstaheli on 5/8/2017.
 */
public class Dictionary
{
    private Set<String> dictionaryWords;

    public Dictionary()
    {
        dictionaryWords = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                dictionaryWords.add(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean wordExists(String word)
    {
        return dictionaryWords.contains(word);
    }
}
