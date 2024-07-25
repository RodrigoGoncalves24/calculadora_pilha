package Calculadora;

public class LinkedListOfString {

    // Classe interna Node
    private class Node {
        public String element;
        public Node next;

        public Node(String element) {
            this.element = element;
            next = null;
        }

        public Node(String element, Node next) {
            this.element = element;
            this.next = next;
        }
    }



    // Referência para o primeiro elemento da lista encadeada.
    private Node head;
    // Referência para o último elemento da lista encadeada.
    private Node tail;
    // Contador para a quantidade de elementos que a lista contem.
    private int count;


    /**
     * Construtor da lista.
     */
    public LinkedListOfString() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Adiciona um elemento ao final da lista.
     * @param element elemento a ser adicionado ao final da lista
     */
    public void add(String element)  { // O(1)
        Node n = new Node(element);
        if (head == null) {
            head = n;
        } else {
            tail.next = n;
        }
        tail = n;
        count++;
    }

    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public String get(int index) { // O(n)
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        if (index == count-1)
            return tail.element;

        Node aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return aux.element;
    }

    /**
     * Retorna true se a lista contem o elemento especificado.
     * @param element o elemento a ser testado
     * @return true se a lista contem o elemento especificado
     */
    public boolean contains(String element) { // O(n)
        Node aux = head;
        while(aux != null) {
            if (aux.element.equals(element)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    /* 2 - Implementar em Estudo.LinkedListOfInteger métodos addFirts() e removeFirst()*/
    public void addFirst(String element) { //Elemento auxiliar ganha como link o elemento do head, head ganha elemento euxiliar, sem a perda da linkagem
        Node aux = new Node(element);
        if (head == null){
            head = aux;
        }
        aux.next = head;
        head = aux;
    }

    /**Remove o primeiro indice da lista**/
    public void removeFirst() {
        Node aux = head;
        if (head == null) {
            throw new IndexOutOfBoundsException("Lista esta vazia");
        }
        Node velho = head;
        head = aux.next;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        Node aux = head;

        while (aux != null) {
            s.append(aux.element.toString());
            s.append("\n");
            aux = aux.next;
        }

        return s.toString();
    }


    ////////////////////////////////////////////////////////////////

    /**
     * Substitui o elemento armanzenado em uma determinada posicao da lista pelo
     * elemento indicado.
     * @param index a posicao da lista
     * @param element o elemento a ser armazenado na lista
     * @return o elemento armazenado anteriormente na posicao da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public String set(int index, String element) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        String elem = null;

        if (index == count - 1) { // se for o ultimo, não precisa percorrer a lista
            elem = tail.element;
            tail.element = element;
            return elem;
        }

        Node aux = head;
        for (int i = 0; i < index; i++) { // vai ate a posicao index
            aux = aux.next;
        }
        elem = aux.element;
        aux.element = element;
        return (elem);
    }

    /**
     * Insere um elemento em uma determinada posicao da lista.
     * @param index a posicao da lista onde o elemento sera inserido
     * @param element elemento a ser inserido
     * @throws IndexOutOfBoundsException se (index < 0 || index > size())
     */
    public void add(int index, String element) {
        // Primeiro verifica se index eh valido
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }

        // Cria o nodo
        Node n = new Node(element);

        // "Encadear" o nodo criado na lista
        if (index == 0) { // se insercao no inicio
            if (count == 0) { // se a lista estava vazia
                tail = n;
            }
            else {
                n.next = head;
            }
            head = n;
        }
        else if (index == count) { // se insercao no fim
            tail.next = n;
            tail = n;
        }
        else { // se insercao no meio
            Node ant = head;
            for(int i=0; i<index-1; i++) { // "caminha" ate a posicao index-1
                ant = ant.next;
            }
            n.next = ant.next;
            ant.next = n;
        }

        // Atualiza o count
        count++;
    }

    /**
     * Remove a primeira ocorrencia do elemento na lista, se estiver presente.
     * @param element o elemento a ser removido
     * @return true se a lista contem o elemento especificado
     */
    public boolean remove(String element) {
        if (count == 0)
            return false;

        // Se remocao do primeiro
        if (element.equals(head.element)) {
            if (count == 1) { // se tem apenas 1 elemento na lista
                tail = null;
            }
            head = head.next;
            count--;
            return true;
        }

        Node ant = head; // referencia para o anterior
        Node aux = head.next; // referenca para o elemento que esta sendo verificado

        for(int i=1; i<count; i++) {
            if (element.equals(aux.element)) { // se achou o elemento a ser removido
                if (aux == tail) { // se remocao do ultimo
                    tail = ant;
                    tail.next = null;
                }
                else { // se remocao do meio
                    ant.next = aux.next;
                }
                count--;
                return true;
            }
            aux = aux.next; // passa para o nodo seguinte
            ant = ant.next; // passa para o nodo seguinte
        }

        return false;

    }

    /**
     * Remove o elemento de uma determinada posicao da lista.
     * @param index a posicao da lista
     * @return o elemento que foi removido da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public String removeByIndex(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        // Se remocao do primeiro
        if (index == 0) {
            String elemRemovido = head.element;
            head = head.next;
            if (count == 1) {// se tinha apenas um elemento na lista
                tail = null;
            }
            count--;
            return elemRemovido;
        }

        Node ant = head;
        for (int i = 0; i < index - 1; i++) {
            ant = ant.next;
        }
        String elemRemovido = ant.next.element;
        if (index == count - 1) { //se remocao do ultimo
            tail = ant;
            tail.next = null;
        } else { // se remocao do meio
            ant.next = ant.next.next;
            // Alternativa para o comando acima
            // Node aux = ant.next;
            // ant.next = aux.next;
        }
        count--;
        return elemRemovido;
    }

    /**
     * Retorna o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento.
     * @param element o elemento a ser buscado
     * @return o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     */
    public int indexOf(String element) {
        Node aux = head;
        for(int i=0; i<count; i++) {
            if (aux.element.equals(element)) {
                return i;
            }
            aux = aux.next;
        }
        return -1;
    }



}