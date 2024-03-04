package pt.upskill.RefugeeLINK.Enums;

public enum Language {
    ENGLISH("English"),
    MANDARIN("Mandarin"),
    SPANISH("Spanish"),
    HINDI("Hindi"),
    BENGALI("Bengali"),
    PORTUGUESE("Portuguese"),
    RUSSIAN("Russian"),
    JAPANESE("Japanese"),
    GERMAN("German"),
    KOREAN("Korean"),
    FRENCH("French"),
    ITALIAN("Italian"),
    TURKISH("Turkish"),
    UKRAINIAN("Ukrainian"),
    ARABIC("Arabic");

    private final String name;

    Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

