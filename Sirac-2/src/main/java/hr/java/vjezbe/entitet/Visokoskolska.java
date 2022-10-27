package hr.java.vjezbe.entitet;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public interface Visokoskolska {
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, Integer ocjenaPismenogDijela, Integer ocjenaObraneZavrsnogRada);

    private Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti){
        return null;
    }

    default BigDecimal odrediProsjekOcjenaNaIspitima(Ispit[] ispiti) {
        BigDecimal prosjekOcjena = BigDecimal.valueOf(0);
        BigDecimal zbrojOcjena = BigDecimal.valueOf(0);
        BigDecimal brojOcjena = BigDecimal.valueOf(ispiti.length);
        for (Ispit ispit: ispiti) {
            zbrojOcjena = BigDecimal.valueOf(ispit.ocjena).add(zbrojOcjena);
        }
        prosjekOcjena = zbrojOcjena.divide(brojOcjena);
        return prosjekOcjena;
    }

    default Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student student){
        Ispit[] ispitiOdStudenta = new Ispit[ispiti.length];
        Integer i = 0;
        for (Ispit ispit: ispiti) {
            if(ispit.getStudent().equals(student)){
                ispitiOdStudenta[i] = ispit;
                i++;
            }
        }
        Ispit[] cleanedIspiti = Arrays.stream(ispitiOdStudenta).filter(Objects::nonNull).toArray(Ispit[]::new);
        return cleanedIspiti;
    }
}
