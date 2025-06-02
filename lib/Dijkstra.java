package lib;

import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {
    public static <T> void executar(Grafo<T> grafo, T origem) {
        Map<T, Float> distancias = new HashMap<>();
        Map<T, T> anteriores = new HashMap<>();
        Map<T, Vertice<T>> vertices = grafo.getMapaVertices();

        for (T chave : vertices.keySet()) {
            distancias.put(chave, Float.MAX_VALUE);
            anteriores.put(chave, null);
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
                    anteriores.put(vizinho, atual);
                    fila.add(vizinho);
                }
            }
        }

        System.out.println("Distâncias e caminhos mínimos a partir de " + origem + ":");
        for (T destino : distancias.keySet()) {
            float dist = distancias.get(destino);
            System.out.print(destino + ": " + dist);

            if (!destino.equals(origem) && dist != Float.MAX_VALUE) {
                List<T> caminho = new ArrayList<>();
                T atual = destino;
                while (atual != null) {
                    caminho.add(atual);
                    atual = anteriores.get(atual);
                }
                Collections.reverse(caminho);
                System.out.print(" | " + String.join(" -> ", caminho.stream().map(Object::toString).collect(Collectors.toList())));
            }

            System.out.println();
        }
    }
}
