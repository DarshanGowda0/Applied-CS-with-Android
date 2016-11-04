package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    public ArrayList<String> wordList = new ArrayList<>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {


        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();

            //add to the arrayList data structure
            wordList.add(word);

        }
    }

    public boolean isGoodWord(String word, String base) {
        return true;
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

        //sort the target word
        String sortedTargetWord = sortLetters(targetWord);

        //first step is to iterate through all 10000 words and find the anagrams
        for(String word : wordList){
            //sort the word
            String sortedWord = sortLetters(word);

            //if it matches to sortedTargetWord, then it's an anagram of it
            if(sortedTargetWord.equals(sortedWord)){
                //add the original word
                result.add(word);
            }
        }

        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }

    public String sortLetters(String word) {
        char[] words = word.toCharArray();
        Arrays.sort(words);
        return new String(words);
    }
}
