public class ListaLivros {
    public static void exibirLivrosRevistas(Biblioteca biblioteca) {
        System.out.println("\n===== LIVROS DISPONÍVEIS =====");
        for (Livro livro : biblioteca.getLivros()) {
            if (livro.isDisponivel()) {
                System.out.println(livro.getTitulo() + " - " + livro.getAutor());
            }
        }

        System.out.println("\n===== LIVROS INDISPONÍVEIS =====");
        for (Livro livro : biblioteca.getLivros()) {
            if (!livro.isDisponivel()) {
                System.out.println(livro.getTitulo() + " - " + livro.getAutor());
            }
        }

    }
}
