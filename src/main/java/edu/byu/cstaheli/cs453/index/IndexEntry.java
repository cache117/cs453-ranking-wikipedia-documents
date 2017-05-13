package edu.byu.cstaheli.cs453.index;

import java.util.Comparator;

/**
 * Created by cstaheli on 5/12/2017.
 */
public class IndexEntry implements Comparable<IndexEntry>
{
    private String word;
    private int frequency;
    private String documentId;

    public IndexEntry(String word, int frequency, String documentId)
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
        if (!word.equals(that.word)) return false;
        return documentId.equals(that.documentId);
    }

    @Override
    public int hashCode()
    {
        int result = word.hashCode();
        result = 31 * result + frequency;
        result = 31 * result + documentId.hashCode();
        return result;
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

    public String getDocumentId()
    {
        return documentId;
    }
}
