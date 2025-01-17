package com.epicode.catalogo;

public abstract class ElementoCatalogo {
    private final String codiceISBN;
    private final String titolo;
    private final int annoPubblicazione;
    private final int numeroPagine;

    // Costruttore per inizializzare tutti i campi
    public ElementoCatalogo(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine) {
        this.codiceISBN = codiceISBN;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }


    public String getCodiceISBN() {
        return codiceISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    @Override
    public String toString() {
        return "ISBN: " + codiceISBN + ", Titolo: " + titolo +
                ", Anno: " + annoPubblicazione + ", Pagine: " + numeroPagine;
    }
}
