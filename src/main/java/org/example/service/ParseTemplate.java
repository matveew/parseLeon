package org.example.service;

import org.example.model.Link;
import org.example.model.LinkWithHtml;
import org.example.parser.engine.Parser;
import org.example.parser.parsersTemplates.ParseMatch;
import org.example.parser.parsersTemplates.ParseSelectedLeagueMatches;
import org.example.parser.parsersTemplates.ParseTopOfLeague;

public class ParseTemplate {


    public static void runParseTemplate() {
        while (true) {

            LinkWithHtml link = Parser.getLinkAndHtml();
            if (link == null) {
                try {
                    Thread.currentThread().sleep(Parser.DELAY);
                    continue;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("runParseTemplate, link: "+link.getLink());

            if (link.getLink().getTypePage().equals(Link.TypePage.TOP_LEAGUE)) {
                new ParseTopOfLeague().parsePage(link);
            }

            if (link.getLink().getTypePage().equals(Link.TypePage.LEAGUE)) {
                new ParseSelectedLeagueMatches().parsePage(link);
            }

            if (link.getLink().getTypePage().equals(Link.TypePage.MATCH)) {
                new ParseMatch().parsePage(link);
            }
        }
    }


}
