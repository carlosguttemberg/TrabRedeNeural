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
public class Percp {
    private int ordem;
    private int valorCorte;

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getValorCorte() {
        return valorCorte;
    }

    public void setValorCorte(int valorCorte) {
        this.valorCorte = valorCorte;
    }
    
    public void treinar(double x1, double x2, double[] w){
        double u=0;
        int y=0,ordem=0;
        
        
        u= x1 * w[0] + x2 * w[1] + w[2];
                
        //Calculando a saída
        if (u >= 0){
          y=1;
        }else{
          y=0;
        }
                
        //Mostrando a aprendizagem
        System.out.println("( "    + x1 + 
                           "  ,"   + x2 + 
                           ")  ="  + y  +
                           "  w1=" + w[0] +
                           "  w2=" + w[1] +
                           "  w0=" + w[2]);
    }
    
    private void ajustar_pesos(boolean aumentar, int ordem, double [] w){
        if (aumentar){
            w[ordem]+=this.valorCorte;
        }else{
            w[ordem]-=this.valorCorte;
        }
    }
}
