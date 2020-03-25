package com.charsharing.bootcamp.filter.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service

public class FilterService {

    //Profanity words mapped to their first letter
    private Map<String, List<String>> profanityWordMap;
    boolean valid = false;

    public FilterService() {
        InputStream in = FilterService.class.getResourceAsStream("profanityWordList.json");
        ObjectMapper om = new ObjectMapper();
        try {
            profanityWordMap = om.readValue(in, Map.class);
        } catch (IOException e) {
            profanityWordMap = new HashMap<>();
        }
    }


    public String filterText(String text) {

        List<String> filteredText = new ArrayList<>();
        String[] textArray = text.split("\\s+");
        String wordNoPunctuation ="";
        valid = false;
        Pattern pattern = Pattern.compile("[a-zA-Z]*");

        for (String s : textArray) {
            Matcher matcher = pattern.matcher(s);

            if(!matcher.matches()){
                wordNoPunctuation = s.replaceAll("[^a-zA-Z]", "");
            } else {
                wordNoPunctuation = s;
            }

            if(wordNoPunctuation.length() <= 1){
                filteredText.add(s);
            }
            else if (profanityWordMap.get(String.valueOf(wordNoPunctuation.toLowerCase().charAt(0))).contains(wordNoPunctuation.toLowerCase())) {
                valid = true;
                filteredText.add(keepPunctuation(s));
            } else {
                filteredText.add(s);
            }
        }
        return String.join(" ", filteredText);
    }

    public String keepPunctuation(String word) {
        switch (word.charAt(word.length() - 1)) {
            case '.':
                return "*****.";
            case '!':
                return "*****!";
            case '?':
                return "*****?";
            case ',':
                return "*****,";
            case ';':
                return "*****;";
            case ':':
                return "*****:";
            default:
                return "*****";
        }
    }

    public boolean isValid() {
        return valid;
    }
}
