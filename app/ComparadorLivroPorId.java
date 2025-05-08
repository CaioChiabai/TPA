package app;

import java.util.Comparator;

public class ComparadorLivroPorId implements Comparator<Livro> {
    @Override
    public int compare(Livro l1, Livro l2) {
        return Integer.compare(l1.getId(), l2.getId());
    }
}
