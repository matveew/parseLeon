package org.example.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@lombok.Data
public class Data {
    String sport;
    String league;
    String gameName;
    String idGame;
    String date;

    List<String> info = new ArrayList<>();


    public String toSting() {
        String result =
                sport + ", " + league + "\n"
                        + gameName + ", " + date + ", " + idGame + "\n";
        for (String s: info){
            result+=s;
        }

        return result;
    }
}
