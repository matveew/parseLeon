package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Link {
    private String link;
    private TypePage typePage;

    public enum TypePage {
        TOP_LEAGUE,
        LEAGUE,
        MATCH
    }
}
