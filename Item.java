
public abstract class Item {
    private String titulo;
    private boolean disponivel;

    public Item(String titulo) {
        this.titulo = titulo;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }//INSTANCIADO NA INTERFACE

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void alugar() {
        this.disponivel = false;
    }

    public void devolver() {
        this.disponivel = true;
    }
}
