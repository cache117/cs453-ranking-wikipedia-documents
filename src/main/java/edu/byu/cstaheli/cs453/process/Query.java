package edu.byu.cstaheli.cs453.process;

import edu.byu.cstaheli.cs453.index.Index;
import edu.byu.cstaheli.cs453.rank.QueryRanker;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by cstaheli on 5/13/2017.
 */
public class Query
{
    private Index index;
    private TreeSet<QueryResult> results;

    public Query(String query)
    {
        results = new TreeSet<>();
        index = Index.getInstance();
        List<String> processedWords = new LineProcessor(query).getProcessedWords();
        for (Integer document : index.getAllDocumentIds())
        {
            QueryRanker ranker = new QueryRanker(document, processedWords);
            double score = ranker.getScore();
            QueryResult result = new QueryResult(document, score);
            addResult(result);
        }
    }

    private void addResult(QueryResult result)
    {
        results.add(result);
        if (results.size() > 10)
        {
            results.pollLast();
        }
    }

    public SortedSet<QueryResult> getResults()
    {
        return results;
    }
}
