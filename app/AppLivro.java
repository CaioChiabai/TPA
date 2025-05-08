package app;

import lib.ArvoreBinaria;
import lib.IArvoreBinaria;

import java.util.Scanner;

public class AppLivro {

    public static void main(String[] args) {
        IArvoreBinaria<Livro> arvorePorNome = new ArvoreBinaria<>(new ComparadorLivroPorNome());
        IArvoreBinaria<Livro> arvorePorData = new ArvoreBinaria<>(new ComparadorLivroPorData());

        Livro livro1 = new Livro("A Guerra dos Tronos", "George R. R. Martin",
                "Excelente começo para uma série épica.", "10/3/2020", 9);
        Livro livro2 = new Livro("O Hobbit", "J. R. R. Tolkien",
                "Uma jornada leve e mágica.", "22/5/2021", 8);
        Livro livro3 = new Livro("Dom Quixote", "Miguel de Cervantes",
                "Um clássico com muito humor e crítica.", "5/11/2022", 7);
        Livro livro4 = new Livro("1984", "George Orwell",
                "Assustadoramente atual.", "14/8/2020", 10);
        Livro livro5 = new Livro("A Revolução dos Bichos", "George Orwell",
                "Fábula crítica e direta.", "30/1/2024", 9);

        // Adicionando nas árvores
        arvorePorNome.adicionar(livro1);
        arvorePorNome.adicionar(livro2);
        arvorePorNome.adicionar(livro3);
        arvorePorNome.adicionar(livro4);
        arvorePorNome.adicionar(livro5);

        arvorePorData.adicionar(livro1);
        arvorePorData.adicionar(livro2);
        arvorePorData.adicionar(livro3);
        arvorePorData.adicionar(livro4);
        arvorePorData.adicionar(livro5);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha a opcao:");
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
