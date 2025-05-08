package app;

import java.text.Normalizer;
import java.util.Comparator;

public class ComparadorLivroPorNome implements Comparator<Livro> {
    @Override
    public int compare(Livro l1, Livro l2) {
        String nome1 = normalizar(l1.getNome());
        String nome2 = normalizar(l2.getNome());
        return nome1.compareToIgnoreCase(nome2);
    }

    private String normalizar(String texto) {
        if (texto == null) return "";
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").trim();
    }
}
