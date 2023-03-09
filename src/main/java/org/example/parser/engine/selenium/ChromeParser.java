package org.example.parser.engine.selenium;

public class ChromeParser {


    public static int COUNT_THREAD = 1;

    public static void runChromeParser() {
        for (int i = 0; i < COUNT_THREAD; i++) {
            new Thread(new ChromeThread()).start();
        }
    }


}
