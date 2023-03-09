package org.example.parser.engine.selenium;

import org.example.model.Link;
import org.example.parser.engine.Parser;

public class ChromeThread implements Runnable {

    private Chrome chrome;

    @Override
    public void run() {
        startChrome();
        while (true) {
            Link link = Parser.getAndRemove();
            if (link == null) {
                try {
                    Thread.currentThread().sleep(Parser.DELAY);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("parse link: " + link.getLink());
                chrome.get(link.getLink());
                Parser.addLinkAndHtml(link, chrome.getPageSource());
                chrome.delaySecond(3);
            }
        }
    }


    private void startChrome() {
        chrome = new Chrome();
    }

}
