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
        boolean continua=true;
        int max_int = 200, epocas=0;
        
        
        Percp neuronio1 = new Percp();
        neuronio1.setValorCorte(0.3);
        neuronio1.setOrdem(0);
        
        Percp neuronio2 = new Percp();
        neuronio2.setValorCorte(0.4);
        neuronio2.setOrdem(0);
        
        Percp neuronio3 = new Percp();
        neuronio3.setValorCorte(0.6);
        neuronio3.setOrdem(0);
        
        while ((epocas < max_int)&&(continua)){
            continua=false;
            
            for (int i=0;i < x[0].length;i++){
                resultadoNeuronio1 = neuronio1.treinar(x[0][i], x[1][i], w[0]);
                resultadoNeuronio2 = neuronio2.treinar(x[0][i], x[1][i], w[1]);
                resultadoNeuronio3 = neuronio3.treinar(resultadoNeuronio1, resultadoNeuronio2, w[2]);
                
                //Mostrando a aprendizagem
                System.out.println("( "    + resultadoNeuronio1 + 
                                   "  ,"   + resultadoNeuronio2 + 
                                   ")  ="  + resultadoNeuronio3  +
                                   "  Neu1w1=" + w[0][0] +
                                   "  Neu1w2=" + w[0][1] +
                                   "  Neu1w0=" + w[0][2]);
                
                
                if((resultadoNeuronio1==1)&&(d[0][i]==0)){
                    neuronio1.ajustar_pesos(false,w[0]); //Deve diminuir os pesos
                    neuronio1.setOrdem(neuronio1.getOrdem() + 1);
                    continua=true;
                }else if ((resultadoNeuronio1==0)&&(d[0][i]==1)){
                    neuronio1.ajustar_pesos(true,w[0]); //Deve aumentar os pesos
                    neuronio1.setOrdem(neuronio1.getOrdem() + 1);
                    continua=true;
                }
                if (neuronio1.getOrdem() >= w.length){
                    neuronio1.setOrdem(0);
                }
            }
            
            epocas++;
        }
    }
    
}
