public abstract class Pet {
    protected String nome;
    protected int fome;
    protected int energia;
    protected int humor;
    protected int tempoAtributosZerados; // contador de tempo com atributos zerados
    protected boolean vivo = true;

    public Pet(String nome) {
        this.nome = nome;
        this.fome = 50;
        this.energia = 50;
        this.humor = 50;
        this.tempoAtributosZerados = 0;
    }

    public abstract void cuidar();

    public void alimentar() {
        if (!vivo) return;
        fome = Math.max(0, fome - 20);
        System.out.println(nome + " saboreou a refeição e ficou mais satisfeito. 🍗");
    }

    public void brincar() {
        if (!vivo) return;
        energia = Math.max(0, energia - 15);
        humor = Math.min(100, humor + 20);
        System.out.println(nome + " se divertiu muito brincando! 🎾");
    }

    public void dormir() {
        if (!vivo) return;
        energia = Math.min(100, energia + 30);
        System.out.println(nome + " dormiu como um anjinho e está revigorado. 😴");
    }

    public void passarTempo() {
        if (!vivo) return;
        fome = Math.min(100, fome + 10);
        energia = Math.max(0, energia - 10);
        humor = Math.max(0, humor - 5);

        // Verifica se todos os atributos estão zerados
        if (fome == 100 && energia == 0 && humor == 0) {
            tempoAtributosZerados++;
        } else {
            tempoAtributosZerados = 0; // reseta se melhorar
        }

        // Se passar 3 ciclos com atributos críticos, o pet morre
        if (tempoAtributosZerados >= 3) {
            vivo = false;
            System.out.println("💀 " + nome + " não resistiu e partiu para o céu dos pets... 🌈");
        }
    }

    public String mostrarStatus() {
        if (!vivo) {
            return "💔 " + nome + " infelizmente faleceu... 😢";
        }
        String status = "Nome: " + nome + "\nFome: " + fome + "\nEnergia: " + energia + "\nHumor: " + humor + "\n";
        if (fome >= 80) status += nome + " está faminto! 🍽️\n";
        if (energia <= 20) status += nome + " está exausto! 💤\n";
        if (humor <= 20) status += nome + " parece triste...😔\n";
        return status;
    }

    public boolean estaVivo() {
        return vivo;
    }

    public String getNome() {
        return nome;
    }
}
