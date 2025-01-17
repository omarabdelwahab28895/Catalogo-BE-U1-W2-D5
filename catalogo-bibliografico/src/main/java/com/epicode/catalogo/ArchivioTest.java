package com.epicode.catalogo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ArchivioTest {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);

        // Popolazione iniziale del catalogo
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

            try {
                int scelta = scanner.nextInt();
                scanner.nextLine(); // Consuma il newline

                switch (scelta) {
                    case 1:
                        aggiungiLibro(archivio, scanner);
                        break;
                    case 2:
                        aggiungiRivista(archivio, scanner);
                        break;
                    case 3:
                        ricercaPerISBN(archivio, scanner);
                        break;
                    case 4:
                        ricercaPerAnno(archivio, scanner);
                        break;
                    case 5:
                        ricercaPerAutore(archivio, scanner);
                        break;
                    case 6:
                        rimuoviElemento(archivio, scanner);
                        break;
                    case 7:
                        archivio.stampaStatistiche();
                        break;
                    case 8:
                        aggiornaElemento(archivio, scanner);
                        break;
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
            } catch (InputMismatchException e) {
                System.out.println("Errore: Inserisci un numero valido.");
                scanner.nextLine(); // Pulisce il buffer
            }
        }
    }

    private static void aggiungiLibro(Archivio archivio, Scanner scanner) {
        try {
            System.out.println("Inserisci ISBN, titolo, anno, numero pagine, autore, genere:");
            String isbn = scanner.nextLine();
            String titolo = scanner.nextLine();
            System.out.print("Anno: ");
            int anno = scanner.nextInt();
            System.out.print("Numero pagine: ");
            int pagine = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline
            System.out.print("Autore: ");
            String autore = scanner.nextLine();
            System.out.print("Genere: ");
            String genere = scanner.nextLine();

            archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
            System.out.println("Libro aggiunto!");
        } catch (InputMismatchException e) {
            System.out.println("Errore: Inserisci valori numerici validi per anno e numero di pagine.");
            scanner.nextLine(); // Pulisce il buffer
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    private static void aggiungiRivista(Archivio archivio, Scanner scanner) {
        try {
            System.out.println("Inserisci ISBN, titolo, anno, numero pagine, periodicità (SETTIMANALE, MENSILE, SEMESTRALE):");
            String isbn = scanner.nextLine();
            String titolo = scanner.nextLine();
            System.out.print("Anno: ");
            int anno = scanner.nextInt();
            System.out.print("Numero pagine: ");
            int pagine = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline
            System.out.print("Periodicità: ");
            String periodicita = scanner.nextLine().toUpperCase();

            Rivista.Periodicita per = Rivista.Periodicita.valueOf(periodicita);
            archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, per));
            System.out.println("Rivista aggiunta!");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: La periodicità deve essere SETTIMANALE, MENSILE o SEMESTRALE.");
        } catch (InputMismatchException e) {
            System.out.println("Errore: Inserisci valori numerici validi per anno e numero di pagine.");
            scanner.nextLine(); // Pulisce il buffer
        }
    }

    private static void ricercaPerISBN(Archivio archivio, Scanner scanner) {
        System.out.println("Inserisci ISBN:");
        String isbn = scanner.nextLine();
        try {
            System.out.println(archivio.ricercaPerISBN(isbn));
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    private static void ricercaPerAnno(Archivio archivio, Scanner scanner) {
        try {
            System.out.println("Inserisci anno di pubblicazione:");
            int anno = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline
            archivio.ricercaPerAnno(anno).forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("Errore: Inserisci un numero valido per l'anno.");
            scanner.nextLine(); // Pulisce il buffer
        }
    }

    private static void ricercaPerAutore(Archivio archivio, Scanner scanner) {
        System.out.println("Inserisci autore:");
        String autore = scanner.nextLine();
        archivio.ricercaPerAutore(autore).forEach(System.out::println);
    }

    private static void rimuoviElemento(Archivio archivio, Scanner scanner) {
        System.out.println("Inserisci ISBN da rimuovere:");
        String isbn = scanner.nextLine();
        try {
            archivio.rimuoviElemento(isbn);
            System.out.println("Elemento rimosso con successo.");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    private static void aggiornaElemento(Archivio archivio, Scanner scanner) {
        System.out.println("Inserisci ISBN dell'elemento da aggiornare:");
        String isbnVecchio = scanner.nextLine();
        System.out.println("Inserisci i nuovi dati (ISBN, titolo, anno, numero pagine, autore, genere):");
        try {
            String nuovoISBN = scanner.nextLine();
            String nuovoTitolo = scanner.nextLine();
            int nuovoAnno = scanner.nextInt();
            int nuovePagine = scanner.nextInt();
            scanner.nextLine(); // Consuma newline
            String nuovoAutore = scanner.nextLine();
            String nuovoGenere = scanner.nextLine();

            archivio.aggiornaElemento(isbnVecchio, new Libro(nuovoISBN, nuovoTitolo, nuovoAnno, nuovePagine, nuovoAutore, nuovoGenere));
            System.out.println("Elemento aggiornato!");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    private static void eseguiTest(Archivio archivio) {
        System.out.println("--- Esecuzione Test Automatici ---");
        try {
            System.out.println(archivio.ricercaPerISBN("12345"));
            archivio.rimuoviElemento("12345");
            System.out.println("Elemento rimosso!");
            archivio.stampaStatistiche();
        } catch (Exception e) {
            System.out.println("Errore durante i test: " + e.getMessage());
        }
    }

    private static void popolazioneIniziale(Archivio archivio) {
        try {
            archivio.aggiungiElemento(new Libro("12345", "Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", "Fantasy"));
            archivio.aggiungiElemento(new Libro("67890", "1984", 1949, 328, "George Orwell", "Distopico"));
            archivio.aggiungiElemento(new Libro("11223", "La Divina Commedia", 1320, 500, "Dante Alighieri", "Classico"));
            archivio.aggiungiElemento(new Rivista("44556", "National Geographic", 2023, 100, Rivista.Periodicita.MENSILE));
            archivio.aggiungiElemento(new Rivista("77889", "Science", 2022, 50, Rivista.Periodicita.SETTIMANALE));
            System.out.println("Popolazione iniziale completata con successo.");
        } catch (Exception e) {
            System.out.println("Errore durante la popolazione iniziale: " + e.getMessage());
        }
    }
}
