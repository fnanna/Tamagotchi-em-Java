public class Gato extends Pet {
    public Gato(String nome) {
        super(nome);
    }

    @Override
    public void cuidar() {
        if (!vivo) return;
        humor = Math.min(100, humor + 15);
        System.out.println(nome + " ronronou de prazer com o cafuné. 🐱");
    }
}

