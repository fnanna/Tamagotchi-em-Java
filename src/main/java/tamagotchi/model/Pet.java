package tamagotchi.model;

public abstract class Pet implements PetInterface {
    protected String nome;
    protected int fome;
    protected int energia;
    protected int humor;

    public Pet(String nome) {
        this.nome = nome;
        this.fome = 50;
        this.energia = 100;
        this.humor = 50;
    }

    // Getters para encapsulamento
    public String getNome() { return this.nome; }
    public int getFome() { return this.fome; }
    public int getEnergia() { return this.energia; }
    public int getHumor() { return this.humor; }

    @Override
    public void brincar() {
        this.energia = Math.max(0, this.energia - 20); // Garante que o atributo não passe de 0
        this.humor = Math.min(100, this.humor + 20); // Garante que o atributo não passe de 100
    }

    // Implementação dos métodos da interface Interativo
    @Override
    public void alimentar() {
        this.fome = Math.max(0, this.fome - 30);
    }

    // Ações específicas que não estão na interface
    public void dormir() {
        this.energia = 100;
        this.fome = Math.min(100, this.fome + 10);
    }

    // Método abstrato polimórfico
    public abstract void cuidar();

    // Simula a passagem do tempo
    public void passarTempo() {
        this.fome = Math.min(100, this.fome + 5);
        this.energia = Math.max(0, this.energia - 5);
        this.humor = Math.max(0, this.humor - 2);
    }


    @Override
    public String mostrarStatus() {
        return String.format(
                "Nome: %s\nhumor: %d\nFome: %d\nEnergia: %d\n",
                nome,humor, fome, energia
        );
    }

}
