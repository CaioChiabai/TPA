package app;

import java.util.Comparator;

public class ComparadorLivroPorData implements Comparator<Livro> {
    @Override
    public int compare(Livro l1, Livro l2) {
        return l1.getDataLeitura().compareTo(l2.getDataLeitura());
    }
}
