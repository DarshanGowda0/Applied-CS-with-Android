package com.google.engedu.ghost;

import java.util.HashMap;


public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {

        //get the root node in this object
        TrieNode currentNode = this;
        String key;

        //loop through all the chars in the word
        for (int i = 0; i < s.length(); i++) {
            //fetch individual characters in the word and check if that char exists as a key
            key = "" + s.charAt(i);
            if (!currentNode.children.containsKey(key)) {
                //if it doesn't exist then add it as a new key with empty node as child
                currentNode.children.put(key, new TrieNode());
            }
            //go to the next child in that node( with next char as key)
            currentNode = currentNode.children.get(key);
        }

        //make the boolean true after the whole word is added
        currentNode.isWord = true;

    }


    public boolean isWord(String s) {

        //get the current node of that object
        TrieNode currentNode = this;
        String key;

        //maintain a boolean to check for end of word
        boolean isFound = false;

        //loop through all the chars
        for (int i = 0; i < s.length(); i++) {
            key = "" + s.charAt(i);
            //if the current char is present as a key , then go to its child node and further so on
            if (currentNode.children.containsKey(key)) {
                currentNode = currentNode.children.get(key);
            }
            //if any char is not found , then break out of the loop
            else {
                isFound = false;
                return isFound;
            }
        }

        //if the loop was completed with all chars present in the node, then make the boolean true and return it.
        if (currentNode.isWord) {
            isFound = true;
        }
        return isFound;

    }

    public String getAnyWordStartingWith(String s) {
        return null;
    }

    public String getGoodWordStartingWith(String s) {
        return null;
    }
}
