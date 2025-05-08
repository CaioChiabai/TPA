package app;

import lib.ArvoreBinaria;
import lib.IArvoreBinaria;

import java.util.Comparator;
import java.util.Scanner;

public class AppLivro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Deseja ordenar os livros por:");
        System.out.println("1. ID");
        System.out.println("2. Nome");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        Comparator<Livro> comparadorPrincipal = (opcao == 2)
                ? new ComparadorLivroPorNome()
                : new ComparadorLivroPorId();

        IArvoreBinaria<Livro> arvore = new ArvoreBinaria<>(comparadorPrincipal);

        Livro livro1 = new Livro(1, "A Guerra dos Tronos", "George R. R. Martin", "Excelente começo para uma série épica.", "10/3/2020", 9);
        Livro livro2 = new Livro(2, "O Hobbit", "J. R. R. Tolkien", "Uma jornada leve e mágica.", "22/5/2021", 8);
        Livro livro3 = new Livro(3, "Dom Quixote", "Miguel de Cervantes", "Um clássico com muito humor e crítica.", "5/11/2022", 7);
        Livro livro4 = new Livro(4, "1984", "George Orwell", "Assustadoramente atual.", "14/8/2020", 10);
        Livro livro5 = new Livro(5, "A Revolução dos Bichos", "George Orwell", "Fábula crítica e direta.", "30/1/2024", 9);

        arvore.adicionar(livro1);
        arvore.adicionar(livro2);
        arvore.adicionar(livro3);
        arvore.adicionar(livro4);
        arvore.adicionar(livro5);

        while (true) {
            System.out.println("\nEscolha a opcao:");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Buscar livro por nome");
            System.out.println("3. Buscar livro por ID");
            System.out.println("4. Listar livros em ordem");
            System.out.println("5. Remover livro");
            System.out.println("6. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    System.out.print("Digite o ID do livro: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do livro: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Digite a data de leitura (dd/MM/yyyy): ");
                    String data = scanner.nextLine();
                    System.out.print("Digite a nota do livro (0-10): ");
                    int nota = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Escreva sua review: ");
                    String review = scanner.nextLine();

                    Livro novoLivro = new Livro(id, nome, autor, review, data, nota);
                    arvore.adicionar(novoLivro);
                    break;

                case 2:
                    System.out.print("Digite o nome do livro a buscar: ");
                    String nomeBusca = scanner.nextLine().trim();
                    Livro livroBuscaNome = new Livro(0, nomeBusca, "", "", "", 0);

                    // Se a árvore já estiver ordenada por nome, pode usar pesquisar direto
                    Livro encontradoPorNome = (comparadorPrincipal instanceof ComparadorLivroPorNome)
                            ? arvore.pesquisar(livroBuscaNome)
                            : arvore.pesquisar(livroBuscaNome, new ComparadorLivroPorNome());

                    System.out.println(encontradoPorNome != null ? encontradoPorNome : "Livro não encontrado.");
                    break;

                case 3:
                    System.out.print("Digite o ID do livro a buscar: ");
                    int idBusca = scanner.nextInt();
                    scanner.nextLine();
                    Livro livroBuscaId = new Livro(idBusca, "", "", "", "", 0);

                    // Se a árvore já estiver ordenada por ID, pode usar pesquisar direto
                    Livro encontradoPorId = (comparadorPrincipal instanceof ComparadorLivroPorId)
                            ? arvore.pesquisar(livroBuscaId)
                            : arvore.pesquisar(livroBuscaId, new ComparadorLivroPorId());

                    System.out.println(encontradoPorId != null ? encontradoPorId : "Livro não encontrado.");
                    break;

                case 4:
                    System.out.println("Livros em ordem:");
                    System.out.println(arvore.caminharEmOrdem());
                    break;

                case 5:
                    System.out.print("Digite o ID ou Nome do livro a remover (de acordo com o critério escolhido): ");
                    String entrada = scanner.nextLine().trim();

                    Livro livroRemover;
                    if (comparadorPrincipal instanceof ComparadorLivroPorId) {
                        int idRemover;
                        try {
                            idRemover = Integer.parseInt(entrada);
                        } catch (NumberFormatException e) {
                            System.out.println("ID inválido.");
                            break;
                        }
                        livroRemover = new Livro(idRemover, "", "", "", "", 0);
                    } else {
                        livroRemover = new Livro(0, entrada, "", "", "", 0);
                    }

                    Livro removido = arvore.remover(livroRemover);
                    System.out.println(removido != null ? "Remoção concluída." : "Livro não encontrado.");
                    break;

                case 6:
                    System.out.println("Encerrando...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
