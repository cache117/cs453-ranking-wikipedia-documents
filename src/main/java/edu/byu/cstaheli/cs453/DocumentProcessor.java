package edu.byu.cstaheli.cs453;

import edu.byu.cstaheli.cs453.util.PorterStemmer;
import edu.byu.cstaheli.cs453.util.StopWordsRemover;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cstaheli on 5/11/2017.
 */
public class DocumentProcessor
{
    private List<String> document;
    public DocumentProcessor(String fileName)
    {
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines)
            {
                List<String> words = new WordTokenizer(line).getWords();
                List<String> nonStopwords = removeStopwords(words);
                List<String> stemmedWords = stemWords(nonStopwords);
                document.addAll(stemmedWords);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private List<String> stemWords(List<String> words)
    {
        List<String> stemmedWords = new ArrayList<>();
        for (String word : words)
        {
            String stemmedWord = new PorterStemmer().stem(word);
            //Determine if word was actually stemmed
            if (!"Invalid term".equals(stemmedWord) && !"No term entered".equals(stemmedWord))
            {
                stemmedWords.add(stemmedWord);
            }
        }
        return stemmedWords;
    }

    private List<String> removeStopwords(List<String> words)
    {
        List<String> nonStopwords = new ArrayList<>();
        for (String word : words)
        {
            if (!StopWordsRemover.getInstance().contains(word))
            {
                nonStopwords.add(word);
            }
        }
        return nonStopwords;
    }
}
