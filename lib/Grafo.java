package lib;

import java.util.*;

public class Grafo<T> {
    private Map<T, Vertice<T>> vertices;
    private boolean direcionado;

    public Grafo(boolean direcionado) {
        this.vertices = new HashMap<>();
        this.direcionado = direcionado;
    }

    public boolean isDirecionado() {
        return direcionado;
    }

    public void adicionarVertice(T valor) {
        if (!vertices.containsKey(valor)) {
            vertices.put(valor, new Vertice<>(valor));
        }
    }

    public boolean adicionarAresta(T origem, T destino, float peso) {
        if (!vertices.containsKey(origem) || !vertices.containsKey(destino)) {
            return false;
        }
        Vertice<T> vOrigem = vertices.get(origem);
        Vertice<T> vDestino = vertices.get(destino);
        vOrigem.adicionarAresta(vDestino, peso);

        if (!direcionado) {
            vDestino.adicionarAresta(vOrigem, peso);
        }
        return true;
    }

    public void bfs(T inicio) {
        if (!vertices.containsKey(inicio)) {
            System.out.println("Vertice inicial nao encontrado.");
            return;
        }

        Set<T> visitados = new HashSet<>();
        Queue<Vertice<T>> fila = new LinkedList<>();

        Vertice<T> inicial = vertices.get(inicio);
        fila.add(inicial);
        visitados.add(inicio);

        System.out.println("Caminhamento em largura a partir de " + inicio + ":");

        while (!fila.isEmpty()) {
            Vertice<T> atual = fila.poll();
            System.out.println(atual.getDado());

            for (Aresta<T> aresta : atual.getArestas()) {
                T vizinho = aresta.getDestino().getDado();
                if (!visitados.contains(vizinho)) {
                    visitados.add(vizinho);
                    fila.add(aresta.getDestino());
                }
            }
        }
    }

    public void imprimirGrafo() {
        for (Vertice<T> v : vertices.values()) {
            for (Aresta<T> a : v.getArestas()) {
                System.out.println(a);
            }
        }
    }

    public Collection<Vertice<T>> getVertices() {
        return vertices.values();
    }

    public Vertice<T> getVertice(T dado) {
        return vertices.get(dado);
    }

    public Map<T, Vertice<T>> getMapaVertices() {
        return vertices;
    }
}
