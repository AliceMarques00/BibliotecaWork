import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n===== MENU =====");
                System.out.println("1 - Alugar item");
                System.out.println("2 - Devolver item");
                System.out.println("3 - Ver livros disponíveis");
                System.out.println("4 - Cadastrar usuário");
                System.out.println("5 - Cadastrar livro");
                System.out.println("6 - Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = sc.nextInt();
                sc.nextLine(); // Consumir nova linha

                switch (opcao) {
                    case 1:
                        System.out.print("Nome do usuário: ");
                        String nomeUsuario = sc.nextLine();
                        Usuario usuario = biblioteca.buscarUsuario(nomeUsuario);

                        if (usuario != null) {
                            System.out.print("Título do livro: ");
                            String tituloLivro = sc.nextLine();
                            Livro livro = (Livro) biblioteca.buscarItem(tituloLivro);

                            if (livro != null && livro.isDisponivel()) {
                                usuario.alugarItem(livro);
                                biblioteca.salvarLivros(); // Atualiza o arquivo
                            } else {
                                System.out.println("Livro indisponível ou não encontrado.");
                            }
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        break;

                    case 2:
                        System.out.print("Nome do usuário: ");
                        nomeUsuario = sc.nextLine();
                        usuario = biblioteca.buscarUsuario(nomeUsuario);

                        if (usuario != null) {
                            System.out.print("Título do livro: ");
                            String tituloLivro = sc.nextLine();
                            Livro livro = (Livro) biblioteca.buscarItem(tituloLivro);

                            if (livro != null) {
                                usuario.devolverItem(livro);
                                biblioteca.salvarLivros(); // Atualiza o arquivo
                            } else {
                                System.out.println("Livro não encontrado.");
                            }
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        break;

                    case 3:
                        ListaLivros.exibirLivrosRevistas(biblioteca);
                        break;

                    case 4:
                        System.out.print("Nome do novo usuário: ");
                        nomeUsuario = sc.nextLine();
                        if (biblioteca.buscarUsuario(nomeUsuario) != null) {
                            System.out.println("Usuário já cadastrado.");
                        } else {
                            biblioteca.adicionarUsuarios(new Usuario(nomeUsuario));
                            System.out.println("Usuário cadastrado!");
                        }
                        break;

                    case 5:
                        System.out.print("Título do livro: ");
                        String titulo = sc.nextLine();
                        System.out.print("Autor do livro: ");
                        String autor = sc.nextLine();
                        biblioteca.adicionarLivro(new Livro(titulo, autor));
                        System.out.println("Livro cadastrado!");
                        break;

                    case 6:
                        System.out.println("Saindo...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                sc.nextLine(); // Consumir entrada inválida
            }
        }
    }
}
