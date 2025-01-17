package com.epicode.catalogo;

public abstract class ElementoCatalogo {
    private final String codiceISBN; // Campo reso immutabile con 'final'
    private final String titolo; // Campo reso immutabile con 'final'
    private final int annoPubblicazione; // Campo reso immutabile con 'final'
    private final int numeroPagine; // Campo reso immutabile con 'final'

    // Costruttore per inizializzare tutti i campi
    public ElementoCatalogo(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine) {
        this.codiceISBN = codiceISBN;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    // Metodi getter per accedere ai campi
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

    // Metodo toString per rappresentare i dati dell'elemento
    @Override
    public String toString() {
        return "ISBN: " + codiceISBN + ", Titolo: " + titolo +
                ", Anno: " + annoPubblicazione + ", Pagine: " + numeroPagine;
    }
}
