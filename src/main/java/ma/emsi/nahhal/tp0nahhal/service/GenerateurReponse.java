package ma.emsi.nahhal.tp0nahhal.service;

import jakarta.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.Locale;

@Dependent
public class GenerateurReponse implements Serializable {

    private boolean roleSystemeAffiche = false;

    public String genererReponse(String question, String roleSysteme) {
        // -1 permet de conserver les champs vides en fin de ligne.
        String[] champs = question.split(";", -1);
        int champsVides = 0;

        for (String champ : champs) {
            if (champ.isBlank()) {
                champsVides++;
            }
        }

        int champsRemplis = champs.length - champsVides;
        double tauxRemplissage = 100.0 * champsRemplis / champs.length;

        String resultat = "";

        if (!roleSystemeAffiche && roleSysteme != null) {
            resultat += roleSysteme.toUpperCase(Locale.FRENCH) + "\n\n";
            roleSystemeAffiche = true;
        }

        resultat += "CONTROLE QUALITE DES DONNEES\n";
        resultat += "Separateur utilise : ;\n";
        resultat += "Nombre de champs : " + champs.length + "\n";
        resultat += "Champs remplis : " + champsRemplis + "\n";
        resultat += "Champs vides : " + champsVides + "\n";
        resultat += String.format(
                Locale.FRENCH,
                "Taux de remplissage : %.1f %%\n",
                tauxRemplissage
        );

        resultat += champsVides == 0
                ? "Bilan : tous les champs sont renseignes."
                : "Bilan : des donnees sont manquantes.";

        return resultat;
    }
}