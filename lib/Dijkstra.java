package lib;

import java.util.*;

public class Dijkstra {
    public static <T> void executar(Grafo<T> grafo, T origem) {
        Map<T, Float> distancias = new HashMap<>();
        Map<T, Vertice<T>> vertices = grafo.getMapaVertices();

        for (T chave : vertices.keySet()) {
            distancias.put(chave, Float.MAX_VALUE);
        }
        distancias.put(origem, 0f);

        PriorityQueue<T> fila = new PriorityQueue<>(Comparator.comparing(distancias::get));
        fila.add(origem);

        while (!fila.isEmpty()) {
            T atual = fila.poll();
            Vertice<T> verticeAtual = vertices.get(atual);

            for (Aresta<T> aresta : verticeAtual.getArestas()) {
                T vizinho = aresta.getDestino().getDado();
                float novaDist = distancias.get(atual) + aresta.getPeso();

                if (novaDist < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDist);
                    fila.add(vizinho);
                }
            }
        }

        System.out.println("Distâncias a partir de " + origem + ":");
        for (Map.Entry<T, Float> entrada : distancias.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
    }
}
