package listas;

public class ListaNaoOrdenada<T> {
    private Node<T> head;

    private static class Node<T> {
        T valor;
        Node<T> proximo;
        Node(T valor) { this.valor = valor; }
    }

    public void adicionar(T novoValor) {
        Node<T> novo = new Node<>(novoValor);
        novo.proximo = head;
        head = novo;
    }

    public T pesquisar(T valor) {
        Node<T> atual = head;
        while (atual != null) {
            if (atual.valor.equals(valor)) return atual.valor;
            atual = atual.proximo;
        }
        return null;
    }
}
