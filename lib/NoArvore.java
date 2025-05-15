package lib;

public class NoArvore<T> {
    private T valor;
    private NoArvore<T> filhoEsquerda;
    private NoArvore<T> filhoDireita;
    private int altura;

    public NoArvore(T valor) {
        this.valor = valor;
        this.altura = 1; // Novo nó é inicializado com altura 1
    }

    // Getters e Setters
    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public NoArvore<T> getFilhoEsquerda() {
        return filhoEsquerda;
    }

    public void setFilhoEsquerda(NoArvore<T> filhoEsquerda) {
        this.filhoEsquerda = filhoEsquerda;
    }

    public NoArvore<T> getFilhoDireita() {
        return filhoDireita;
    }

    public void setFilhoDireita(NoArvore<T> filhoDireita) {
        this.filhoDireita = filhoDireita;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}