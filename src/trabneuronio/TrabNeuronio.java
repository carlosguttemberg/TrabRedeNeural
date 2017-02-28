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
        
        int [][]x = {{0, 0, 1, 1},{0, 1, 0, 1}};//entrada de dados
        double [][]w={{-0.5,0.5,-0.3},{-0.5,-0.2,0.75},{0.5,-0.2,-0.75}};//valores da função
        int [][] d= {{0, 1, 0, 0},{0, 0, 1, 0}, {0, 1, 1, 0}};//saída esperada
        int resultadoNeuronio1, resultadoNeuronio2, resultadoNeuronio3;
        boolean [] continua = {true, true, true};
        boolean [] ajustaNeuronio = {true, true, true};
        boolean parar = true;
        int max_int = 1000, epocas=0;
        
        
        Percp neuronio1 = new Percp();
        neuronio1.setValorCorte(0.05);
        neuronio1.setOrdem(0);
        
        Percp neuronio2 = new Percp();
        neuronio2.setValorCorte(0.07);
        neuronio2.setOrdem(0);
        
        Percp neuronio3 = new Percp();
        neuronio3.setValorCorte(0.07);
        neuronio3.setOrdem(0);
        
        while ((epocas < max_int)&&(parar)){
            parar=false;
            
            for (int i=0;i < x[0].length;i++){
                resultadoNeuronio1 = neuronio1.treinar(x[0][i], x[1][i], w[0]);
                resultadoNeuronio2 = neuronio2.treinar(x[0][i], x[1][i], w[1]);
                resultadoNeuronio3 = neuronio3.treinar(resultadoNeuronio1, resultadoNeuronio2, w[2]);
                
                //Mostrando a aprendizagem
                System.out.println("Epoca " + epocas +
                                   "( "    + resultadoNeuronio1 + 
                                   "  ,"   + resultadoNeuronio2 + 
                                   ")  ="  + resultadoNeuronio3  +
                                   "  w1=" + w[2][0] +
                                   "  w2=" + w[2][1] +
                                   "  w0=" + w[2][2]);
                
                if (ajustaNeuronio[0]){
                   if((resultadoNeuronio1==1)&&(d[0][i]==0)){
                       neuronio1.ajustar_pesos(false,w[0]); //Deve diminuir os pesos
                       neuronio1.setOrdem(neuronio1.getOrdem() + 1);
                       ajustaNeuronio[0] = true;
                       continua[0]=true;
                   }else if ((resultadoNeuronio1==0)&&(d[0][i]==1)){
                       neuronio1.ajustar_pesos(true,w[0]); //Deve aumentar os pesos
                       neuronio1.setOrdem(neuronio1.getOrdem() + 1);
                       ajustaNeuronio[0] = true;
                       continua[0]=true;
                   }
                   if (neuronio1.getOrdem() >= w.length){
                       neuronio1.setOrdem(0);
                   }
                }
                
                if (ajustaNeuronio[1]){
                   if((resultadoNeuronio2==1)&&(d[1][i]==0)){
                       neuronio2.ajustar_pesos(false,w[1]); //Deve diminuir os pesos
                       neuronio2.setOrdem(neuronio2.getOrdem() + 1);
                       continua[1]=true;
                       ajustaNeuronio[1] = true;
                   }else if ((resultadoNeuronio2==0)&&(d[1][i]==1)){
                       neuronio2.ajustar_pesos(true,w[1]); //Deve aumentar os pesos
                       neuronio2.setOrdem(neuronio2.getOrdem() + 1);
                       continua[1]=true;
                       ajustaNeuronio[1] = true;
                   }
                   if (neuronio2.getOrdem() >= w.length){
                       neuronio2.setOrdem(0);
                   }
                }
                
                if (ajustaNeuronio[2]){
                   if((resultadoNeuronio3==1)&&(d[2][i]==0)){
                       neuronio3.ajustar_pesos(false,w[2]); //Deve diminuir os pesos
                       neuronio3.setOrdem(neuronio3.getOrdem() + 1);
                       continua[2]=true;
                       ajustaNeuronio[2] = true;
                   }else if ((resultadoNeuronio3==0)&&(d[2][i]==1)){
                       neuronio3.ajustar_pesos(true,w[2]); //Deve aumentar os pesos
                       neuronio3.setOrdem(neuronio3.getOrdem() + 1);
                       continua[2]=true;
                       ajustaNeuronio[2] = true;
                   }
                   
                   if (neuronio3.getOrdem() >= w.length){
                       neuronio3.setOrdem(0);
                   }
                }
            }
            
            for (int i = 0; i < ajustaNeuronio.length; i++) {
                if(continua[i] == true){
                    parar = true;
                }
                if(continua[i] == false){
                    ajustaNeuronio[i] = true;
                }
            }
            System.out.println("_____________________________");
            epocas++;
        }
    }
    
}
