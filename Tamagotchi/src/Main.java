import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main {
    static ArrayList<Pet> pets = new ArrayList<>();
    static JTextArea statusArea = new JTextArea(10, 30);
    static JLabel imagemPet = new JLabel();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> criarInterface());
    }

    static void criarInterface() {
        JFrame frame = new JFrame("Simulador de Pets");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField nomeField = new JTextField(10);
        String[] tipos = {"Cachorro", "Gato", "Coelho"};
        JComboBox<String> tipoBox = new JComboBox<>(tipos);

        JButton adotarBtn = new JButton("Adotar Pet");
        JButton removerBtn = new JButton("Remover Pet");
        JButton alimentarBtn = new JButton("Alimentar");
        JButton brincarBtn = new JButton("Brincar");
        JButton cuidarBtn = new JButton("Cuidar");
        JButton dormirBtn = new JButton("Dormir");
        JButton tempoBtn = new JButton("Passar Tempo");
        JButton listarBtn = new JButton("Mostrar Status");

        imagemPet.setPreferredSize(new Dimension(150, 150));

        adotarBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            String tipo = (String) tipoBox.getSelectedItem();
            switch (tipo) {
                case "Cachorro" -> {
                    pets.add(new Cachorro(nome));
                    imagemPet.setIcon(new ImageIcon("imagens/cachorro.png"));
                }
                case "Gato" -> {
                    pets.add(new Gato(nome));
                    imagemPet.setIcon(new ImageIcon("imagens/gato.png"));
                }
                case "Coelho" -> {
                    pets.add(new Coelho(nome));
                    imagemPet.setIcon(new ImageIcon("imagens/coelho.png"));
                }
                default -> {
                }
            }
            nomeField.setText("");
            atualizarStatus();
        });

        removerBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            pets.removeIf(p -> p.getNome().equalsIgnoreCase(nome));
            nomeField.setText("");
            atualizarStatus();
        });

        alimentarBtn.addActionListener(e -> interagirPet("alimentar"));
        brincarBtn.addActionListener(e -> interagirPet("brincar"));
        cuidarBtn.addActionListener(e -> interagirPet("cuidar"));
        dormirBtn.addActionListener(e -> interagirPet("dormir"));
        tempoBtn.addActionListener(e -> {
            for (Pet p : pets) p.passarTempo();
            atualizarStatus();
        });
        listarBtn.addActionListener(e -> atualizarStatus());

        frame.add(new JLabel("Nome:"));
        frame.add(nomeField);
        frame.add(tipoBox);
        frame.add(adotarBtn);
        frame.add(removerBtn);
        frame.add(alimentarBtn);
        frame.add(brincarBtn);
        frame.add(cuidarBtn);
        frame.add(dormirBtn);
        frame.add(tempoBtn);
        frame.add(listarBtn);
        frame.add(new JScrollPane(statusArea));
        frame.add(imagemPet);

        frame.pack();
        frame.setVisible(true);
    }

    static void interagirPet(String acao) {
        String nome = JOptionPane.showInputDialog("Digite o nome do Pet:");
        for (Pet p : pets) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                if (!p.estaVivo()) {
                    JOptionPane.showMessageDialog(null, p.getNome() + " já faleceu... 😢");
                    return;
                }
                switch (acao) {
                    case "alimentar": p.alimentar(); break;
                    case "brincar": p.brincar(); break;
                    case "cuidar": p.cuidar(); break;
                    case "dormir": p.dormir(); break;
                }
                atualizarStatus();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Pet não encontrado.");
    }

    static void atualizarStatus() {
        StringBuilder sb = new StringBuilder();
        for (Pet p : pets) {
            sb.append(p.mostrarStatus()).append("\n");
        }
        statusArea.setText(sb.toString());
    }
}
