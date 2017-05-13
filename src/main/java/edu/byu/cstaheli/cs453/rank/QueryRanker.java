package edu.byu.cstaheli.cs453.rank;

import edu.byu.cstaheli.cs453.index.Index;

import java.util.List;

/**
 * Created by cstaheli on 5/13/2017.
 */
public class QueryRanker
{
    private double score;

    public QueryRanker(int documentId, List<String> query)
    {
        score = 0;
        Index index = Index.getInstance();
        int maxFrequencyInDocument = index.mostFrequentWordFrequency(documentId);
        int numberOfDocuments = index.size();
        for (String word : query)
        {
            int frequency = index.getFrequencyOfWordInDocument(word, documentId);
            int numberOfRelevantDocuments = index.getNumberOfDocumentsWordIsPresentIn(word);
            double termFrequency = termFrequency(frequency, maxFrequencyInDocument);
            double inverseDocumentFrequency = inverseDocumentFrequency(numberOfDocuments, numberOfRelevantDocuments);
            score += termFrequency * inverseDocumentFrequency;
        }
    }

    private double log2(double value)
    {
        return (Math.log(value) / Math.log(2));
    }

    public double getScore()
    {
        return score;
    }

    private double termFrequency(double wordFrequency, double maxFrequency)
    {
        return wordFrequency / maxFrequency;
    }

    private double inverseDocumentFrequency(double numberOfDocuments, double numberOfRelevantDocuments)
    {
        if (numberOfRelevantDocuments == 0)
        {
            return numberOfDocuments;
        }
        return log2(numberOfDocuments / numberOfRelevantDocuments);
    }
}
