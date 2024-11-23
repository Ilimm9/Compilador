/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Errores;

import estructura.Variable;

/**
 *
 * @author peter
 */
public class ErrorVariable {
    
    private String mensajeError;
    private Variable variableError;

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Variable getVariableError() {
        return variableError;
    }

    public void setVariableError(Variable variableError) {
        this.variableError = variableError;
    }
    
    @Override
    public String toString() {
        return "Error Semantico en Variable en la linea:  " + variableError.getFila() +
        " => " + mensajeError;
    }

//    @Override
//    public String toString() {
//        return "ErrorVariable{" + "mensajeError=" + mensajeError + ", variableError=" + variableError + '}';
//    }
//    
    
    
}
