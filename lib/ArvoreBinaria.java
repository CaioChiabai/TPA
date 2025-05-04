package lib;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria<T extends Comparable<T>> implements IArvoreBinaria<T> {

    private NoArvore<T> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    @Override
    public void adicionar(T novoValor) {
        if (raiz == null) {
            raiz = new NoArvore<>(novoValor);
            return;
        }

        NoArvore<T> atual = raiz;
        while (true) {
            int comparacao = novoValor.compareTo(atual.getValor());

            if (comparacao < 0) {
                if (atual.getFilhoEsquerda() == null) {
                    atual.setFilhoEsquerda(new NoArvore<>(novoValor));
                    break;
                }
                atual = atual.getFilhoEsquerda();
            } else if (comparacao > 0) {
                if (atual.getFilhoDireita() == null) {
                    atual.setFilhoDireita(new NoArvore<>(novoValor));
                    break;
                }
                atual = atual.getFilhoDireita();
            } else {
                // valor já existe, não insere duplicado
                break;
            }
        }
    }

    @Override
    public T pesquisar(T valor) {
        NoArvore<T> atual = raiz;

        while (atual != null) {
            int comparacao = valor.compareTo(atual.getValor());
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
    public T pesquisar(T valor, Comparator comparador) {
        if (raiz == null) return null;

        Queue<NoArvore<T>> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            NoArvore<T> atual = fila.poll();
            if (comparador.compare(valor, atual.getValor()) == 0) {
                return atual.getValor();
            }

            if (atual.getFilhoEsquerda() != null) {
                fila.add(atual.getFilhoEsquerda());
            }
            if (atual.getFilhoDireita() != null) {
                fila.add(atual.getFilhoDireita());
            }
        }

        return null;
    }

    @Override
    public int altura() {
        return calcularAltura(raiz);
    }

    private int calcularAltura(NoArvore<T> no) {
        if (no == null) return -1;
        int altEsq = calcularAltura(no.getFilhoEsquerda());
        int altDir = calcularAltura(no.getFilhoDireita());
        return 1 + Math.max(altEsq, altDir);
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
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    @Override
    public String caminharEmOrdem() {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    @Override
    public T remover(T valor) {
        throw new UnsupportedOperationException("Ainda não implementado");
    }
}
