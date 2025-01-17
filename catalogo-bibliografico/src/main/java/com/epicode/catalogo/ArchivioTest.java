package com.epicode.catalogo;

import java.util.Scanner;

public class ArchivioTest {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);

        // Popolazione iniziale del catalogo per i test automatizzati
        popolazioneIniziale(archivio);

        // Menu interattivo
        while (true) {
            System.out.println("\n--- Menu Catalogo Bibliografico ---");
            System.out.println("1. Aggiungi un libro");
            System.out.println("2. Aggiungi una rivista");
            System.out.println("3. Ricerca per ISBN");
            System.out.println("4. Ricerca per anno di pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Rimuovi un elemento");
            System.out.println("7. Stampa statistiche");
            System.out.println("8. Aggiorna un elemento esistente");
            System.out.println("9. Esegui test automatici");
            System.out.println("10. Esci");
            System.out.print("Scegli un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            switch (scelta) {
                case 1: {
                    System.out.println("Inserisci ISBN, titolo, anno, numero pagine, autore, genere:");
                    String isbnLibro = scanner.nextLine();
                    String titoloLibro = scanner.nextLine();
                    int annoLibro = scanner.nextInt();
                    int pagineLibro = scanner.nextInt();
                    scanner.nextLine(); // Consuma il newline
                    String autoreLibro = scanner.nextLine();
                    String genereLibro = scanner.nextLine();
                    archivio.aggiungiElemento(new Libro(isbnLibro, titoloLibro, annoLibro, pagineLibro, autoreLibro, genereLibro));
                    System.out.println("Libro aggiunto!");
                    break;
                }

                case 2: {
                    System.out.println("Inserisci ISBN, titolo, anno, numero pagine, periodicità (SETTIMANALE, MENSILE, SEMESTRALE):");
                    String isbnRivista = scanner.nextLine();
                    String titoloRivista = scanner.nextLine();
                    int annoRivista = scanner.nextInt();
                    int pagineRivista = scanner.nextInt();
                    scanner.nextLine(); // Consuma il newline
                    String periodicita = scanner.nextLine();
                    Rivista.Periodicita per = Rivista.Periodicita.valueOf(periodicita.toUpperCase());
                    archivio.aggiungiElemento(new Rivista(isbnRivista, titoloRivista, annoRivista, pagineRivista, per));
                    System.out.println("Rivista aggiunta!");
                    break;
                }

                case 3: {
                    System.out.println("Inserisci ISBN:");
                    String isbnRicerca = scanner.nextLine();
                    try {
                        System.out.println(archivio.ricercaPerISBN(isbnRicerca));
                    } catch (ElementoNonTrovatoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 4: {
                    System.out.println("Inserisci anno di pubblicazione:");
                    int annoRicerca = scanner.nextInt();
                    scanner.nextLine(); // Consuma il newline
                    archivio.ricercaPerAnno(annoRicerca).forEach(System.out::println);
                    break;
                }

                case 5: {
                    System.out.println("Inserisci autore:");
                    String autoreRicerca = scanner.nextLine();
                    archivio.ricercaPerAutore(autoreRicerca).forEach(System.out::println);
                    break;
                }

                case 6: {
                    System.out.println("Inserisci ISBN da rimuovere:");
                    String isbnRimozione = scanner.nextLine();
                    try {
                        archivio.rimuoviElemento(isbnRimozione);
                        System.out.println("Elemento rimosso!");
                    } catch (ElementoNonTrovatoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 7:
                    archivio.stampaStatistiche();
                    break;

                case 8: {
                    System.out.println("Inserisci ISBN dell'elemento da aggiornare:");
                    String isbnAggiornamento = scanner.nextLine();
                    System.out.println("Inserisci i nuovi dati (ISBN, titolo, anno, numero pagine, autore, genere):");
                    String nuovoISBN = scanner.nextLine();
                    String nuovoTitolo = scanner.nextLine();
                    int nuovoAnno = scanner.nextInt();
                    int nuovePagine = scanner.nextInt();
                    scanner.nextLine(); // Consuma newline
                    String nuovoAutore = scanner.nextLine();
                    String nuovoGenere = scanner.nextLine();

                    try {
                        Libro nuovoLibro = new Libro(nuovoISBN, nuovoTitolo, nuovoAnno, nuovePagine, nuovoAutore, nuovoGenere);
                        archivio.aggiornaElemento(isbnAggiornamento, nuovoLibro);
                        System.out.println("Elemento aggiornato!");
                    } catch (ElementoNonTrovatoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 9:
                    eseguiTest(archivio);
                    break;

                case 10:
                    System.out.println("Uscita dal programma. Arrivederci!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        }
    }

    // Metodo per eseguire i test automatizzati
    private static void eseguiTest(Archivio archivio) {
        System.out.println("\n--- Esecuzione Test Automatici ---");

        // Ricerca per ISBN
        try {
            System.out.println("Ricerca per ISBN '12345':");
            System.out.println(archivio.ricercaPerISBN("12345"));
        } catch (ElementoNonTrovatoException e) {
            System.out.println(e.getMessage());
        }

        // Ricerca per anno di pubblicazione
        System.out.println("\nRicerca per anno di pubblicazione 2023:");
        archivio.ricercaPerAnno(2023).forEach(System.out::println);

        // Ricerca per autore
        System.out.println("\nRicerca per autore 'Dante Alighieri':");
        archivio.ricercaPerAutore("Dante Alighieri").forEach(System.out::println);

        // Rimozione di un elemento
        System.out.println("\nRimozione dell'elemento con ISBN '67890'.");
        try {
            archivio.rimuoviElemento("67890");
            System.out.println("Elemento rimosso!");
        } catch (ElementoNonTrovatoException e) {
            System.out.println(e.getMessage());
        }

        // Stampa statistiche
        System.out.println("\nStatistiche del catalogo:");
        archivio.stampaStatistiche();
    }

    // Metodo per popolare inizialmente il catalogo con dati di esempio
    private static void popolazioneIniziale(Archivio archivio) {
        // Creazione di alcuni libri
        Libro libro1 = new Libro("12345", "Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", "Fantasy");
        Libro libro2 = new Libro("67890", "1984", 1949, 328, "George Orwell", "Distopico");
        Libro libro3 = new Libro("11223", "La Divina Commedia", 1320, 500, "Dante Alighieri", "Classico");

        // Creazione di alcune riviste
        Rivista rivista1 = new Rivista("44556", "National Geographic", 2023, 100, Rivista.Periodicita.MENSILE);
        Rivista rivista2 = new Rivista("77889", "Science", 2022, 50, Rivista.Periodicita.SETTIMANALE);

        // Aggiunta di elementi al catalogo
        archivio.aggiungiElemento(libro1);
        archivio.aggiungiElemento(libro2);
        archivio.aggiungiElemento(libro3);
        archivio.aggiungiElemento(rivista1);
        archivio.aggiungiElemento(rivista2);
    }
}