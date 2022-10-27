package hr.java.vjezbe.entitet;

public class Profesor extends Osoba {

    private String sifra;
    private String ime;
    private String prezime;
    private String titula;

    private Profesor(Builder builder){
        this.ime = builder.ime;
        this.prezime = builder.prezime;
        this.sifra = builder.sifra;
        this.titula = builder.titula;
    }

    public String getSifra() {
        return sifra;
    }


    public String getIme() {
        return ime;
    }


    public String getPrezime() {
        return prezime;
    }

    public String getTitula() {
        return titula;
    }

    public static class Builder{
        private String sifra;
        private String ime;
        private String prezime;
        private String titula;

        public Builder(String sifra){
            this.sifra = sifra;
        }
        public Builder saImenom(String ime){
            this.ime = ime;
            return this;
        }
        public Builder saPrezimenom(String prezime){
            this.prezime = prezime;
            return this;
        }
        public Builder saTitulom(String titula){
            this.titula = titula;
            return this;
        }

        public Profesor build(){
            Profesor profesor = new Profesor(this);
            return  profesor;
        }

    }



}
