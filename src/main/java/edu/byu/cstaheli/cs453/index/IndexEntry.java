package edu.byu.cstaheli.cs453.index;

/**
 * Created by cstaheli on 5/12/2017.
 */
public class IndexEntry implements Comparable<IndexEntry>
{
    private final String word;
    private final int frequency;
    private final int documentId;

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

    @Override
    public int compareTo(IndexEntry other) {
        int frequencyComparison = Integer.compare(this.frequency, other.frequency);
        //overall ordering is flipped (Index uses reversed ordering for IndexEntry),
        // so flip the word and document id comparisons
        if (frequencyComparison == 0)
        {
            int wordComparison = other.word.compareTo(this.word);
            if (wordComparison == 0)
            {
                return Integer.compare(other.getDocumentId(), this.documentId);
            }
            else
            {
                return wordComparison;
            }
        }
        else
        {
            return frequencyComparison;
        }
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
