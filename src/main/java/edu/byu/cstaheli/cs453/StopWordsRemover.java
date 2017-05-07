package edu.byu.cstaheli.cs453;

import java.io.*;
import java.util.StringTokenizer;
import java.util.HashSet;
//import utils.DataBase;

/**
 * @author rajiv
 * @since Aug 26, 2004
 */
public class StopWordsRemover
{
    private HashSet<String> stopWords;
    private HashSet<String> validWords;

    public StopWordsRemover()
    {
        stopWords = new HashSet<>();
        validWords = new HashSet<>();
        try
        {
            // Read the unordered file in
            BufferedReader in = new BufferedReader(new FileReader("stopwords.txt"));
            StringBuilder str = new StringBuilder();
            String nextLine;
            while ((nextLine = in.readLine()) != null)
            {
                str.append(nextLine).append("\n");
            }
            in.close();
            //save it to a bin tree.
            StringTokenizer st = new StringTokenizer(str.toString());//create a string
            while (st.hasMoreTokens())
            {
                nextLine = st.nextToken();
                if (nextLine.matches("[a-zA-Z'.]*"))
                {
                    stopWords.add(nextLine.trim());
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public HashSet<String> getStopWords()
    {
        return stopWords;
    }

    public void setStopWord(HashSet<String> words)
    {
        stopWords = words;
    }

    public boolean contains(String word)
    {
        return stopWords.contains(word.toLowerCase().trim());
    }
}
