package org.example.ds;

import java.util.HashMap;
import java.util.Map;

/** https://leetcode.com/problems/design-add-and-search-words-data-structure/description/ */
public class WordDictionary {

  private static class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    int wordCount = 0;
  }

  private final TrieNode root = new TrieNode();

  public void addWord(String word) {
    var curr = root;
    for (char c : word.toCharArray()) {
      curr.children.computeIfAbsent(c, _ -> new TrieNode());
      curr = curr.children.get(c);
    }
    curr.wordCount++;
  }

  public boolean search(String word) {
    return searchPattern(word, 0, root);
  }

  private boolean searchPattern(String word, int index, TrieNode curr) {
    if (curr == null) {
      return false;
    }
    if (curr.wordCount > 0 && index == word.length()) {
      return true;
    }
    if (index >= word.length()) {
      return false;
    }
    char c = word.charAt(index);
    if (c == '.') {
      for (TrieNode child : curr.children.values()) {
        if (searchPattern(word, index + 1, child)) {
          return true;
        }
      }
      return false;
    }
    return searchPattern(word, index + 1, curr.children.get(c));
  }

  public static void main(String[] args) {
    WordDictionary dictionary = new WordDictionary();
    dictionary.addWord("complex");
    dictionary.addWord("complication");
    System.out.println(dictionary.search("c.mpl.x"));
    System.out.println(dictionary.search("complic.tion"));
    System.out.println(dictionary.search("..........."));
    System.out.println(dictionary.search("c....."));
  }
}
