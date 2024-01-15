package com.mmd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Review {
    public static void main(String[] args) {
        Pattern phonePattern = Pattern.compile("\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}");
        //Parentheses r used in regular expressions as meta characters, identifying groups in most cases.
        //If u want a literal U have to prefix(escape) it with \\
        //after that u v character class of range 0-9. and {3} is a quantifier saying exactly 3 numbers.
        // - does not need to be escaped in Java.
        String phoneList = """
                (800) 123-4567
                (800)123-4567
                (800) 123 4567
                800-123-4567
                800 123-4567
                800 123 4567
                8001234567
                """;

        Matcher phonePatternMather = phonePattern.matcher(phoneList);
        phonePatternMather.results().forEach(mr-> System.out.println(mr.group(0)));
        System.out.println("-".repeat(20));


        phonePatternMather.usePattern(Pattern.compile("\\([0-9]{3}\\)\\s?[0-9]{3}-[0-9]{4}"));
        phonePatternMather.reset();
        phonePatternMather.results().forEach(mr-> System.out.println(mr.group(0)));
        System.out.println("-".repeat(20));

        phonePatternMather.usePattern(Pattern.compile("\\([0-9]{3}\\)\\s?[0-9]{3}[-\\s][0-9]{4}"));
        phonePatternMather.reset();
        phonePatternMather.results().forEach(mr-> System.out.println(mr.group(0)));
        System.out.println("-".repeat(20));

        phonePatternMather.usePattern(Pattern.compile("\\(?[0-9]{3}\\)?[-\\s]?[0-9]{3}[\\s-][0-9]{4}"));
        phonePatternMather.reset();
        phonePatternMather.results().forEach(mr-> System.out.println(mr.group(0)));
        System.out.println("-".repeat(20));

        phonePatternMather.usePattern(Pattern.compile("\\(?[0-9]{3}\\)?[-\\s]?\\d{3}[\\s-]?\\p{Digit}{4}"));
        phonePatternMather.reset();
        phonePatternMather.results().forEach(mr-> System.out.println(mr.group(0)));
        System.out.println("-".repeat(20));

        String htmlSnippets = """
                <H1>My Heading</h1>
                <h2>Sub-heading</h2>
                <p>This is a paragraph about something.</p>
                <p style="abc">This is another paragraph about something else.</p>
                <h3 id="third">Summary</h3>
                <br/>
                <p>Testing</p>
                """;
        Pattern htmlPattern= Pattern.compile("<(\\w+)[^>]*>([^\\v</>]*)(</\\1>)*", Pattern.CASE_INSENSITIVE);
        //U can specify as the 2nd arg Pattern.CASE_INSENSITIVE to ignore case for the entire pattern.
        Matcher htmlPatternMatcher = htmlPattern.matcher(htmlSnippets);
        htmlPatternMatcher.results().forEach(mr-> System.out.println(mr.group()));
    }
}
