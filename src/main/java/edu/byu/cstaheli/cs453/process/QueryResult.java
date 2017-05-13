package edu.byu.cstaheli.cs453.process;

/**
 * Created by cstaheli on 5/13/2017.
 */
public class QueryResult implements Comparable<QueryResult>
{
    private final int documentId;
    private final double rankingScore;

    public QueryResult(Integer documentId, double rankingScore)
    {
        this.documentId = documentId;
        this.rankingScore = rankingScore;
    }

    public int getDocumentId()
    {
        return documentId;
    }

    public double getRankingScore()
    {
        return rankingScore;
    }

    @Override
    public int compareTo(QueryResult other)
    {
        int scoreComparison = Double.compare(this.rankingScore, other.rankingScore);
        if (scoreComparison != 0)
        {
            return scoreComparison;
        }
        else
        {
            return Integer.compare(this.documentId, other.documentId);
        }
    }
}
