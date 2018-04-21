/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolavl;

import arbolavl.objects.Input;
import arbolavl.objects.Node;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class ArbolAVL {
    static Input input = new Input();
    static Node root = new Node();
    static int currentFactor = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            input = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.INPUT, Input.class);
        } catch (IOException ex) {
            System.out.println("No se encontro el archivo input_numbers.json en /Documents/ArbolAVL, se creara uno nuevo");
            initNumbers();
        }
        
        makeTree(input.getNumbers());
    }
    private static void makeTree(ArrayList<Integer> numbers){
        for(Integer i : numbers){
            putNumber(root, i, 1);
        }
        
        mostrarNodos(root, "|--");
        
        int balance = getBalance(root);
        
        System.out.println(balance);
    }
    

    
    
    private static void putNumber(Node node, int number, int level){
        if(node.getValue() == -1){
            node.setLevel(level);
            node.setValue(number);
        }else{
            if(number > node.getValue()){
                if(node.getRight() == null){
                    node.setRight(new Node());
                    node.getRight().setLevel(level + 1);
                    node.getRight().setValue(number);
                }else{
                    putNumber(node.getRight(), number, level + 1);
                }
            }else{
                if(node.getLeft() == null){
                    node.setLeft(new Node());
                    node.getLeft().setLevel(level + 1);
                    node.getLeft().setValue(number);
                }else{
                    putNumber(node.getLeft(), number, level + 1);
                }
            }
        }
    }
    
    private static int getBalance(Node node){
        currentFactor = node.getLevel();
        balance(node.getRight());
        int right = (currentFactor + 1) - node.getLevel();
        
        currentFactor = node.getLevel();
        balance(node.getLeft());
        int left = (currentFactor + 1) - node.getLevel();
        
        return right - left;
    }
    
    private static void balance(Node node){
        if(currentFactor < node.getLevel()){
            currentFactor = node.getLevel();
        }
        
        if(node.getLeft() != null){
            balance(node.getLeft());
        }
        if(node.getRight() != null){
            balance(node.getRight());
        }
    }
    
    public static void mostrarNodos(Node nodo, String tab){
        System.out.println(tab+"Nodo No. "+nodo.getValue() + " Level: " + nodo.getLevel());
        if(nodo.getRight()!=null){
            mostrarNodos(nodo.getRight(),"   "+tab);
            
        }
        if(nodo.getLeft() != null){
            mostrarNodos(nodo.getLeft(),"   "+tab);
        }
    }
    
    private static void initNumbers(){
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(86);
        numbers.add(65);
        numbers.add(70);
        numbers.add(67);
        numbers.add(73);
        numbers.add(93);
        numbers.add(69);
        numbers.add(25);
        numbers.add(66);
        numbers.add(68);
        numbers.add(47);
        numbers.add(62);
        numbers.add(10);
        numbers.add(60);
        
        Input newInput = new Input(numbers);
        JsonUtils.INSTANCIA.writeJSON(newInput, JsonUtils.FILE_TYPE.INPUT);
        
        input = newInput;
    }
}
