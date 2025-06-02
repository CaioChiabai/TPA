package app;

import lib.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AppGrafo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o caminho do arquivo do grafo: ");
        String caminho = scanner.nextLine();

        Grafo<String> grafo = null;

        try {
            File arquivo = new File(caminho);
            Scanner leitor = new Scanner(arquivo);

            String tipo = leitor.nextLine().trim().toLowerCase();
            boolean direcionado = tipo.equals("direcionado");

            grafo = new Grafo<>(direcionado);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();
                if (linha.isEmpty()) continue;

                String[] partes = linha.split("\\s+");
                if (partes.length == 1) {
                    grafo.adicionarVertice(partes[0].toUpperCase());
                } else if (partes.length == 3) {
                    String origem = partes[0].toUpperCase();
                    String destino = partes[1].toUpperCase();
                    float peso = Float.parseFloat(partes[2]);
                    grafo.adicionarAresta(origem, destino, peso);
                } else {
                    System.out.println("Linha inválida ignorada: " + linha);
                }
            }

            leitor.close();
            System.out.println("Grafo carregado com sucesso!");

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + caminho);
            return;
        } catch (Exception e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
            return;
        }

        boolean direcionado = grafo.isDirecionado();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Imprimir grafo");
            System.out.println("2 - Caminhamento em largura (BFS)");
            System.out.println("3 - " + (direcionado ? "Dijkstra" : "Kruskal"));
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // consumir a quebra de linha

            switch (opcao) {
                case 1:
                    grafo.imprimirGrafo();
                    break;

                case 2:
                    System.out.print("Vértice inicial para BFS: ");
                    String inicio = scanner.nextLine().trim().toUpperCase();
                    grafo.bfs(inicio);
                    break;

                case 3:
                    if (direcionado) {
                        System.out.print("Vértice inicial para Dijkstra: ");
                        String origem = scanner.nextLine().trim().toUpperCase();
                        Dijkstra.executar(grafo, origem);
                    } else {
                        Kruskal.executar(grafo);
                    }
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
