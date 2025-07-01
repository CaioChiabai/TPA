package lib;

import java.util.*;

public class Kruskal {
    public static <T> void executar(Grafo<T> grafo) {
        if (grafo.isDirecionado()) {
            System.out.println("Erro: Kruskal só funciona em grafos não-direcionados.");
            return;
        }

        List<Aresta<T>> todasArestas = new ArrayList<>();
        Set<String> vistas = new HashSet<>();

        for (Vertice<T> v : grafo.getVertices()) {
            for (Aresta<T> a : v.getArestas()) {
                String chave = a.getOrigem().getDado() + "-" + a.getDestino().getDado();
                String reversa = a.getDestino().getDado() + "-" + a.getOrigem().getDado();
                if (!vistas.contains(chave) && !vistas.contains(reversa)) {
                    todasArestas.add(a);
                    vistas.add(chave);
                }
            }
        }

        todasArestas.sort(Comparator.comparing(Aresta::getPeso));
        Map<T, T> pai = new HashMap<>();

        for (Vertice<T> v : grafo.getVertices()) {
            pai.put(v.getDado(), v.getDado());
        }

        List<Aresta<T>> resultado = new ArrayList<>();

        for (Aresta<T> a : todasArestas) {
            T raiz1 = encontrarRaiz(pai, a.getOrigem().getDado());
            T raiz2 = encontrarRaiz(pai, a.getDestino().getDado());

            if (!raiz1.equals(raiz2)) {
                resultado.add(a);
                pai.put(raiz1, raiz2);
            }
        }

        System.out.println("Arvore Geradora Mínima (Kruskal):");
        for (Aresta<T> a : resultado) {
            System.out.println(a);
        }
    }

    private static <T> T encontrarRaiz(Map<T, T> pai, T vertice) {
        while (!vertice.equals(pai.get(vertice))) {
            vertice = pai.get(vertice);
        }
        return vertice;
    }
}
