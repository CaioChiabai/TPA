package lib;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comparador) {
        super(comparador);
    }

    @Override
    public void adicionar(T novoValor) {
        raiz = adicionar(raiz, novoValor);
    }

    private NoArvore<T> adicionar(NoArvore<T> no, T valor) {
        if (no == null) {
            return new NoArvore<>(valor);
        }

        int comparacao = comparar(valor, no.getValor());

        if (comparacao < 0) {
            no.setFilhoEsquerda(adicionar(no.getFilhoEsquerda(), valor));
        } else if (comparacao > 0) {
            no.setFilhoDireita(adicionar(no.getFilhoDireita(), valor));
        } else {
            return no;
        }

        no.setAltura(1 + Math.max(altura(no.getFilhoEsquerda()), altura(no.getFilhoDireita())));

        int balanceamento = getBalanceamento(no);

        if (balanceamento > 1 && comparar(valor, no.getFilhoEsquerda().getValor()) < 0) {
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && comparar(valor, no.getFilhoDireita().getValor()) > 0) {
            return rotacaoEsquerda(no);
        }

        if (balanceamento > 1 && comparar(valor, no.getFilhoEsquerda().getValor()) > 0) {
            no.setFilhoEsquerda(rotacaoEsquerda(no.getFilhoEsquerda()));
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && comparar(valor, no.getFilhoDireita().getValor()) < 0) {
            no.setFilhoDireita(rotacaoDireita(no.getFilhoDireita()));
            return rotacaoEsquerda(no);
        }

        return no;
    }

    @Override
    public T remover(T valor) {
        raiz = remover(raiz, valor);
        return valor;
    }

    private NoArvore<T> remover(NoArvore<T> no, T valor) {
        if (no == null) {
            return null;
        }

        int comparacao = comparar(valor, no.getValor());

        if (comparacao < 0) {
            no.setFilhoEsquerda(remover(no.getFilhoEsquerda(), valor));
        } else if (comparacao > 0) {
            no.setFilhoDireita(remover(no.getFilhoDireita(), valor));
        } else {
            if (no.getFilhoEsquerda() == null || no.getFilhoDireita() == null) {
                NoArvore<T> temp = no.getFilhoEsquerda() != null ? no.getFilhoEsquerda() : no.getFilhoDireita();

                if (temp == null) {
                    temp = no;
                    no = null;
                } else {
                    no = temp;
                }
            } else {
                NoArvore<T> temp = menorNo(no.getFilhoDireita());
                no.setValor(temp.getValor());
                no.setFilhoDireita(remover(no.getFilhoDireita(), temp.getValor()));
            }
        }

        if (no == null) {
            return null;
        }

        no.setAltura(1 + Math.max(altura(no.getFilhoEsquerda()), altura(no.getFilhoDireita())));

        int balanceamento = getBalanceamento(no);

        if (balanceamento > 1 && getBalanceamento(no.getFilhoEsquerda()) >= 0) {
            return rotacaoDireita(no);
        }

        if (balanceamento > 1 && getBalanceamento(no.getFilhoEsquerda()) < 0) {
            no.setFilhoEsquerda(rotacaoEsquerda(no.getFilhoEsquerda()));
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && getBalanceamento(no.getFilhoDireita()) <= 0) {
            return rotacaoEsquerda(no);
        }

        if (balanceamento < -1 && getBalanceamento(no.getFilhoDireita()) > 0) {
            no.setFilhoDireita(rotacaoDireita(no.getFilhoDireita()));
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private int altura(NoArvore<T> no) {
        return no == null ? 0 : no.getAltura();
    }

    private int getBalanceamento(NoArvore<T> no) {
        return no == null ? 0 : altura(no.getFilhoEsquerda()) - altura(no.getFilhoDireita());
    }

    private NoArvore<T> rotacaoDireita(NoArvore<T> y) {
        NoArvore<T> x = y.getFilhoEsquerda();
        NoArvore<T> T2 = x.getFilhoDireita();

        x.setFilhoDireita(y);
        y.setFilhoEsquerda(T2);

        y.setAltura(Math.max(altura(y.getFilhoEsquerda()), altura(y.getFilhoDireita())) + 1);
        x.setAltura(Math.max(altura(x.getFilhoEsquerda()), altura(x.getFilhoDireita())) + 1);

        return x;
    }

    private NoArvore<T> rotacaoEsquerda(NoArvore<T> x) {
        NoArvore<T> y = x.getFilhoDireita();
        NoArvore<T> T2 = y.getFilhoEsquerda();

        y.setFilhoEsquerda(x);
        x.setFilhoDireita(T2);

        x.setAltura(Math.max(altura(x.getFilhoEsquerda()), altura(x.getFilhoDireita())) + 1);
        y.setAltura(Math.max(altura(y.getFilhoEsquerda()), altura(y.getFilhoDireita())) + 1);

        return y;
    }

    private NoArvore<T> menorNo(NoArvore<T> no) {
        NoArvore<T> atual = no;
        while (atual.getFilhoEsquerda() != null) {
            atual = atual.getFilhoEsquerda();
        }
        return atual;
    }
}