package edu.byu.cstaheli.cs453.index;

import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by cstaheli on 5/12/2017.
 */
public class Index
{
    private SortedSetMultimap<String, IndexEntry> entries;

    public Index()
    {
        entries = TreeMultimap.create(Comparator.naturalOrder(), Comparator.comparing(IndexEntry::getDocumentId));
    }

    public void addEntry(IndexEntry entry)
    {
        entries.put(entry.getWord(), entry);
    }

    public SortedSet<IndexEntry> getEntriesOfWord(String word)
    {
        return entries.get(word);
    }

    public int size(String word)
    {
        return getEntriesOfWord(word).size();
    }

    public int size()
    {
        return entries.size();
    }

    public SortedSet<Integer> getDocumentIdsWhereWordPresent(String word)
    {
        return getEntriesOfWord(word)
                .stream()
                .map(IndexEntry::getDocumentId)
                .collect(Collectors.toCollection(TreeSet<Integer>::new));
    }

    public int totalFrequencyOfWord(String word)
    {
        return getEntriesOfWord(word)
                .stream()
                .mapToInt(IndexEntry::getFrequency)
                .sum();

    }
}
