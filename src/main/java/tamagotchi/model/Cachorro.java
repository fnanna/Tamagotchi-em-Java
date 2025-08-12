package tamagotchi.model;

public class Cachorro extends Pet {
    public Cachorro(String nome) {
        super(nome);
    }

    @Override
    public void cuidar() {
        System.out.println(this.nome + " está recebendo um carinho na barriga! Ele late feliz!");
        this.humor = Math.min(100, this.humor + 25);
    }

    @Override
    public String mostrarStatus() {
        String statusBase = super.mostrarStatus();
        return statusBase + "Mensagem: " + this.nome + " está abanando o rabo.\n";
    }
}
