/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Errores;

import estructura.LlamadaFuncion;

/**
 *
 * @author peter
 */
public class ErrorLlamadaFuncion {
    
    private String mensajeError;
    private LlamadaFuncion llamadaErronea;

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public LlamadaFuncion getLlamadaErronea() {
        return llamadaErronea;
    }

    public void setLlamadaErronea(LlamadaFuncion llamadaErronea) {
        this.llamadaErronea = llamadaErronea;
    }
    
    @Override
    public String toString() {
        return "Error Semantico en llamada a Funcion en la linea:  " + llamadaErronea.getFilaInicial()+
        " => " + mensajeError;
    }

//    @Override
//    public String toString() {
//        return "ErrorLlamadaFuncion{" + "mensajeError=" + mensajeError + ", llamadaErronea=" + llamadaErronea + '}';
//    }
    
    
    
}
