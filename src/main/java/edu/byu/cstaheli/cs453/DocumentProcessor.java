package edu.byu.cstaheli.cs453;

import com.sun.istack.internal.Nullable;
import edu.byu.cstaheli.cs453.util.PorterStemmer;
import edu.byu.cstaheli.cs453.util.StopWordsRemover;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cstaheli on 5/11/2017.
 */
public class DocumentProcessor
{
    private Map<String, Integer> wordCounts;

    public DocumentProcessor(String fileName)
    {
        wordCounts = new HashMap<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(fileName), Charset.forName("ISO-8859-1"));
            //List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines)
            {
                if (line.length() > 0)
                {
                    List<String> words = new WordTokenizer(line).getWords();
                    List<String> nonStopwords = removeStopwords(words);
                    List<String> stemmedWords = stemWords(nonStopwords);
                    addToWordCounts(stemmedWords);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Nullable
    public Integer getCount(String word)
    {
        return wordCounts.get(word);
    }

    public Map<String, Integer> getWordCounts()
    {
        return wordCounts;
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

    private void addToWordCounts(List<String> words)
    {
        for (String word : words)
        {
            Integer count = wordCounts.get(word);
            wordCounts.put(word, (count == null) ? 1 : count + 1);
        }
    }
}
