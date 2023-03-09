package org.example.parser.parsersTemplates;

import org.example.model.Data;
import org.example.model.LinkWithHtml;
import org.example.parser.engine.Parser;
import org.example.parser.parsersTemplates.inerface.ParserTemplateInterface;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseMatch implements ParserTemplateInterface {
    @Override
    public void parsePage(LinkWithHtml linkWithHtml) {

        Document document = Jsoup.parse(linkWithHtml.getHtml());

        String sport; // Футбол
        sport = document.select("#app > section > div > main > div > div > div > div > div:nth-child(1) > div.sport-event-details__breadcumbs-holder > div > div.sportline-breadcrumbs__inner > div > ol > li:nth-child(1) > div > div.popper__reference > a > div > span").text();
        String league; // Европа Лига Чемпионов
        league = document.select("#app > section > div > main > div > div > div > div > div:nth-child(1) > div.sport-event-details__breadcumbs-holder > div > div.sportline-breadcrumbs__inner > div > ol > li:nth-child(2) > div > div.popper__reference > a > div.breadcrumb__title > span").text()
                + " - "
                + document.select("#app > section > div > main > div > div > div > div > div:nth-child(1) > div.sport-event-details__breadcumbs-holder > div > div.sportline-breadcrumbs__inner > div > ol > li:nth-child(3) > div > div.popper__reference > a > div.breadcrumb__title > span").text();

        String gameName; // Ливерпуль - Реал Мадрид
        gameName = document.select("#app > section > div > main > div > div > div > div > div:nth-child(1) > div.sport-event-details__breadcumbs-holder > div > div.sportline-breadcrumbs__inner > div > ol > li:nth-child(4) > div > div.popper__reference > div > div.breadcrumb__title > span").text();

        String date; //2022-05-28 19:00:00 UTC
        date = document.select("#app > section > div > main > div > div > div > div > div:nth-child(1) > div.sport-event-details-headline-holder > div > div > div > div > div > div:nth-child(2) > div.headline-info__date").text();
        String idGame = ""; // 1970324839951653


        Pattern pattern = Pattern.compile("(?<=\\/)(\\d+)(?=-)");
        Matcher matcher = pattern.matcher(linkWithHtml.getLink().getLink());

        if (matcher.find()) {
            idGame = matcher.group(1);
        }

        List<String> infoS = new ArrayList<>();

        for (Element e : document.getElementsByClass("sport-event-details-market-group sport-event-details-market-list__block")) {
            String info = "    " + e.getElementsByClass("sport-event-details-market-group__title-label").text() + "\n";
            for (Element ele : e.getElementsByClass("sport-event-details-item__runner-holder"))
                info += "        " + ele.text() + "\n";
            infoS.add(info);
        }

        Data d = new Data();
        d.setSport(sport);
        d.setLeague(league);
        d.setDate(date);
        d.setIdGame(idGame);
        d.setGameName(gameName);
        d.setInfo(infoS);

        Parser.addData(d);

        System.out.println(d.toSting());

    }
}
