package org.example.parser.engine;

import org.example.model.Data;
import org.example.model.Link;
import org.example.model.LinkWithHtml;

import java.util.*;

public class Parser {

    public static int DELAY = 3000; // ms
    private static List<Link> links = new ArrayList<>();
    private static List<LinkWithHtml> linksWithHtml = new ArrayList<>();

    private static List<Data> dataOfMatch = new ArrayList<>();

    public static synchronized Link getAndRemove() {
        if (links.size() == 0)
            return null;
        Link result = links.get(0);
        links.remove(0);
        return result;
    }

    public static void addLink(Link link) {
        links.add(link);
    }


    public static synchronized LinkWithHtml getLinkAndHtml() {
        if (linksWithHtml.size() == 0)
            return null;
        LinkWithHtml result = linksWithHtml.get(0);
        linksWithHtml.remove(0);
        return result;
    }

    public static synchronized void addLinkAndHtml(Link link, String html) {
        linksWithHtml.add(new LinkWithHtml(link, html));
    }

    public static synchronized void addData(Data data) {
        dataOfMatch.add(data);
    }


}
