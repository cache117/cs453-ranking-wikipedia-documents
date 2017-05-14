package edu.byu.cstaheli.cs453;

import edu.byu.cstaheli.cs453.index.Index;
import edu.byu.cstaheli.cs453.index.IndexEntry;
import edu.byu.cstaheli.cs453.process.DocumentProcessor;
import edu.byu.cstaheli.cs453.process.Query;
import edu.byu.cstaheli.cs453.process.QueryResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.SortedSet;
import java.util.stream.Stream;

/**
 * Created by cstaheli on 5/12/2017.
 */
public class Driver
{
    private Index index;

    public Driver()
    {
        index = Index.getInstance();
    }

    public static void main(String[] args)
    {
        Driver driver = new Driver();
        driver.readInCorpus("src/main/resources");
        String[] query = {
//                "James Bond Actors",
                "killing incident",
                "suspect charged with murder",
                "court",
                "jury sentenced murderer to prison",
                "movie",
                "entertainment films",
                "court appeal",
                "action film producer",
                "drunk driving accusations",
                "actor appeared in movie premiere"
        };
        for (String queryString : query)
        {
            SortedSet<QueryResult> results = driver.runQuery(queryString);
            driver.handleResults(queryString, results);
        }
    }

    private void handleResults(String queryString, SortedSet<QueryResult> results)
    {
        System.out.println("Query: " + queryString);
        for (QueryResult result : results)
        {
            System.out.printf("Doc: %d, Score: %.4f\n", result.getDocumentId(), result.getRankingScore());
        }
        System.out.println();
    }

    private SortedSet<QueryResult> runQuery(String queryString)
    {
        Query query = new Query(queryString);
        return query.getResults();
    }

    public void readInCorpus(String directory)
    {
        try (Stream<Path> paths = Files.walk(Paths.get(directory)))
        {
            paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .filter(path -> path.toString().contains("Doc"))
                    .forEach(filePath ->
                    {
                        String fileName = filePath.toString();
                        DocumentProcessor documentProcessor = new DocumentProcessor(fileName);

                        Map<String, Integer> wordCounts = documentProcessor.getWordCounts();
                        for (Map.Entry<String, Integer> entry : wordCounts.entrySet())
                        {
                            IndexEntry indexEntry = new IndexEntry(entry.getKey(), entry.getValue(), Integer.parseInt(fileName.replaceAll("[^0-9]", "")));
                            index.addEntry(indexEntry);
                        }
                    });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("");
    }
}
