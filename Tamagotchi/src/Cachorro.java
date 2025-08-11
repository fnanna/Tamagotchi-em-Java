public class Cachorro extends Pet {
    public Cachorro(String nome) {
        super(nome);
    }

    @Override
    public void cuidar() {
        if (!vivo) return;
        humor = Math.min(100, humor + 10);
        energia = Math.min(100, energia + 10);
        System.out.println(nome + " abanou o rabo e ficou todo feliz com o carinho! 🐶");
    }
}
