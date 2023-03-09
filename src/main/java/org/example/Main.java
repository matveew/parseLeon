package org.example;

import org.example.model.Link;
import org.example.parser.engine.Parser;
import org.example.parser.engine.selenium.ChromeParser;
import org.example.service.ParseTemplate;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        Parser.addLink(new Link("https://leon.bet/ru-ua/bets/soccer", Link.TypePage.TOP_LEAGUE));
       Parser.addLink(new Link("https://leon.bet/ru-ua/bets/hockey", Link.TypePage.TOP_LEAGUE));
      Parser.addLink(new Link("https://leon.bet/ru-ua/bets/basketball", Link.TypePage.TOP_LEAGUE));

        ChromeParser.runChromeParser();
        ParseTemplate.runParseTemplate();

    }
}