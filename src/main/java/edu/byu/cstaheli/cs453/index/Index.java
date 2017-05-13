package edu.byu.cstaheli.cs453.index;

import com.google.common.collect.Ordering;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by cstaheli on 5/12/2017.
 */
public class Index
{
    private static Index _instance;
    //    private SortedSetMultimap<String, IndexEntry> entries;
    private SortedSetMultimap<Integer, IndexEntry> entries;

    private Index()
    {
        entries = TreeMultimap.create(Ordering.natural(), Ordering.natural().reversed());
    }

    public static Index getInstance()
    {
        if (_instance == null)
        {
            _instance = new Index();
        }
        return _instance;
    }

    public void addEntry(IndexEntry entry)
    {
        entries.put(entry.getDocumentId(), entry);
    }

    public SortedSet<Integer> getAllDocumentIds()
    {
        return (SortedSet<Integer>) entries.keySet();
    }

    public SortedSet<IndexEntry> getEntriesOfWord(String word)
    {
//        return entries.get(word);
        return entries
                .values()
                .stream()
                .filter(i -> word.equals(i.getWord()))
                .collect(Collectors.toCollection(TreeSet<IndexEntry>::new));
    }

    public SortedSet<IndexEntry> getEntriesOfWordInDocument(String word, int documentId)
    {
        return getEntriesFromDocument(documentId)
                .stream()
                .filter(i -> word.equals(i.getWord()))
                .collect(Collectors.toCollection(TreeSet<IndexEntry>::new));
    }

    public int getFrequencyOfWordInDocument(String word, int documentId)
    {
        return getEntriesOfWordInDocument(word, documentId)
                .stream()
                .mapToInt(IndexEntry::getFrequency)
                .sum();
    }

    public int getNumberOfDocumentsWordIsPresentIn(String word)
    {
        return getDocumentIdsWhereWordPresent(word)
                .size();
    }

    public SortedSet<IndexEntry> getEntriesFromDocument(int documentId)
    {
        return entries.get(documentId);
    }

//    public int size(String word)
//    {
//        return getEntriesOfWord(word).size();
//    }

    public int size()
    {
        return getAllDocumentIds().size();
    }

    public SortedSet<Integer> getDocumentIdsWhereWordPresent(String word)
    {
        return getEntriesOfWord(word)
                .stream()
                .map(IndexEntry::getDocumentId)
                .collect(Collectors.toCollection(TreeSet<Integer>::new));
    }

//    public int totalFrequencyOfWord(String word)
//    {
//        return getEntriesOfWord(word)
//                .stream()
//                .mapToInt(IndexEntry::getFrequency)
//                .sum();
//    }

    public int mostFrequentWordFrequency(int documentId)
    {
        return getEntriesOfWordInDocument(mostFrequentWord(documentId), documentId)
                .stream()
                .mapToInt(IndexEntry::getFrequency)
                .sum();
    }

    public String mostFrequentWord(int documentId)
    {
        return entries
                .get(documentId)
                .first()
                .getWord();
    }

//    /**
//     * This method iterates through the values of the {@link TreeMultimap},
//     * searching for {@link IndexEntry} objects which have their {@code word}
//     * field equal to the parameter, word.
//     *
//     * @param word The word to search for in every document.
//     * @return A {@link List<Pair<Integer, Integer>>} where each {@link Pair<>}
//     * will hold the document's ID as its first element and the frequency
//     * of the word in the document as its second element.
//     * <p>
//     * Note that the {@link Pair} object is defined in javafx.util.Pair
//     */
//    public List<Pair<Integer, Integer>> totalWordUses(String word)
//    {
//        return entries.values()
//                .stream()
//                .filter(i -> word.equals(i.getWord()))
//                .map(i -> new Pair<>(i.getDocumentId(), i.getFrequency()))
//                .collect(Collectors.toList());
//    }
}
