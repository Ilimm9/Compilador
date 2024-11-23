/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Errores;

import estructura.FuncionEstatica;

/**
 *
 * @author peter
 */
public class ErrorFuncionEstatica {
    
    private String mensajeError;
    private FuncionEstatica funcionEstaticaError;

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public FuncionEstatica getFuncionEstaticaError() {
        return funcionEstaticaError;
    }

    public void setFuncionEstaticaError(FuncionEstatica funcionEstaticaError) {
        this.funcionEstaticaError = funcionEstaticaError;
    }
    
    @Override
    public String toString() {
        return "Error Semantico en FuncionEstatica en la linea:  " + funcionEstaticaError.getFilaInicial()+
        " => " + mensajeError;
    }

//    @Override
//    public String toString() {
//        return "ErrorFuncionEstatica{" + "mensajeError=" + mensajeError + ", funcionEstaticaError=" + funcionEstaticaError + '}';
//    }
    
    
    
    
}
