package edu.byu.cstaheli.cs453.index;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import java.util.*;

/**
 * Created by cstaheli on 5/12/2017.
 */
public class Index
{
    private Multimap<String, IndexEntry> entries;

    public Index()
    {
        entries = TreeMultimap.create(Comparator.naturalOrder(), Comparator.comparing(IndexEntry::getDocumentId));
    }

    public void addEntry(IndexEntry entry)
    {
        entries.put(entry.getWord(), entry);
    }

    public Collection<IndexEntry> getEntriesOfWord(String word)
    {
        return entries.get(word);
    }
}
