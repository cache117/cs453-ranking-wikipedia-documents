package edu.byu.cstaheli.cs453;

import edu.byu.cstaheli.cs453.index.Index;
import edu.byu.cstaheli.cs453.index.IndexEntry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        index = new Index();
    }

    public static void main(String[] args)
    {
        Driver driver = new Driver();
        driver.readInCorpus("src/main/resources");
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
