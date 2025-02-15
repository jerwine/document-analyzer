package ie.demo.service;

import ie.demo.mapper.DocumentMapper;
import ie.demo.model.LongestWordDTO;
import ie.demo.model.WordCountDTO;

import java.util.*;
import java.util.stream.Collectors;

public class WordUtils {

    static final List<String> COMMON_WORDS = List.of("the","me","you","i","of","and","a","we");

    public static List<WordCountDTO> extractTopWordFrequencies(
            DocumentMapper documentMapper, String text, int resultCount, boolean descending) {

        Map<String, Integer> wordCount = new HashMap<>();
        extractWords(text)
                .forEach(word -> wordCount.put(word, wordCount.getOrDefault(word, 0) + 1));

        return wordCount.entrySet().stream()
                .map(entry -> documentMapper.toWordCountDTO(entry.getKey(), entry.getValue()))
                .sorted(descending ?
                        Comparator.comparingInt(WordCountDTO :: getFrequency).reversed() :
                        Comparator.comparingInt(WordCountDTO :: getFrequency))
                .limit(resultCount)
                .collect(Collectors.toList());
    }

    public static List<String> extractLongestWords(String text) {
        List<String>words = extractWords(text);
        final int maxLength = words.stream().mapToInt(String::length).max().orElse(0);
        return words.stream().filter(word -> word.length() == maxLength).collect(Collectors.toList());
    }

    private static List<String> extractWords(String text) {
        return Arrays.stream(text.toLowerCase().split("\\W+"))
                .filter(word -> !word.isEmpty() && !COMMON_WORDS.contains(word))
                .collect(Collectors.toList());
    }

    public static LongestWordDTO create(String word, List<String> synonyms) {
        LongestWordDTO lw = new LongestWordDTO();
        lw.setValue(word);
        lw.setSynonyms(synonyms);
        return lw;
    }
}
