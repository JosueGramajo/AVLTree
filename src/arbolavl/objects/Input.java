/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolavl.objects;

import java.util.ArrayList;

/**
 *
 * @author alumno
 */
public class Input extends BaseObject{
    private ArrayList<Integer> numbers;

    public Input(){}
    
    public Input(ArrayList<Integer> numbers){
        this.numbers = numbers;
    }
    /**
     * @return the numbers
     */
    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    /**
     * @param numbers the numbers to set
     */
    public void setNumbers(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }
    
    
}
