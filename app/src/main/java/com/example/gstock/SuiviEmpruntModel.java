package com.example.gstock;

public class SuiviEmpruntModel {

    int idSuivi;
    String nomC;
    String nomMembre;
    String tel;

    @Override
    public String toString() {
        return "SuiviEmpruntModel{" +
                "idSuivi=" + idSuivi +
                ", nomC='" + nomC + '\'' +
                ", nomMembre='" + nomMembre + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
