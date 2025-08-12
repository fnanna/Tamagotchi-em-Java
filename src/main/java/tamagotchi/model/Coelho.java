package tamagotchi.model;


public class Coelho extends Pet {

    public Coelho(String nome) {
        super(nome);
    }

    @Override
    public void cuidar() {
        System.out.println(this.nome + " está recebendo carinho nas orelhas. Ele mexe o narizinho!");
        this.humor = Math.min(100, this.humor + 20);
    }

    @Override
    public void brincar() {
        // Coelhos se cansam mais rápido que outros pets ao brincar
        this.energia = Math.max(0, this.energia - 30);
        this.humor = Math.min(100, this.humor + 25);
    }

    @Override
    public String mostrarStatus() {
        String statusBase = super.mostrarStatus();
        return statusBase + "Mensagem: " + this.nome + " está roendo um pedaço de cenoura.\n";
    }
}

