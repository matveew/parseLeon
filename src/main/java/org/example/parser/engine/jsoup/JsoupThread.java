package org.example.parser.engine.jsoup;

import org.example.model.Link;
import org.example.parser.engine.Parser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupThread implements Runnable {


    @Override
    public void run() {

        while (true) {

            Link link = Parser.getAndRemove();
            if (link == null) {
                try {
                    Thread.currentThread().sleep(Parser.DELAY);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    System.out.println("parse link: " + link.getLink());
                    Connection connection = Jsoup.connect(link.getLink());
                    Thread.currentThread().sleep(Parser.DELAY);
                    Document doc = connection.get();
                    Parser.addLinkAndHtml(link, doc.html());
                } catch (IOException e) {
                    Parser.addLink(link);
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
