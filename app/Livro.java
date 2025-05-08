package app;

public class Livro implements Comparable<Livro> {
    private int id;
    private String nome;
    private String autor;
    private String review;
    private String dataLeitura;
    private int nota;

    public Livro(int id, String nome, String autor, String review, String dataLeitura, int nota) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.review = review;
        this.dataLeitura = dataLeitura;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public String getReview() {
        return review;
    }

    public String getDataLeitura() {
        return dataLeitura;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Livro ID: " + id + " - " + nome + " (" + autor + ") - Nota: " + nota + " - Data: " + dataLeitura +
               "\nReview: " + review;
    }

    @Override
    public int compareTo(Livro outro) {
        return Integer.compare(this.id, outro.id); // Ordenação por ID
    }
}
