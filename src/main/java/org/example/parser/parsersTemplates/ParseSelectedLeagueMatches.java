package org.example.parser.parsersTemplates;

import org.example.model.Link;
import org.example.model.LinkWithHtml;
import org.example.parser.engine.Parser;
import org.example.parser.parsersTemplates.inerface.ParserTemplateInterface;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class ParseSelectedLeagueMatches implements ParserTemplateInterface {
    @Override
    public void parsePage(LinkWithHtml linkWithHtml) {
        String page = linkWithHtml.getHtml();
        Document document = Jsoup.parse(page);
        for (int i = 1; ; i++) {
            Elements element = document.select("#app > section > div > main > div > div > div.component-wrapper > div > div:nth-child(3) > div.SportEventsListSportElement_content__OicB > div > div > div.LeagueElementInner_content_DbZOx > div:nth-child(" + i + ") > div > span > a");
            String link = element.attr("href");

            if (!link.isBlank()) {
                link = "https://leon.bet" + link;
                Parser.addLink(new Link(link, Link.TypePage.MATCH));
            } else {
                //  check for failed page load
                return;
            }
        }
    }
}
