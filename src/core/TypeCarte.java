package core;

/**
 *
 */
public enum TypeCarte {
    LUNE_1("RayonLune1.png"),
    LUNE_2("RayonLune2.png"),
    LUNE_3("RayonLune3.png"),
    SIRENE_1("ChantSirene1.png"),
    SIRENE_2("ChantSirene2.png"),
    SIRENE_3("ChantSirene3.png"),
    DRYADE_1("LarmeDryade1.png"),
    DRYADE_2("LarmeDryade2.png"),
    DRYADE_3("LarmeDryade3.png"),
    FONTAINE_1("FontainePure1.png"),
    FONTAINE_2("FontainePure2.png"),
    FONTAINE_3("FontainePure3.png"),
    OR_1("PoudreOr1.png"),
    OR_2("PoudreOr2.png"),
    OR_3("PoudreOr3.png"),
    ARC_EN_CIEL_1("ArcEnCiel1.png"),
    ARC_EN_CIEL_2("ArcEnCiel2.png"),
    ARC_EN_CIEL_3("ArcEnCiel3.png"),
    DOLMEN_1("EspritDolmen1.png"),
    DOLMEN_2("EspritDolmen2.png"),
    DOLMEN_3("EspritDolmen3.png"),
    FEE_1("RireFee1.png"),
    FEE_2("RireFee2.png"),
    FEE_3("RireFee3.png"),
    TAUPE_1("Taupe1.png"),
    TAUPE_2("Taupe2.png"),
    TAUPE_3("Taupe3.png"),
    CHIEN_1("Chien1.png"),
    CHIEN_2("Chien2.png"),
    CHIEN_3("Chien3.png"),
    DOS_INGREDIENT("DosCarteIngredient.png"),
    DOS_ALLIE("DosCarteAllie.png");

    private static String location="images/";
    private String imageUrl;
    TypeCarte(String url){
        imageUrl = url;
    }
    public String getImageUrl(){
        return location + imageUrl;
    }
    
    
}
