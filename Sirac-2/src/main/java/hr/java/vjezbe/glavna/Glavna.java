package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Glavna {

    private static final int BROJ_PROFESORA = 2;
    private static final int BROJ_PREDMETA = 2;
    private static final int BROJ_STUDENATA = 2;
    private static final int BROJ_ISPITA = 2;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.println("Unesite broj obrazovnih ustanova: ");
        Integer brojUstanova = Integer.parseInt(sc.nextLine());
        ObrazovnaUstanova[] obrazovnaUstanova = new ObrazovnaUstanova[brojUstanova];

        for(int k=0;k<brojUstanova;k++) {

            Profesor[] profesori = new Profesor[BROJ_PROFESORA];
            Predmet[] predmeti = new Predmet[BROJ_PREDMETA];
            Student[] studenti = new Student[BROJ_STUDENATA];
            Ispit[] ispiti = new Ispit[BROJ_ISPITA];

            System.out.println("Unesite podatke za" + (k + 1) + ". obrazovnu ustanovu: ");

            for (int i = 0; i < BROJ_PROFESORA; i++) {
                System.out.println("Unesite " + (i + 1) + ". profesora: ");
                System.out.println("Unesite šifru profesora: ");
                String sifra = sc.nextLine();
                System.out.println("Unesite ime profesora: ");
                String ime = sc.nextLine();
                System.out.println("Unesite prezime profesora: ");
                String prezime = sc.nextLine();
                System.out.println("Unesite titulu profesora: ");
                String titula = sc.nextLine();
                profesori[i] = new Profesor.Builder(sifra)
                        .saImenom(ime)
                        .saPrezimenom(prezime)
                        .saTitulom(titula)
                        .build();
            }

            for (int i = 0; i < BROJ_PREDMETA; i++) {
                System.out.println("Unesite " + (i + 1) + ". predmet: ");
                System.out.println("Unesite šifru predmeta: ");
                String sifra = sc.nextLine();
                System.out.println("Unesite naziv predmeta: ");
                String naziv = sc.nextLine();
                System.out.println("Unesite broj ECTS bodova za predmet '" + naziv + "': ");
                Integer brojBodova = Integer.parseInt(sc.nextLine());
                System.out.println("Odaberite profesora:");
                for (int j = 0; j < BROJ_PROFESORA; j++) {
                    System.out.println((j + 1) + "." + profesori[j].getIme() + " " + profesori[j].getPrezime());
                }
                Integer brojProfesora = Integer.parseInt(sc.nextLine());
                Profesor profesor = profesori[(brojProfesora - 1)];
                System.out.println("Unesite broj studenata za predmet '" + naziv + "': ");
                Integer brojStudenata = Integer.parseInt(sc.nextLine());
                predmeti[i] = new Predmet(sifra, naziv, brojBodova, profesor, brojStudenata);
            }


            for (int i = 0; i < BROJ_STUDENATA; i++) {
                System.out.println("Unesite " + (i + 1) + ". studenta: ");
                System.out.println("Unesite ime studenta: ");
                String imeStudenta = sc.nextLine();
                System.out.println("Unesite prezime studenta: ");
                String prezime = sc.nextLine();
                System.out.println("Unesite datum rođenja studenta " + imeStudenta + " " + prezime + " u formatu (dd.MM.yyyy.): ");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                LocalDate datum = LocalDate.parse(sc.nextLine(), dtf);
                System.out.println("Unesite JMBAG studenta: ");
                String jmbag = sc.nextLine();
                studenti[i] = new Student(imeStudenta, prezime, jmbag, datum);
            }


            for (int i = 0; i < BROJ_ISPITA; i++) {
                System.out.println("Unesite " + (i + 1) + ". ispitni rok: ");
                System.out.println("Odaberite predmet: ");
                for (int j = 0; j < BROJ_PREDMETA; j++) {
                    System.out.println((j + 1) + ". " + predmeti[j].getNaziv());
                }
                Integer brojPredmeta = Integer.parseInt(sc.nextLine());
                Predmet predmet = predmeti[(brojPredmeta - 1)];
                System.out.println("Unesite naziv dvorane: ");
                String dvorana = sc.nextLine();
                System.out.println("Unesite zgradu dvorane: ");
                String zgrada = sc.nextLine();
                Dvorana dvorana1 = new Dvorana(dvorana,zgrada);
                System.out.println("Odaberite studenta: ");
                for (int j = 0; j < BROJ_STUDENATA; j++) {
                    System.out.println((j + 1) + ". " + studenti[j].getIme() + " " + studenti[j].getPrezime());
                }
                Integer brojStudenta = Integer.parseInt(sc.nextLine());
                Student student = studenti[(brojStudenta - 1)];
                System.out.println("Unesite ocjenu na ispitu (1-5): ");
                Integer ocjena = Integer.parseInt(sc.nextLine());
                System.out.println("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm): ");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH:mm");
                LocalDateTime datumIVrijeme = LocalDateTime.parse(sc.nextLine(), dtf);

                ispiti[i] = new Ispit(predmet, student, ocjena, datumIVrijeme, dvorana1);

                System.out.println("Odrzava li se ispit online? ");
                System.out.println("1. DA");
                System.out.println("2. NE");
                Integer input = Integer.parseInt(sc.nextLine());
                if(input == 1){
                    System.out.println("Molimo odaberite software u kojemu se odrzava ispit");
                    System.out.println("1. IntelliJ");
                    System.out.println("2. Rider");
                    input = Integer.parseInt(sc.nextLine());
                    String program = "nista";
                    if(input == 1){
                        program = ispiti[i].imePrograma("intellij");
                    } else if (input == 2) {
                        program = ispiti[i].imePrograma("rider");
                    }
                    System.out.println("Programski jezik koristen na ispitu je: " + program);
                }



            }


            for (int i = 0; i < BROJ_ISPITA; i++) {
                if (ispiti[i].getOcjena() == 5) {
                    System.out.println("Student " + ispiti[i].getStudent().getIme() + " " + ispiti[i].getStudent().getPrezime() +
                            "  je ostvario ocjenu 'izvrstan' na predmetu '" + ispiti[i].getPredmet().getNaziv() + "' ");
                }
            }

            System.out.println("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti (1 - Veleučilište Jave, 2 - Fakultet računarstva): ");
            Integer ustanova = Integer.parseInt(sc.nextLine());
            String nazivObrazovneUstanove;
            switch (ustanova) {
                case 1 -> {
                    System.out.println("Unesite naziv obrazovne ustanove: ");
                    nazivObrazovneUstanove = sc.nextLine();
                    obrazovnaUstanova[k] = new VeleucilisteJave(nazivObrazovneUstanove, predmeti, profesori, studenti, ispiti);
                    for (Student student : obrazovnaUstanova[k].getStudenti()) {
                        System.out.println("Unesite ocjenu završnog rada za studenta: " + student.getIme() + " " + student.getPrezime() + ": ");
                        student.setOcjenaZavrsnogRada(Integer.parseInt(sc.nextLine()));
                        System.out.println("Unesite ocjenu obrane završnog rada za studenta: " + student.getIme() + " " + student.getPrezime() + ": ");
                        student.setOcjenaObraneZavrsnogRada(Integer.parseInt(sc.nextLine()));
                        Ispit[] filtriraniIspiti = new Ispit[BROJ_ISPITA];
                        filtriraniIspiti = ((VeleucilisteJave) obrazovnaUstanova[k]).filtrirajIspitePoStudentu(obrazovnaUstanova[k].getIspiti(), student);
                        System.out.println("konacna ocjena studija studenta " + student.getIme() + " " + student.getPrezime() + ": "
                                + ((VeleucilisteJave) obrazovnaUstanova[k])
                                .izracunajKonacnuOcjenuStudijaZaStudenta(filtriraniIspiti, student.getOcjenaZavrsnogRada(), student.getOcjenaObraneZavrsnogRada()));
                    }
                    Student najboljiStudent = ((VeleucilisteJave) obrazovnaUstanova[k]).odrediNajuspjesnijegStudentaNaGodini(2022);
                    System.out.println("Najbolji student 2022. godine je " + najboljiStudent.getIme() + " " + najboljiStudent.getPrezime() + " JMBAG: " + najboljiStudent.getJmbag());
                }
                case 2 -> {
                    System.out.println("Unesite naziv obrazovne ustanove: ");
                    nazivObrazovneUstanove = sc.nextLine();
                    obrazovnaUstanova[k] = new FakultetRacunarstva(nazivObrazovneUstanove, predmeti, profesori, studenti, ispiti);
                    for (Student student : obrazovnaUstanova[k].getStudenti()) {
                        System.out.println("Unesite ocjenu završnog rada za studenta: " + student.getIme() + " " + student.getPrezime() + ": ");
                        student.setOcjenaZavrsnogRada(Integer.parseInt(sc.nextLine()));
                        System.out.println("Unesite ocjenu obrane završnog rada za studenta: " + student.getIme() + " " + student.getPrezime() + ": ");
                        student.setOcjenaObraneZavrsnogRada(Integer.parseInt(sc.nextLine()));
                        System.out.println("konacna ocjena studija studenta " + student.getIme() + " " + student.getPrezime() + ": "
                                + ((FakultetRacunarstva) obrazovnaUstanova[k])
                                .izracunajKonacnuOcjenuStudijaZaStudenta(((FakultetRacunarstva) obrazovnaUstanova[k])
                                        .filtrirajIspitePoStudentu(obrazovnaUstanova[k].getIspiti(), student), student.getOcjenaZavrsnogRada(), student.getOcjenaObraneZavrsnogRada()));
                    }
                    Student najboljiStudentFakultet = ((FakultetRacunarstva) obrazovnaUstanova[k]).odrediNajuspjesnijegStudentaNaGodini(2022);
                    System.out.println("Najbolji student 2022. godine je " + najboljiStudentFakultet.getIme() + " " + najboljiStudentFakultet.getPrezime() + " JMBAG: " + najboljiStudentFakultet.getJmbag());
                    Student studentSaRektorovomNagradom = ((FakultetRacunarstva) obrazovnaUstanova[k]).odrediStudentaZaRektorovuNagradu();
                    System.out.println("Student koji je osvojio rektorovu nagradu je: " + studentSaRektorovomNagradom.getIme() + " " + studentSaRektorovomNagradom.getPrezime() + " JMBAG: " + studentSaRektorovomNagradom.getJmbag());
                }
                default -> System.out.println("Unesen je krivi broj obrazovne ustanove");
            }

        }
    }
}
