package nl.hva.makeitwork.bankit.bankitapplication.model.company;

public enum Industry {
    AGRICULTURE("Landbouw, bosbouw en visserij"),
    MINING("Delfstoffenwinning"),
    INDUSTRY("Industrie"),
    ENERGY("Energie- en waterleidingbedrijven"),
    CONSTRUCTION("Bouwvijverheid"),
    RETAIL("Handel, horeca en reparatie"),
    TRANSPORT("Vervoer, opslag en communicatie"),
    FINANCE("Financiële en zakelijke dienstverlening"),
    GOVERNMENT("Overheid"),
    HEALTH("Zorg en overige dienstverlening");

    private final String AS_STRING;

    Industry(String industrie) {
        AS_STRING = industrie;
    }

/*    public String toString() {
        return this.AS_STRING;
    }*/


}
