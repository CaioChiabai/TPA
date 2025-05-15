package lib;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    private NoArvore<T> raiz;
    private Comparator<T> comparador;

    public ArvoreBinaria(Comparator<T> comparador) {
        this.raiz = null;
        this.comparador = comparador;
    }

    private int comparar(T a, T b) {
        return  comparador.compare(a, b);
    }

    @Override
    public void adicionar(T novoValor) {
        if (raiz == null) {
            raiz = new NoArvore<>(novoValor);
            return;
        }

        NoArvore<T> atual = raiz;
        while (true) {
            int comparacao = comparar(novoValor, atual.getValor());

            if (comparacao < 0) {
                if (atual.getFilhoEsquerda() == null) {
                    atual.setFilhoEsquerda(new NoArvore<>(novoValor));
                    return;
                }
                atual = atual.getFilhoEsquerda();
            } else if (comparacao > 0) {
                if (atual.getFilhoDireita() == null) {
                    atual.setFilhoDireita(new NoArvore<>(novoValor));
                    return;
                }
                atual = atual.getFilhoDireita();
            } else {
                return; // valor já existe, não adiciona duplicado
            }
        }
    }

    @Override
    public T pesquisar(T valor) {
        NoArvore<T> atual = raiz;

        while (atual != null) {
            int comparacao = comparar(valor, atual.getValor());

            if (comparacao == 0) {
                return atual.getValor();
            } else if (comparacao < 0) {
                atual = atual.getFilhoEsquerda();
            } else {
                atual = atual.getFilhoDireita();
            }
        }

        return null;
    }

    @Override
    public T pesquisar(T valor, Comparator comparadorAlternativo) {
        if (raiz == null) return null;

        Queue<NoArvore<T>> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            NoArvore<T> atual = fila.poll();
            if (comparadorAlternativo.compare(valor, atual.getValor()) == 0) {
                return atual.getValor();
            }

            if (atual.getFilhoEsquerda() != null) fila.add(atual.getFilhoEsquerda());
            if (atual.getFilhoDireita() != null) fila.add(atual.getFilhoDireita());
        }

        return null;
    }

    @Override
    public T remover(T valor) {
        NoArvore<T> atual = raiz;
        NoArvore<T> pai = null;

        while (atual != null && comparar(valor, atual.getValor()) != 0) {
            pai = atual;
            if (comparar(valor, atual.getValor()) < 0) {
                atual = atual.getFilhoEsquerda();
            } else {
                atual = atual.getFilhoDireita();
            }
        }

        if (atual == null) return null;

        T valorRemovido = atual.getValor();

        if (atual.getFilhoEsquerda() == null && atual.getFilhoDireita() == null) {
            if (atual == raiz) raiz = null;
            else if (pai.getFilhoEsquerda() == atual) pai.setFilhoEsquerda(null);
            else pai.setFilhoDireita(null);
        } else if (atual.getFilhoEsquerda() == null || atual.getFilhoDireita() == null) {
            NoArvore<T> filhoUnico = (atual.getFilhoEsquerda() != null) ? atual.getFilhoEsquerda() : atual.getFilhoDireita();
            if (atual == raiz) raiz = filhoUnico;
            else if (pai.getFilhoEsquerda() == atual) pai.setFilhoEsquerda(filhoUnico);
            else pai.setFilhoDireita(filhoUnico);
        } else {
            NoArvore<T> substituto = atual.getFilhoDireita();
            NoArvore<T> paiSubstituto = atual;
            while (substituto.getFilhoEsquerda() != null) {
                paiSubstituto = substituto;
                substituto = substituto.getFilhoEsquerda();
            }

            atual.setValor(substituto.getValor());

            if (paiSubstituto.getFilhoEsquerda() == substituto) {
                paiSubstituto.setFilhoEsquerda(substituto.getFilhoDireita());
            } else {
                paiSubstituto.setFilhoDireita(substituto.getFilhoDireita());
            }
        }

        return valorRemovido;
    }

   @Override
public int altura() {
    if (raiz == null) return -1;
    
    Queue<NoArvore<T>> fila = new LinkedList<>();
    fila.add(raiz);
    int altura = -1;
    
    while (!fila.isEmpty()) {
        int nodesNesteNivel = fila.size();
        altura++;
        
        for (int i = 0; i < nodesNesteNivel; i++) {
            NoArvore<T> atual = fila.poll();
            
            if (atual.getFilhoEsquerda() != null) {
                fila.add(atual.getFilhoEsquerda());
            }
            if (atual.getFilhoDireita() != null) {
                fila.add(atual.getFilhoDireita());
            }
        }
    }
    return altura;
}

    @Override
    public int quantidadeNos() {
        return contarNos(raiz);
    }

    private int contarNos(NoArvore<T> no) {
        if (no == null) return 0;
        return 1 + contarNos(no.getFilhoEsquerda()) + contarNos(no.getFilhoDireita());
    }

    @Override
    public String caminharEmNivel() {
        if (raiz == null) return "[]";

        StringBuilder sb = new StringBuilder("[");
        Queue<NoArvore<T>> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            NoArvore<T> atual = fila.poll();
            sb.append(atual.getValor()).append(" \n");

            if (atual.getFilhoEsquerda() != null) fila.add(atual.getFilhoEsquerda());
            if (atual.getFilhoDireita() != null) fila.add(atual.getFilhoDireita());
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public String caminharEmOrdem() {
        StringBuilder sb = new StringBuilder("[\n");
        caminharEmOrdem(raiz, sb);
        sb.append("]");
        return sb.toString();
    }

    private void caminharEmOrdem(NoArvore<T> no, StringBuilder sb) {
        if (no != null) {
            caminharEmOrdem(no.getFilhoEsquerda(), sb);
            sb.append(no.getValor()).append("\n\n");
            caminharEmOrdem(no.getFilhoDireita(), sb);
        }
    }
}
