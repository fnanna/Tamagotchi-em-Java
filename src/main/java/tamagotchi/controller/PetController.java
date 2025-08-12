package tamagotchi.controller;

import java.util.ArrayList;
import tamagotchi.model.*;

public class PetController {
    private final ArrayList<Pet> pets;
    private Pet petAtual;

    public PetController() {
        pets = new ArrayList<>();
    }

    public void adotarPet(String tipo, String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do pet não pode ser vazio");
        }

        switch (tipo.toLowerCase()) {
            case "gato":
                petAtual = new Gato(nome);
                break;
            case "cachorro":
                petAtual = new Cachorro(nome);
                break;
            case "coelho":
                petAtual = new Coelho(nome);
                break;
            default:
                throw new IllegalArgumentException("Tipo de pet inválido");
        }
        pets.add(petAtual);
    }

    public void removerPet(Pet pet) {
        if (pet == null) return;

        pets.remove(pet);
        if (pet == petAtual) {
            petAtual = pets.isEmpty() ? null : pets.getFirst();
        }
    }

    public void trocarPet(int index) {
        if (index >= 0 && index < pets.size()) {
            petAtual = pets.get(index);
        }
    }

    public ArrayList<Pet> getPets() {
        return new ArrayList<>(pets);
    }

    public Pet getPetAtual() {
        return petAtual;
    }

    public void atualizarTodosPets() {
        for (Pet pet : pets) {
                pet.passarTempo();
        }
    }

}