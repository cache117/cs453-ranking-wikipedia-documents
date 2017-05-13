package edu.byu.cstaheli.cs453.index;

import java.util.Comparator;

/**
 * Created by cstaheli on 5/12/2017.
 */
public class IndexEntry implements Comparable<IndexEntry>
{
    private String word;
    private int frequency;
    private int documentId;

    public IndexEntry(String word, int frequency, int documentId)
    {
        this.word = word;
        this.frequency = frequency;
        this.documentId = documentId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexEntry that = (IndexEntry) o;

        if (frequency != that.frequency) return false;
        if (documentId != that.documentId) return false;
        return word.equals(that.word);
    }

    @Override
    public int hashCode()
    {
        int result = word.hashCode();
        result = 31 * result + frequency;
        result = 31 * result + documentId;
        return result;
    }

    @Override
    public String toString()
    {
        return String.format("{%s:%s:%s}", word, documentId, frequency);
    }

    public int compareTo(IndexEntry other)
    {
        return this.word.compareTo(other.word);
    }

    public String getWord()
    {
        return word;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public int getDocumentId()
    {
        return documentId;
    }
}
