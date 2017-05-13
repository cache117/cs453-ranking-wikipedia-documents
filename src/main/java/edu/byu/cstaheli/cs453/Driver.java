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
import java.util.List;
import java.util.Map;
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
        List<QueryResult> results = driver.runQuery("killing incident");
        driver.handleResults(results);
        results = driver.runQuery("suspect charged with murder");
        driver.handleResults(results);
        results = driver.runQuery("court");
        driver.handleResults(results);
        results = driver.runQuery("jury sentenced murderer to prison");
        driver.handleResults(results);
        results = driver.runQuery("movie");
        driver.handleResults(results);
        results = driver.runQuery("entertainment films");
        driver.handleResults(results);
        results = driver.runQuery("court appeal");
        driver.handleResults(results);
        results = driver.runQuery("action film producer");
        driver.handleResults(results);
        results = driver.runQuery("drunk driving accusations");
        driver.handleResults(results);
        results = driver.runQuery("actor appeared in movie premiere");
        driver.handleResults(results);
    }

    private void handleResults(List<QueryResult> results)
    {
        for (QueryResult result : results)
        {

        }
    }

    private List<QueryResult> runQuery(String queryString)
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
