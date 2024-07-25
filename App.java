package Calculadora;

import java.util.Scanner;

// Tratar cada caracter como uma palavra
public class App {
    //Variáveis de escopo global, usado no programa inteiro
    final static Pilha calculadora = new Pilha();

    private static int countParenteses;
    private static int countColchetes;
    private static int countChaves;

    private static int countTamanho;
    private static double proxValor;
    private static String next;

    private static double resParenteses;
    private static double resColchetes;
    private static double resultado;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expressao, caracter;
        char extraiEspressao;

        System.out.println("\nDigite a sua conta: ");
        expressao = in.nextLine();
        System.out.println("Sua conta é " + expressao);


        // Coloca o resultado da expressão passada por teclado para a pilha
        for (int i = 0; i < expressao.length(); i++) {
            extraiEspressao = expressao.charAt(i);
            caracter = Character.toString(extraiEspressao);
            calculadora.push(caracter);
            countTamanho++;
        }

        prox();

        if(countChaves%2 != 0){
            System.out.println("Erro no fechamento de '{}'");
        }else if(countColchetes%2 != 0){
            System.out.println("Erro no fechamento de '[]'");
        }else if(countParenteses%2 != 0){
            System.out.println("Erro no fechamento de '()'");
        }else{
            System.out.println("Resultado para sua conta igual: "+resultado);
            System.out.println("Tamanho total da pilha igual a "+countTamanho);
        }

    }

    //Resolve conta total, usada em 'idChaves'
    public static double resolvConta(String next) {
        switch (next) {
            case "+": {
                resultado = prox() + resultado;
                break;
            }
            case "-": {
                resultado = prox() - resultado;
                break;
            }
            case "*": {
                resultado = prox() * resultado;
                break;
            }
            case "/": {
                resultado = prox() / resultado;
                break;
            }
            case "^": {
                resultado = Math.pow(prox(), resultado);
                break;
            }
        }
        return resultado;
    }


    public static double prox(){
        //Identificar se a conta continua e se dentro dos colchetes existem chaves - retornar para a variável que chamou ele
        proxValor = 0;
        try{
            next = calculadora.pop();
        }catch (IndexOutOfBoundsException e){
            System.out.println("Resultado: "+resultado);
        }

        switch (next){
            case "0": {
                proxValor = Double.parseDouble(calculadora.pop())*10;
                break;
            }
            case ")":{
                proxValor = idParenteses(next);
                break;
            }
            case "]":{
                proxValor = idColchetes(next);
                break;
            }
            case "}":{
                proxValor = idChaves(next);
                break;
            }
        }

        if(proxValor == 0){
            return  proxValor = Double.parseDouble(next);
        }else{
            return proxValor;
        }

    }

    //Resolve contas dentro do parenteses
    public static double resolveParenteses(String next){
        switch (calculadora.pop()) {
            case "+": {
                resParenteses = prox() + resParenteses;
                break;
            }
            case "-": {
                resParenteses = prox() - resParenteses;
                break;
            }
            case "*": {
                resParenteses = prox() * resParenteses;
                break;
            }
            case "/": {
                resParenteses = prox() / resParenteses;
                break;
            }
            case "^": {
                resParenteses = Math.pow(prox(), resParenteses);
                break;
            }
        }

        return resParenteses;

    }

    //Resolve a conta dentro do colchetes, recebendo valores de dentro de parenteses se for o caso
    public static double resolveColchetes(Double resColchetes){
        switch (calculadora.pop()) {
            case "+": {
                resColchetes = prox() + resColchetes;
                break;
            }
            case "-": {

                resColchetes = prox() - resColchetes;
                break;
            }
            case "*": {

                resColchetes = prox() * resColchetes;
                break;
            }
            case "/": {
                resColchetes = prox() / resColchetes;
                break;
            }
            case "^": {
                resColchetes = Math.pow(prox(), resColchetes);
                break;
            }
        }

        return resColchetes;
    }

    //Começa com o código identificando a chave de abertura
    public static double idChaves(String next){
        switch(next){
            case "}":{
                countChaves++;
                resultado = idColchetes(calculadora.pop());
                next = calculadora.pop();
                resolvConta(next);
            }
            case "{":{
                countChaves++;
                return resultado;

            }
            case "[":{
                countColchetes++;
                return resultado;
            }
        }
        idColchetes(next);
        return resultado;
    }

    //Identifica os colchetes da expressão e faz o método que calcula seu valores internos
    public static double idColchetes(String next) {
        switch (next) {
            case "]":{
                countColchetes++;
                resColchetes = Double.parseDouble(calculadora.pop());
                resColchetes = resolveColchetes(resColchetes);
                calculadora.pop();
            }
            case "[":{
                countColchetes++;
                return resColchetes;
            }

        }
        return idParenteses(next);

    }

    //Método que identifica parenteses e faz método que calcula seus resultados internos
    public static double idParenteses(String next) {
        switch (next) {
            case ")": {
                countParenteses++;
                resParenteses = Double.parseDouble(calculadora.pop());
                resParenteses = resolveParenteses(next);
                calculadora.pop();
            }
            case "(": {
                countParenteses++;
                return resParenteses;
            }

        }
        return resParenteses;
    }
}


