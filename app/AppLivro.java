package app;

import lib.ArvoreBinaria;
import lib.IArvoreBinaria;

import java.util.Scanner;

public class AppLivro {

    public static void main(String[] args) {
        IArvoreBinaria<Livro> arvorePorNome = new ArvoreBinaria<>(new ComparadorLivroPorNome());
        IArvoreBinaria<Livro> arvorePorData = new ArvoreBinaria<>(new ComparadorLivroPorData());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha a opção:");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Buscar livro por nome");
            System.out.println("3. Buscar livro por data");
            System.out.println("4. Listar livros em ordem por nome");
            System.out.println("5. Listar livros em ordem por data");
            System.out.println("6. Remover livro");
            System.out.println("7. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 1:
                    System.out.println("Digite o nome do livro:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o autor do livro:");
                    String autor = scanner.nextLine();
                    System.out.println("Digite a data de leitura (dd/MM/yyyy):");
                    String data = scanner.nextLine();
                    System.out.println("Digite a nota do livro (0-10):");
                    int nota = scanner.nextInt();
                    scanner.nextLine(); // consumir quebra de linha
                    System.out.println("Escreva sua review:");
                    String review = scanner.nextLine();

                    Livro novoLivro = new Livro(nome, autor, review, data, nota);
                    arvorePorNome.adicionar(novoLivro);
                    arvorePorData.adicionar(novoLivro);
                    break;

                case 2:
                    System.out.println("Digite o nome do livro a buscar:");
                    String nomeBusca = scanner.nextLine();
                    Livro livroBuscaNome = new Livro(nomeBusca, "", "", "", 0);
                    Livro encontradoNome = arvorePorNome.pesquisar(livroBuscaNome);
                    System.out.println(encontradoNome != null ? encontradoNome : "Livro nao encontrado.");
                    break;

                case 3:
                    System.out.println("Digite a data de leitura do livro a buscar:");
                    String dataBusca = scanner.nextLine();
                    Livro livroBuscaData = new Livro("", "", "", dataBusca, 0);
                    Livro encontradoData = arvorePorData.pesquisar(livroBuscaData, new ComparadorLivroPorData());
                    System.out.println(encontradoData != null ? encontradoData : "Livro nao encontrado.");
                    break;

                case 4:
                    System.out.println("Livros em ordem por nome:");
                    System.out.println(arvorePorNome.caminharEmOrdem());
                    break;

                case 5:
                    System.out.println("Livros em ordem por data:");
                    System.out.println(arvorePorData.caminharEmOrdem());
                    break;

                case 6:
                    System.out.println("Digite o nome do livro a remover:");
                    String nomeRemover = scanner.nextLine();
                    Livro livroRemover = new Livro(nomeRemover, "", "", "", 0);
                    arvorePorNome.remover(livroRemover);
                    arvorePorData.remover(livroRemover);
                    System.out.println("Remocao concluida.");
                    break;

                case 7:
                    System.out.println("Encerrando...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }
}
