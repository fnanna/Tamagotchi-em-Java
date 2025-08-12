package tamagotchi.model;

public class Gato extends Pet {
    public Gato(String nome) {
        super(nome);
    }

    @Override
    public void cuidar() {
        System.out.println(this.nome + " está recebendo um cafuné. Ele ronrona!");
        this.humor = Math.min(100, this.humor + 15);
    }

    @Override
    public String mostrarStatus() {
        String statusBase = super.mostrarStatus();
        return statusBase + "Mensagem: " + this.nome + " está amassando pãozinho.\n";
    }
}

