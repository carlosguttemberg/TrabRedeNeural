/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabneuronio;

/**
 *
 * @author carlos
 */
public class TrabNeuronio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int [][]x = {{1, 0, 1, 1},{1, 1, 0, 0}};//entrada de dados
        double [][]w={{0.5, 0.3, -0.6}, {0.9, -0.5, -0.4}, {0.3, 0.5, 0.6}};//valores da função
        int [][] d= {{0, 1, 0, 0},{0, 0, 1, 0}, {0, 1, 1, 0}};//saída esperada
        int resultadoNeuronio1, resultadoNeuronio2, resultadoNeuronio3;
        
        Percp neuronio1 = new Percp();
        Percp neuronio2 = new Percp();
        Percp neuronio3 = new Percp();
        
        
        
    }
    
}
