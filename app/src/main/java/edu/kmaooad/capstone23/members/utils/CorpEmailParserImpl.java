package edu.kmaooad.capstone23.members.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorpEmailParserImpl implements CorpEmailParser {
    @Override
    public String getCorpEmailDomain(String email) {
        if (email == null) return null;
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
