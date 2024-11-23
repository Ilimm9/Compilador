/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructura;

/**
 *
 * @author peter
 */
public class Variable {
    
    private String contextoFuncion; //hace referencia a cual es la funcion mas cercana desde donde se esta llamando
    private String nombreVariable;
    private String tipoDato;
    private boolean inicializada;
    private int fila;
    private int columna;
    
    private int rangoInicial;
    private int rangoFinal;
    
    public Variable(){
        this.inicializada = false;
        this.contextoFuncion = "";
    }

    public String getNombreVariable() {
        return nombreVariable;
    }

    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getContextoFuncion() {
        return contextoFuncion;
    }

    public void setContextoFuncion(String contextoFuncion) {
        this.contextoFuncion = contextoFuncion;
    }

    public boolean isInicializada() {
        return inicializada;
    }

    public void setInicializada(boolean inicializada) {
        this.inicializada = inicializada;
    }

    public int getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(int rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public int getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(int rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    @Override
    public String toString() {
        return "Variable{" + "contextoFuncion=" + contextoFuncion + ", nombreVariable=" + nombreVariable + ", tipoDato=" + tipoDato + ", inicializada=" + inicializada + ", fila=" + fila + ", columna=" + columna + ", rangoInicial=" + rangoInicial + ", rangoFinal=" + rangoFinal + '}';
    }
    
   

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    
    
    
    
    
}
