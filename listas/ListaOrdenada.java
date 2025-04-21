package listas;
import java.util.Comparator;

public class ListaOrdenada<T> {
    private Node<T> head;
    private Comparator<T> comparator;

    public ListaOrdenada(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private static class Node<T> {
        T valor;
        Node<T> proximo;
        Node(T valor) { this.valor = valor; }
    }

    public void adicionar(T novoValor) {
        Node<T> novo = new Node<>(novoValor);
        if (head == null || comparator.compare(novoValor, head.valor) < 0) {
            novo.proximo = head;
            head = novo;
            return;
        }
        Node<T> atual = head;
        while (atual.proximo != null && comparator.compare(novoValor, atual.proximo.valor) >= 0) {
            atual = atual.proximo;
        }
        novo.proximo = atual.proximo;
        atual.proximo = novo;
    }

    public T pesquisar(T valor) {
        Node<T> atual = head;
        while (atual != null && comparator.compare(valor, atual.valor) >= 0) {
            if (atual.valor.equals(valor)) return atual.valor;
            atual = atual.proximo;
        }
        return null;
    }
}
