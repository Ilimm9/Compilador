/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructura;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author peter
 */
public class LlamadaFuncion {
    
    private String nombre;
    private LinkedHashMap<String, String> parametros = new LinkedHashMap<>(); //nombre paramaetro - tipo parametro 
    private int filaInicial;
    private int columna;
    private String contexto; //estatico o no estatico
    private String contextoFuncionLlamada; //estatico o no estatico
    
    public void agregarParametro(String llave, String valor) {
        parametros.put(llave, valor);
        System.out.println("Parámetro agregado: " + llave + " -> " + valor);
    }

    // Método para recuperar el valor asociado a una llave
    public String obtenerValor(String llave) {
        if (parametros.containsKey(llave)) {
            return parametros.get(llave);
        } else {
            System.out.println("La llave '" + llave + "' no existe.");
            return null;
        }
    }

    // Método para mostrar todos los parámetros (opcional)
    public void mostrarParametros() {
        System.out.println("Parámetros almacenados:");
        parametros.forEach((key, value) -> System.out.println(key + " -> " + value));
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<String, String> getParametros() {
        return parametros;
    }

    public int getFilaInicial() {
        return filaInicial;
    }

    public void setFilaInicial(int filaInicial) {
        this.filaInicial = filaInicial;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public String getContextoFuncionLlamada() {
        return contextoFuncionLlamada;
    }

    public void setContextoFuncionLlamada(String contextoFuncionLlamada) {
        this.contextoFuncionLlamada = contextoFuncionLlamada;
    }

    @Override
    public String toString() {
        return "LlamadaFuncion{" + "nombre=" + nombre + ", parametros=" + parametros + ", filaInicial=" + filaInicial + ", columna=" + columna + ", contexto=" + contexto + ", contextoFuncionLlamada=" + contextoFuncionLlamada + '}';
    }
    
    
    
    
    
}
