package edu.byu.cstaheli.cs453.process;

import edu.byu.cstaheli.cs453.index.Index;
import edu.byu.cstaheli.cs453.rank.QueryRanker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cstaheli on 5/13/2017.
 */
public class Query
{
    private Index index;
    private List<QueryResult> results;

    public Query(String query)
    {
        index = Index.getInstance();
        List<String> processedWords = new LineProcessor(query).getProcessedWords();
        for (Integer document : index.getAllDocumentIds())
        {
            QueryRanker ranker = new QueryRanker(document, processedWords);
            double score = ranker.getScore();
        }
    }

    public List<QueryResult> getResults()
    {
        return results;
    }
}
