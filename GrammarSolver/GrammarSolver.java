// Nicolas Neven
// 2/8/18
// CSE 143 AO
// TA: Benjamin Macmillan
// Assignment #5
//
// This class uses a grammar list to perform certain tasks of the grammar, most importantly 
// generating random rules based of the given nonterminals.

import java.util.*;
public class GrammarSolver {

   private SortedMap<String, String[]> grammarMap;
   
   // Pre : Takes in a grammar (list) as a paramater and throws an IllegalArgumentException
   //       if the grammar is empty or if there are two or more entries in the grammar for the
   //       same non terminal.
   // Post: Splits the grammar list so each nonterminal corresponds with its rules.
   public GrammarSolver(List<String> grammar) {
      if (grammar.isEmpty() || duplicate(grammar)) {
         throw new IllegalArgumentException();
      }
      grammarMap = new TreeMap<String, String[]>();
      for (String s : grammar) {
         String[] nonTerminal = s.split("::=");
         String[] rule = nonTerminal[1].split("[|]");
         grammarMap.put(nonTerminal[0], rule);
      }
   }
   
   // Pre : Takes in a symobl (string) as a paramater.
   // Post: Returns trye if the given symbol is a nonterminal of the grammar and false otherwise.
   public boolean grammarContains(String symbol) {
      return grammarMap.containsKey(symbol);
   }
   
   // Pre : Takes in a symbol (string) and the required number of occurences (int) to generate.
   //       Throws an IllegalArgumentException if the grammar does not contain the given
   //       nonterminal or if the number of occurences given is less than 0.
   // Post: Randomly generates the given number of occurences of a given symbol and returns
   //       the result as an array of strings. The resulting array of strings have no leading
   //       or trailing whitespace.
   public String[] generate(String symbol, int times) {
      if (!grammarContains(symbol) || times < 0) {
         throw new IllegalArgumentException();
      }
      String[] grammarArray = new String[times];
      String[] parts;
      for (int i = 0; i < times; i++) {
         grammarArray[i] = rule(symbol).trim();
      }
      return grammarArray;
   }
   
   // Post: Returns a string representation of the various nonterminal symbols from the grammar
   //       as a sprted, comma-seperated list enclosed in square brackets.
   public String getSymbols() {
      return grammarMap.keySet().toString();
   }
   
   // Pre : Takes in symbol (string) as a paramater.
   // Post: Randomly generates a rule from the given string to return. If the generated rule is
   //       a nonterminal, it keeps generating until it is no longer a nonterminal. Returns a
   //       string of the rules in the order they were generated.
   private String rule(String symbol) {
      int size = (int)(Math.random() * grammarMap.get(symbol).length);
      String[] parts = grammarMap.get(symbol)[size].split("\\s+");
      String result = "";
      for (int i = 0; i < parts.length; i++) {
         String temp = parts[i];
         if (grammarMap.containsKey(temp)) {
            result += rule(temp).trim() + " ";
         } else {
            result += temp.trim() + " ";
         }
      }
      return result;
   }
   
   // Pre : Takes in a grammar (list) as a paramater.
   // Post: Checks the grammar for duplicates and returns true if there are 2 or more entries
   //       for the same nonterminal and false if there is only one.
   private boolean duplicate(List<String> grammar) {
      Set<String> nonTerminal = new HashSet<String>();
      for (String s : grammar) {
         if(!nonTerminal.add(s.split("::=")[0])) {
            return true;
         }
      }
      return false;
   }
}