package com.ahoububy.movies.models;

public enum TypeMovie {
    ACTION("ACTION"), ADVENTURE("ADVENTURE"), ANIMATION("ANIMATION"),
    COMEDY("COMEDY"), DRAMA("DRAMA"), HORROR("HORROR"),
    SCIFI("SCIFI"), THRILLER("THRILLER"), ROMANCE("ROMANCE");
    private String typeString;

    TypeMovie(String value) {
        this.typeString = value;
    }

    public String value() {
        return typeString;
    }
}
