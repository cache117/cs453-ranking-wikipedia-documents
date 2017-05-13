package edu.byu.cstaheli.cs453.process;

import com.sun.istack.internal.Nullable;
import edu.byu.cstaheli.cs453.process.util.PorterStemmer;
import edu.byu.cstaheli.cs453.process.util.StopWordsRemover;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by cstaheli on 5/11/2017.
 */
public class DocumentProcessor
{
    private Map<String, Integer> wordCounts;

    public DocumentProcessor(String fileName)
    {
        wordCounts = new TreeMap<>();
        try
        {
            readFile(fileName)
                    .stream()
                    .filter(line -> line.length() > 0)
                    .forEach(line -> addToWordCounts(new LineProcessor(line).getProcessedWords()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private List<String> readFile(String fileName) throws IOException
    {
        //A lot of the files aren't encoded with UTF-8
        return Files.readAllLines(Paths.get(fileName), Charset.forName("ISO-8859-1"));
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

    private void addToWordCounts(List<String> words)
    {
        for (String word : words)
        {
            Integer count = wordCounts.get(word);
            wordCounts.put(word, (count == null) ? 1 : count + 1);
        }
    }
}
