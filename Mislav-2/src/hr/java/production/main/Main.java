package hr.java.production.main;


import hr.java.production.model.*;

import java.lang.String;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static final int cat_art = 1;
    private static final int item_art = 1;
    private static final int fact_num = 1;
    private static final int store_num = 1;

    public static Category insertCategory(){

        Scanner input = new Scanner(System.in);

        System.out.println("Unesite ime: ");
        String name = input.nextLine();

        System.out.println("Unesite opis: ");
        String opis = input.nextLine();

        return  new Category(name,opis);

    }
    public static Item insertItem(Category[] categories){

        Category kategorija;
        int temp = 0;
        int uvijet = 0;
        Scanner input = new Scanner(System.in);

        System.out.println("Unesite ime: ");
        String name = input.nextLine();

        System.out.println("Odaberite kategoriju: ");
        while(uvijet == 0) {
            for (int i = 0; i < cat_art; i++) {
                System.out.println((i+1) + " " + categories[i].getName());
            }
            temp = input.nextInt();
            if(temp > 0 && temp <= cat_art){
                uvijet = 1;
            }else{
                System.out.println("Molimo odaberite ispravnu kategoriju: ");
            }
        }


        kategorija = categories[temp-1];

        System.out.println("Upisite width");
        BigDecimal width = input.nextBigDecimal();

        System.out.println("Upisite height");
        BigDecimal height = input.nextBigDecimal();

        System.out.println("Upisite lenght");
        BigDecimal lenght = input.nextBigDecimal();

        System.out.println("Upisite cijenu izrade");
        BigDecimal productionCost = input.nextBigDecimal();

        System.out.println("Upisite prodajnu cijenu");
        BigDecimal sellingPrice = input.nextBigDecimal();

        BigDecimal volume = width.multiply(height.multiply(lenght));

        return new Item(name,kategorija,width,height,lenght,productionCost,sellingPrice,volume);


    }

    public static Factory insertFactory(Item[] items){

        Item[] predmet = new Item[10];
        int temp = 0;
        int uvijet = 0;
        Scanner input = new Scanner(System.in).useDelimiter("\\n");

        System.out.println("Unesite ime: ");
        String name = input.nextLine();

        System.out.println("Unesite Ulicu: ");
        String ulica = input.nextLine();

        System.out.println("Unesite kucni broj: ");
        String kb = input.nextLine();

        System.out.println("Unesite grad: ");
        String grad = input.nextLine();

        System.out.println("Unesite postanski broj: ");
        String pb = input.nextLine();

        Address address = new Address(ulica,kb,grad,pb);

        System.out.println("Odaberite proizvode: ");
        int j = 0;
        while(uvijet == 0 ) {
            for (int i = 0; i < item_art; i++) {
                System.out.println((i+1) + " " + items[i].getName());
            }
            System.out.println((item_art+1) + " Izlaz");
            temp = input.nextInt();
            if(temp-1 < item_art){
                predmet[j] = items[temp-1];
                j++;

            }else if(temp == item_art+1){

                uvijet = 1;
            }else{
                System.out.println("Molimo odaberite idući proizvod: ");
            }
        }



        Factory tempFactory = new Factory(name, address,predmet);
        tempFactory.setLen(j);
        return tempFactory;


    }

    public static Store insertStore(Item[] items){

        Item[] predmet = new Item[item_art];
        int temp = 0;
        int uvijet = 0;
        Scanner input = new Scanner(System.in).useDelimiter("\\n");

        System.out.println("Unesite ime: ");
        String name = input.nextLine();

        System.out.println("Unesite web stranicu: ");
        String webAddress = input.nextLine();


        System.out.println("Odaberite proizvode: ");
        int j = 0;
        while(uvijet == 0) {
            for (int i = 0; i < item_art; i++) {
                System.out.println((i+1) + " " + items[i].getName());
            }
            System.out.println((item_art+1) + " Izlaz");
            temp = input.nextInt();
            if(temp < item_art){
                predmet[j] = items[temp-1];
                j++;

            }if(temp == item_art+1){
                uvijet = 1;
            }else{
                System.out.println("Molimo odaberite idući proizvod: ");
            }
        }
        Store tempstore = new Store(name,webAddress,predmet);
        tempstore.setLen(j);
        return tempstore;


    }

    public static void main(String[] args) {
        Category[] categories = new Category[cat_art];
        Item[] items = new Item[item_art];
        Factory[] factories = new Factory[fact_num];
        Store[] stores = new Store[store_num];
        Item[] item2 = new Item[item_art] ;
        BigDecimal max = BigDecimal.valueOf(0);
        BigDecimal min = BigDecimal.valueOf(0);
        int polozaj= 0;
        int polozajMin= 0;

        for(int i = 0 ; i<cat_art ;i++){
            System.out.println("Unesite "+(i+1)+". kategoriju:");
            categories[i] = insertCategory();
        }
        for(int i = 0 ; i<item_art ;i++){
            System.out.println("Unesite "+(i+1)+". stvar:");
            items[i] = insertItem(categories);
        }
        for(int i = 0 ; i<fact_num ;i++){
            System.out.println("Unesite "+(i+1)+". tvornicu:");
            factories[i] = insertFactory(items);

            int len = factories[i].getLen();
            for(int j = 0 ; j<len ;j++){
                item2[j] = factories[i].getItems()[j];
                if(item2[j].getVolume().compareTo(max) > 0){
                        max = item2[j].getVolume();
                        polozaj = i;
                }
            }


        }
        for(int i = 0 ; i<store_num ;i++){
            System.out.println("Unesite "+(i+1)+". trgovinu:");
            stores[i] = insertStore(items);

            int len = stores[i].getLen();
            for(int j = 0 ; j<len ;j++){
                item2[j] = stores[i].getItems()[j];
                if(i==0){
                    min = item2[j].getSellingPrice();
                }
                if(item2[j].getSellingPrice().compareTo(min) < 0){
                    min = item2[j].getSellingPrice();
                    polozajMin = i;
                }
            }
        }

        System.out.println("Tvornica koja proizvodi proizvod sa najvećim volumenom je: " + factories[polozaj].getName());
        System.out.println("Trgovina koja prodaje proizvod s najnižom cijenom je: " + stores[polozajMin].getName());
    }
}