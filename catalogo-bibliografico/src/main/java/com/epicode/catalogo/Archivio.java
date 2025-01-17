package com.epicode.catalogo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Archivio {

    private final List<ElementoCatalogo> catalogo;


    public Archivio() {
        this.catalogo = new ArrayList<>();
    }

    // Aggiunta di un elemento al catalogo
    public void aggiungiElemento(ElementoCatalogo elemento) {
        // Controlla se l'elemento con lo stesso ISBN esiste già
        if (catalogo.stream().anyMatch(e -> e.getCodiceISBN().equals(elemento.getCodiceISBN()))) {
            System.out.println("Errore: ISBN duplicato. Non è possibile aggiungere l'elemento con ISBN " + elemento.getCodiceISBN());
            return; // Esci senza aggiungere l'elemento
        }
        // Aggiungi l'elemento al catalogo
        catalogo.add(elemento);
        System.out.println("Elemento aggiunto con successo: " + elemento.getTitolo());
    }


    // Ricerca per ISBN con eccezione custom
    public ElementoCatalogo ricercaPerISBN(String isbn) {
        return catalogo.stream()
                .filter(e -> e.getCodiceISBN().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new ElementoNonTrovatoException("Elemento con ISBN " + isbn + " non trovato."));
    }


    // Rimozione di un elemento dal catalogo dato il codice ISBN
    public void rimuoviElemento(String isbn) {
        boolean removed = catalogo.removeIf(e -> e.getCodiceISBN().equals(isbn));
        if (!removed) {
            throw new ElementoNonTrovatoException("Elemento con ISBN " + isbn + " non trovato. Rimozione fallita.");
        }
    }

    // Ricerca per anno di pubblicazione
    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        return catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    // Ricerca per autore (solo per libri)
    public List<Libro> ricercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    // Aggiornamento di un elemento esistente
    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) {
        rimuoviElemento(isbn);
        aggiungiElemento(nuovoElemento);
    }

    // Stampa delle statistiche del catalogo
    public void stampaStatistiche() {
        long totaleLibri = catalogo.stream().filter(e -> e instanceof Libro).count();
        long totaleRiviste = catalogo.stream().filter(e -> e instanceof Rivista).count();
        Optional<ElementoCatalogo> elementoConPiuPagine = catalogo.stream()
                .max(Comparator.comparingInt(ElementoCatalogo::getNumeroPagine));
        double mediaPagine = catalogo.stream()
                .mapToInt(ElementoCatalogo::getNumeroPagine).average().orElse(0.0);

        System.out.println("Statistiche del catalogo:");
        System.out.println("Totale libri: " + totaleLibri);
        System.out.println("Totale riviste: " + totaleRiviste);
        elementoConPiuPagine.ifPresent(e ->
                System.out.println("Elemento con più pagine: " + e.getTitolo() + " (" + e.getNumeroPagine() + " pagine)")
        );
        System.out.println("Media pagine: " + mediaPagine);
    }

}
