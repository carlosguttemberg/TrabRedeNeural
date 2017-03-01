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
        double [][]w={{-0.1,0.2,-0.3},{-0.5,-0.2,0.75},{-0.5,0.2,-0.75}};//valores da função
        int [][] d= {{0, 1, 0, 0},{0, 0, 1, 0}, {0, 1, 1, 0}};//saída esperada
        int [] resultadoNeu3 = {0, 0, 0, 0};   
        int resultadoNeuronio1, resultadoNeuronio2, resultadoNeuronio3;
        boolean continuar = true;
        int max_int = 1000, epocas=0, verificaCondicao;
        
        
        Percp neuronio1 = new Percp();
        neuronio1.setValorCorte(0.08);
        neuronio1.setOrdem(0);
        
        Percp neuronio2 = new Percp();
        neuronio2.setValorCorte(0.08);
        neuronio2.setOrdem(0);
        
        Percp neuronio3 = new Percp();
        neuronio3.setValorCorte(0.07);
        neuronio3.setOrdem(0);
        
        while ((epocas < max_int)&&(continuar)){
            continuar=false;
            verificaCondicao = 0;
            
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
                
                 //VERIFICAÇÃO DO NEURÔNIO 1
                 if((resultadoNeuronio1==1)&&(d[0][i]==0)){
                     neuronio1.ajustar_pesos(false,w[0]); //Deve diminuir os pesos
                     neuronio1.setOrdem(neuronio1.getOrdem() + 1);
                 }else if ((resultadoNeuronio1==0)&&(d[0][i]==1)){
                     neuronio1.ajustar_pesos(true,w[0]); //Deve aumentar os pesos
                     neuronio1.setOrdem(neuronio1.getOrdem() + 1);
                 }
                 if (neuronio1.getOrdem() >= w.length){
                     neuronio1.setOrdem(0);
                 }
       
                 //VERIFICAÇÃO DO NEURONIO 2
                 if((resultadoNeuronio2==1)&&(d[1][i]==0)){
                     neuronio2.ajustar_pesos(false,w[1]); //Deve diminuir os pesos
                     neuronio2.setOrdem(neuronio2.getOrdem() + 1);
                 }else if ((resultadoNeuronio2==0)&&(d[1][i]==1)){
                     neuronio2.ajustar_pesos(true,w[1]); //Deve aumentar os pesos
                     neuronio2.setOrdem(neuronio2.getOrdem() + 1);    
                 }
                 if (neuronio2.getOrdem() >= w.length){
                     neuronio2.setOrdem(0);
                 }
               
                 //VERIFICAÇÃO DO NEURONIO 3 RESULTADO DA XOR
                 if((resultadoNeuronio3==1)&&(d[2][i]==0)){
                     neuronio3.ajustar_pesos(false,w[2]); //Deve diminuir os pesos
                     neuronio3.setOrdem(neuronio3.getOrdem() + 1);
                 }else if ((resultadoNeuronio3==0)&&(d[2][i]==1)){
                     neuronio3.ajustar_pesos(true,w[2]); //Deve aumentar os pesos
                     neuronio3.setOrdem(neuronio3.getOrdem() + 1);                 
                 }
                   
                 if (neuronio3.getOrdem() >= w.length){
                     neuronio3.setOrdem(0);
                 }
                 
                 //PREENCHENDO O VETOR DE RESULTADOS DO NEURONIO 3
                 resultadoNeu3[i] = resultadoNeuronio3;  
            }
            
            for (int i = 0; i < resultadoNeu3.length; i++) {
                //VERIFICANDO SE TODOS OS VALORES DO RESULTADO DO NEURONIO 3 ESTA IGUAL A MATRIZ COM A SAIDA ESPERADA
                if (resultadoNeu3[i] == d[2][i]){
                    verificaCondicao++;
                }
            }
            
            //VERIFICANDO SE O LOOP DEVE CONTINUAR CASO EXISTA ALGUM VALOR QUE NÃO ESTÁ IGUAL AO DA SAIDA ESPERADA
            if (verificaCondicao == 4){
               continuar = false;  
            }else{
               continuar = true;    
            }
            
            System.out.println("_____________________________");
            epocas++;
        }
    }
    
}

