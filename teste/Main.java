package teste;

import listas.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ListaNaoOrdenada<Aluno> naoOrdenada = new ListaNaoOrdenada<>();
        ListaOrdenada<Aluno> ordenada = new ListaOrdenada<>(new ComparadorMatricula());

        Scanner in = new Scanner(System.in);
        System.out.print("Arquivo de entrada: ");
        String nomeArquivo = in.nextLine();

        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        int total = Integer.parseInt(br.readLine());

        long inicio = System.nanoTime();
        for (int i = 0; i < total; i++) {
            String[] dados = br.readLine().split(";");
            int matricula = Integer.parseInt(dados[0]);
            String nome = dados[1];
            float nota = Float.parseFloat(dados[2]);
            Aluno aluno = new Aluno(matricula, nome, nota);
            naoOrdenada.adicionar(aluno);
            ordenada.adicionar(aluno);
        }
        long fim = System.nanoTime();
        System.out.println("Tempo de leitura e insercao: " + (fim - inicio) + " ns");

        while (true) {
            System.out.println("\n1. Pesquisar na lista NAO ordenada");
            System.out.println("2. Pesquisar na lista ORDENADA");
            System.out.println("3. Sair");
            System.out.print("Opcao: ");
            int opcao = in.nextInt();

            if (opcao == 3) break;

            System.out.print("Digite a matricula do aluno: ");
            int mat = in.nextInt();
            Aluno chave = new Aluno(mat, "", 0);

            if (opcao == 1) {
                inicio = System.nanoTime();
                Aluno encontrado = naoOrdenada.pesquisar(chave);
                fim = System.nanoTime();
                System.out.println(encontrado != null ? encontrado : "Aluno nao encontrado.");
                System.out.println("Tempo de pesquisa: " + (fim - inicio) + " ns");
            } else if (opcao == 2) {
                inicio = System.nanoTime();
                Aluno encontrado = ordenada.pesquisar(chave);
                fim = System.nanoTime();
                System.out.println(encontrado != null ? encontrado : "Aluno nao encontrado.");
                System.out.println("Tempo de pesquisa: " + (fim - inicio) + " ns");
            }
        }
        in.close();
    }
}
