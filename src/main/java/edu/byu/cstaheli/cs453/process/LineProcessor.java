package edu.byu.cstaheli.cs453.process;

import edu.byu.cstaheli.cs453.process.util.PorterStemmer;
import edu.byu.cstaheli.cs453.process.util.StopWordsRemover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cstaheli on 5/13/2017.
 */
public class LineProcessor
{
    private List<String> processedWords;

    public LineProcessor(String line)
    {
        List<String> words = new WordTokenizer(line).getWords();
        List<String> nonStopwords = removeStopwords(words);
        processedWords = stemWords(nonStopwords);
    }

    public List<String> getProcessedWords()
    {
        return processedWords;
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
