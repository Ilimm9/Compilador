/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Errores;

import estructura.FuncionNoEstatica;

/**
 *
 * @author peter
 */
public class ErrorFuncionNoEstatica {
    
    private String mensajeError;
    private FuncionNoEstatica funcionNoEstatica;

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public FuncionNoEstatica getFuncionNoEstatica() {
        return funcionNoEstatica;
    }

    public void setFuncionNoEstatica(FuncionNoEstatica funcionNoEstatica) {
        this.funcionNoEstatica = funcionNoEstatica;
    }
    
    @Override
    public String toString() {
        return "Error Semantico en Funcion No Estatica en la linea:  " + funcionNoEstatica.getFilaInicial()+
        " => " + mensajeError;
    }

//    @Override
//    public String toString() {
//        return "ErrorFuncionNoEstatica{" + "mensajeError=" + mensajeError + ", funcionNoEstatica=" + funcionNoEstatica + '}';
//    }
    
    
    
}
