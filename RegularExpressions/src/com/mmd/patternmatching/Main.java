package com.mmd.patternmatching;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String... arg) {

        String sentence = "I like B.M.W motorcycles.";
        boolean matched = Pattern.matches("[A-Z].*[.]", sentence);//String regex, charSequence
        System.out.println(matched + " " + sentence);

        Pattern compiledPattern = Pattern.compile("[A-Z].*?[.]");//Create a pattern of it using .compile that returns a pattern.
        var matcher = compiledPattern.matcher(sentence); //Much more efficient than using Pattern.matches. n returns a Matcher.
        System.out.println(matcher.matches());// When u compile a regular exp into a Pattern instance expression u needThis
        //mather.matches() requires that the whole string matches. So it does not honour the request to use the least numb of characters
        //it will always use every character in the string to determine a match.

        System.out.println(sentence.length());
        System.out.println(matcher.end());//The last index that was used in the matching process is returned.

        System.out.println(matcher.lookingAt());//Will always start at the beginning of the string but will stop matching, returning true
        System.out.println(matcher.end());//even before it finds the end of the String. lookingAt doesn't have to match the entire string
        System.out.println("Matched on : " + sentence.substring(0, matcher.end()));

        matcher.reset();//find picks up where the lookingAt left off. If u need to start at the beginning then u need to reset
        System.out.println(matcher.find());//this matcher instance.  lookingAt, n matches don't set an incremental state that will
        System.out.println(matcher.end());                                  // be used in their execution. n will always start at 0
        System.out.println("Matched on : " + sentence.substring(matcher.start(), matcher.end()));
        System.out.println("Matched on : " + matcher.group());// Returns a subsequence of the previous match.

        String htmlSnippet = """
                <H1>My Heading</H1>
                <h2>Sub-heading</h2>
                <p>This is a paragraph about something</p>
                <p>This is another paragraph about something else.</p>
                <h3>Summary</h3>""";

        Pattern htmlPattern = Pattern.compile("<[Hh]\\d>(.*)</[hH]\\d>");//U group the part u want by putting it in ()
        Matcher htmlPatterMatcher = htmlPattern.matcher(htmlSnippet);
        while (htmlPatterMatcher.find()) {
            System.out.println(htmlPatterMatcher.group());//With no argument gets the whole match
            System.out.println(htmlPatterMatcher.group(0));//Gets the whole match
            System.out.println(htmlPatterMatcher.group(1));//Gets the capturing group. because there is only one group
            //If u specify more than 1, ie.2. u get IndexOutOfBoundException since there is no group 2.

        }
        System.out.println("-".repeat(30));

        Pattern htmlPattern2 = Pattern.compile("<[Hh](?<digit>\\d)>(?<text>.*)</[hH]\\d>");//U group the part u want by putting it in ()
        Matcher htmlPatternMatcher2 = htmlPattern2.matcher(htmlSnippet);
        htmlPatterMatcher.find(0);//rests, starts from 0 until finds the first match.
        htmlPatterMatcher.reset();
        while (htmlPatternMatcher2.find()) {
            System.out.println(htmlPatternMatcher2.group("digit") + " " + htmlPatternMatcher2.group(2) + " num of groups: " +
                    htmlPatternMatcher2.groupCount());
            System.out.println(htmlPatternMatcher2.start("digit"));
            System.out.println(htmlPatternMatcher2.end("digit"));
        }//the end n start methods also will either take an numeric or name group reference
        System.out.println("-".repeat(30));

        htmlPatternMatcher2.reset();                                                                                          //matches.
        htmlPatternMatcher2.results()// creates a Stream(MatchResult). Underneath the covers, this uses find() to loop through subsequent
                .forEach(matchResult -> System.out.println(matchResult.group(1) + matchResult.group(2)));
        //MatchResult interface only has group of int(index) arg.

        String tabbedText = """
                group1    group2    group3
                1    2    3
                a    b    c
                """;
        htmlPatternMatcher2.reset();//Again u need to reset the matcher since it gets exhausted by calling .results on it too
        tabbedText.lines()//<Stream<String>
                .flatMap(fs-> Pattern.compile("    ").splitAsStream(fs))
                .forEach(System.out::println);

        htmlPatternMatcher2.reset();
        String updatedSnippet = htmlPatternMatcher2.replaceFirst("First header");//replaceFirst resets the Matcher everytime called
        updatedSnippet = htmlPatternMatcher2.replaceFirst(f-> "<em>"+
                htmlPatternMatcher2.group("text")+"</em>");//This version calls a function<MatchResult,String> after the match
        System.out.println("-".repeat(30));
        System.out.println(updatedSnippet);
        System.out.println(htmlPatternMatcher2.start() + " " + htmlPatternMatcher2.end());

        htmlPatternMatcher2.usePattern(//Here the closing tag uses group 1 using \\1. A back reference to first group.
                Pattern.compile("<([Hh]\\d)>(.*)</\\1>")//Do not mix name reference with backRefernce at all.
        ); //This allows u to use a different pattern. U have to pass a Pattern instance.
        htmlPatternMatcher2.reset();
        System.out.println("-".repeat(30)); //See using back reference to do the same as above. $ then number of group
        System.out.println("Using Back reference:\n" + htmlPatternMatcher2.replaceFirst("<em>$2</em>"));


        String updatedHtml = htmlPatternMatcher2.replaceAll(f-> "<em>"+
                htmlPatternMatcher2.group(2)+"</em>");
        System.out.println("-".repeat(30));
        System.out.println(updatedHtml);

    }
}
