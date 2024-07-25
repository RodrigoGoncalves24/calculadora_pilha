package Calculadora;

public class Pilha{

        private LinkedListOfString pilha;

        public Pilha() {
            pilha = new LinkedListOfString();
        }

        // Insere no topo da pilha
        public void push(String e) {
            pilha.add(0,  e); // O topo da pilha eh o inicio da lista
        }

        // Remove e retorna o elemento do topo da pilha
        public String pop() {
            // Primeiro verifica se a pilha nao esta vazia
            if (pilha.isEmpty())
                throw new IndexOutOfBoundsException();
            return pilha.removeByIndex(0);
        }

        public String top() {
            // Primeiro verifica se a pilha nao esta vazia
            if (pilha.isEmpty())
                throw new IndexOutOfBoundsException();
            return pilha.get(0);
        }

        public int size(){
            return pilha.size();
        }

        public boolean isEmpty() {
            return pilha.isEmpty();
        }

        public void clear() {
            pilha.clear();
        }

        @Override
        public String toString() {
            return pilha.toString();
        }
    }
