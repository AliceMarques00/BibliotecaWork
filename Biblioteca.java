import java.io.*;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Livro> livros;

    public Biblioteca() {
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        carregarUsuarios();
        carregarLivros();
    }

    public void adicionarUsuario(Usuario usuario) throws UsuarioJaCadastradoException {
        for (Usuario u : usuarios) {
            if (u.getNome().equals(usuario.getNome())) {
                throw new UsuarioJaCadastradoException("Usuário já cadastrado: " + usuario.getNome());
            }
        }
        usuarios.add(usuario);
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void adicionarUsuarios(Usuario usuario) {  //INSTANCIANDO NA INTERFACE
        usuarios.add(usuario);
        salvarUsuarios();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        salvarLivros();
    }


    public Usuario buscarUsuario(String nome) { //INSTANCIANDO NA INTERFACE
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public Item buscarItem(String titulo) { //INSTANCIADO NA INTERFACE
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro; // Retorna um objeto do tipo Livro, mas como Item(POLIMORFISM)O
            }
        }
        return null;
    }

    public void salvarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getNome());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os usuários: " + e.getMessage());
        }
    }

    public void carregarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                usuarios.add(new Usuario(linha)); // Criado na class usuario
            }
        } catch (IOException e) {
            System.out.println("Nenhum usuário encontrado.");
        }
    }

    public void salvarLivros() { //INSTANCIADA NA INTERFACE
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("livros.txt"))) {
            for (Livro livro : livros) {
                writer.write(livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.isDisponivel());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os livros: " + e.getMessage());
        }
    }

    public void carregarLivros() {
        try (BufferedReader reader = new BufferedReader(new FileReader("livros.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    Livro livro = new Livro(dados[0], dados[1]);
                    livro.setDisponivel(Boolean.parseBoolean(dados[2]));
                    livros.add(livro);
                }
            }
        } catch (IOException e) {
            System.out.println("Nenhum livro encontrado.");
        }
    }

}
