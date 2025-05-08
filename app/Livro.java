package app;

public record Livro(String nome, String autor, String review, String dataLeitura, int nota) implements Comparable<Livro> {

    @Override
    public int compareTo(Livro outro) {
        return this.nome.compareToIgnoreCase(outro.nome); // árvore ordenada por nome
    }
}
