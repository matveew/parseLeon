package org.example.parser.engine.jsoup;

public class JsoupParser {
// doesn't work for leon.bet. We're sorry but client doesn't work properly without JavaScript enabled. Please enable it to continue.
    public static int COUNT_THREAD = 3;

    public static void runJsoupParser() {
        for (int i = 0; i <= COUNT_THREAD; i++) {
            new Thread(new JsoupThread()).start();
        }
    }
}
