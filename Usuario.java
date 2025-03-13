import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa {
    private List<Item> itensAlugados = new ArrayList<>();

    public Usuario(String nome) {//INSTANCIADO NA INTERFACE
        super(nome);
    }

    public void alugarItem(Item item) { //INSTANCIADO NA INTERFACE
        if (!itensAlugados.isEmpty()) {
            System.out.println("Por favor, devolva o livro antes de alugar outro.");
        }

        if (item.isDisponivel()) {
            itensAlugados.add(item);
            item.setDisponivel(false);
            System.out.println(getNome() + " alugou o livro: " + item.getTitulo());
        } else {
            System.out.println("O livro " + item.getTitulo() + " não está disponível.");
        }
    }

    public void devolverItem(Item item) {
        item.devolver();
    }

    public boolean possuiLivroAlugado() {
        return !itensAlugados.isEmpty();
    }
}
