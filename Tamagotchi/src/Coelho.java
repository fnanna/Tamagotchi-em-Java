public class Coelho extends Pet {
    public Coelho(String nome) {
        super(nome);
    }

    @Override
    public void cuidar() {
        if (!vivo) return;
        humor = Math.min(100, humor + 12);
        energia = Math.min(100, energia + 8);
        System.out.println(nome + " pulou feliz depois de receber atenção. 🐇");
    }
}

