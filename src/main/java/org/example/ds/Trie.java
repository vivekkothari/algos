package org.example.ds;

import java.util.HashMap;
import java.util.Map;

class Trie {

  private final TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  public void insert(String word) {
    var curr = root;
    for (char c : word.toLowerCase().toCharArray()) {
      curr.children.computeIfAbsent(c, _ -> new TrieNode());
      curr = curr.children.get(c);
    }
    curr.wordCount++;
  }

  public boolean search(String word) {
    var curr = searchNode(word);
    // Return true if the word exists
    // and is marked as ending
    return curr != null && curr.wordCount > 0;
  }

  private TrieNode searchNode(String word) {
    TrieNode curr = root;
    for (char c : word.toLowerCase().toCharArray()) {
      if (!curr.children.containsKey(c)) return null;
      curr = curr.children.get(c);
    }
    return curr;
  }

  private boolean isEmpty(TrieNode node) {
    for (TrieNode child : node.children.values()) {
      if (child != null) return false;
    }
    return true;
  }

  public boolean startsWith(String prefix) {
    var node = searchNode(prefix);
    return node != null;
  }

  public void delete(String word) {
    delete(root, word, 0);
  }

  private boolean delete(TrieNode node, String word, int index) {
    if (index == word.length()) {
      if (node.wordCount == 0) return false;
      node.wordCount--;
      return node.children.isEmpty(); // If no children, we can delete this node
    }
    char c = word.charAt(index);
    TrieNode next = node.children.get(c);
    if (next == null) return false; // Word not found

    boolean shouldDeleteChild = delete(next, word, index + 1);

    if (shouldDeleteChild) {
      node.children.remove(c); // Remove child if it's no longer needed
      return node.children.isEmpty() && node.wordCount == 0;
    }
    return false;
  }

  private static class TrieNode {
    private final Map<Character, TrieNode> children = new HashMap<>();
    // Number of words ending at current node.
    int wordCount;
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    //    trie.insert("apple");
    trie.insert("app");
    trie.insert("and");
    trie.delete("app");
    System.out.println(trie.search("apple")); // return True
    System.out.println(trie.search("app")); // return False
    System.out.println(trie.startsWith("app")); // return True
    System.out.println(trie.startsWith("App")); // return True
    trie.insert("app");
    System.out.println(trie.search("app")); // return True
    trie.delete("app");
    System.out.println(trie.search("app")); // return false
    trie.delete("apple"); // return false
  }
}
