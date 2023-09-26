package edu.kmaooad.capstone23.members.utils;

public interface CorpEmailParser {
    /**
     * @param email email to parse
     * @return domain of the email, null if malformed
     */
    String getCorpEmailDomain(String email);
}
