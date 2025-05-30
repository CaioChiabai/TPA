package lib;

import java.util.ArrayList;
import java.util.List;

public class Vertice<T> {
    private T dado;
    private List<Aresta<T>> arestas;

    public Vertice(T dado) {
        this.dado = dado;
        this.arestas = new ArrayList<>();
    }

    public T getDado() {
        return dado;
    }

    public List<Aresta<T>> getArestas() {
        return arestas;
    }

    public void adicionarAresta(Vertice<T> destino, float peso) {
        arestas.add(new Aresta<>(this, destino, peso));
    }

    @Override
    public String toString() {
        return dado.toString();
    }
}
