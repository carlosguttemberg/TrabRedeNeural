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
    private double valorCorte;

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public double getValorCorte() {
        return valorCorte;
    }

    public void setValorCorte(double valorCorte) {
        this.valorCorte = valorCorte;
    }
    
    public int treinar(double x1, double x2, double[] w){
        double u=0;
        int y=0,ordem=0;
        
        
        u= x1 * w[0] + x2 * w[1] + w[2];
                
        //Calculando a saída
        if (u >= 0){
          y=1;
        }else{
          y=0;
        }
        return y;
    }
    
    public void ajustar_pesos(boolean aumentar, double [] w){
        if (aumentar){
            w[this.ordem]+=this.valorCorte;
        }else{
            w[this.ordem]-=this.valorCorte;
        }
    }
}
