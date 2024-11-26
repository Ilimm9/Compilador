/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructura;

import Errores.ErrorFuncionEstatica;
import Errores.ErrorFuncionNoEstatica;
import Errores.ErrorLlamadaFuncion;
import Errores.ErrorVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author peter
 */
public class Estructura {

    private static List<FuncionEstatica> funcionesEstaticas = new ArrayList<>();
    private static List<FuncionNoEstatica> funcionesNoEstaticas = new ArrayList<>();
    private static List<Variable> variablesDeclaradas = new ArrayList<>();
    private static List<Variable> variablesEnUso = new ArrayList<>();
    private static FuncionMain funcionMain = new FuncionMain();
    private static List<LlamadaFuncion> llamadasFunciones = new ArrayList<>();

    private static List<ErrorVariable> erroresVariables = new ArrayList<>();
    private static List<ErrorFuncionEstatica> erroresFuncionesEstaticas = new ArrayList<>();
    private static List<ErrorFuncionNoEstatica> erroresFuncionesNoEstaticas = new ArrayList<>();
    private static List<ErrorLlamadaFuncion> erroresLlamadaFuncion = new ArrayList<>();

    //=============================================MANEJO DE FUNCIONES ESTATICAS
    // Método para agregar una función estática
    public static boolean agregarFuncionEstatica(FuncionEstatica nuevaFuncionEstatica) {
        if (verificarExistenciaFuncionEstatica(nuevaFuncionEstatica)) {
            ErrorFuncionEstatica errorFuncionEstatica = new ErrorFuncionEstatica();
            errorFuncionEstatica.setMensajeError("La función Estatica ya existe: " + nuevaFuncionEstatica.getNombre());
            errorFuncionEstatica.setFuncionEstaticaError(nuevaFuncionEstatica);
            erroresFuncionesEstaticas.add(errorFuncionEstatica);
            return false;
        }
        funcionesEstaticas.add(nuevaFuncionEstatica);
        System.out.println("Función Estatica agregada exitosamente.");
        return true;
    }

    // Método para verificar si ya existe una función estática
    public static boolean verificarExistenciaFuncionEstatica(FuncionEstatica funcion) {
        for (FuncionEstatica existente : funcionesEstaticas) {
            // Verificar si tienen el mismo tipo de retorno
            if (!existente.getTipoRetorno().equals(funcion.getTipoRetorno())) {
                continue; // Seguir buscando si el tipo de retorno no coincide
            }
            // Verificar si tienen el mismo nombre
            if (existente.getNombre().equals(funcion.getNombre())) {
                // Verificar si tienen la misma cantidad de parámetros
                if (existente.getParametros().size() == funcion.getParametros().size()) {
                    // Obtener las llaves de los parámetros
                    List<String> llavesExistente = new ArrayList<>(existente.getParametros().keySet());
                    List<String> llavesFuncion = new ArrayList<>(funcion.getParametros().keySet());

                    boolean parametrosCoinciden = true;

                    // Comparar parámetros usando un bucle clásico
                    for (int i = 0; i < llavesExistente.size(); i++) {
                        String llaveExistente = llavesExistente.get(i);
                        String llaveFuncion = llavesFuncion.get(i);

                        String tipoExistente = existente.getParametros().get(llaveExistente);
                        String tipoFuncion = funcion.getParametros().get(llaveFuncion);

//                    System.out.println("Parámetro existente: " + llaveExistente + " -> " + tipoExistente);
//                    System.out.println("Parámetro nuevo: " + llaveFuncion + " -> " + tipoFuncion);
                        // Verificar si los tipos de los parámetros coinciden
                        if (!tipoExistente.equals(tipoFuncion)) {
                            parametrosCoinciden = false;
                            break;
                        }
                    }

                    if (parametrosCoinciden) {
                        return true; // La función ya existe
                    }
                }
            }
        }
        return false; // La función no existe
    }

    public static boolean verificarRetornosFuncionesEstaticas() {

        for (FuncionEstatica funcion : funcionesEstaticas) {
            String tipoRetorno = funcion.getTipoRetorno();
            String retornoAsignado = funcion.getRetornoAsignado();

            if (tipoRetorno != null && retornoAsignado != null) {
                if (retornoAsignado.equals("IDENTIFICADOR")) {
                    //verificar si la variable esta declarada
                    //verificar si el tipo de dato es el correcto

                    if (identificadorDeclarado(funcion)) {
                        continue;
                    }
                }
                //si es un retorno valido entero, char, bool
                if (tipoRetorno.equals(retornoAsignado)) {
                    return false;
                } else if (esValidoRetorno(retornoAsignado)) {
                    ErrorFuncionEstatica errorFuncionEstatica = new ErrorFuncionEstatica();
                    errorFuncionEstatica.setMensajeError("El tipo de retorno no coincide con el tipo de dato devuelto: " + funcion.getNombre()
                            + "Retorno esperado: " + funcion.getTipoRetorno() + " Retorno regresado: " + funcion.getRetornoAsignado());
                    errorFuncionEstatica.setFuncionEstaticaError(funcion);
                    erroresFuncionesEstaticas.add(errorFuncionEstatica);
                }
            } else {
                System.out.println("Tipo de retorno no asignado");
            }
        }

        return false;
    }
    
    public static boolean esValidoRetorno(String retorno){
        return retorno.equals("ENTERO") || retorno.equals("FLOTANTE") || retorno.equals("BOOL") 
                || retorno.equals("CHAR") || retorno.equals("CADENA");
    }

    public static boolean identificadorDeclarado(FuncionEstatica funcion) {
        boolean declarado = false;
        boolean compatible = false;
        for (Variable variable : variablesDeclaradas) {
            if (variable.getNombreVariable().equals(funcion.getNombreIdentificadorRetorno())
                    && variable.getRangoInicial() == funcion.getFilaInicial()
                    && variable.getRangoFinal() == funcion.getFilaFinal()) {
                declarado = true;
                if (variable.getTipoDato().equals(funcion.getTipoRetorno())) {
                    compatible = true;
                }
                break;
            }
        }

        if (!declarado) {
            ErrorFuncionEstatica errorFuncionEstatica = new ErrorFuncionEstatica();
            errorFuncionEstatica.setMensajeError("el identificador de retorno: " + funcion.getNombreIdentificadorRetorno()
                    + " no se encuentra declarado en el bloque de la funcion ");
            errorFuncionEstatica.setFuncionEstaticaError(funcion);
            erroresFuncionesEstaticas.add(errorFuncionEstatica);
        } else if (!compatible) {
            ErrorFuncionEstatica errorFuncionEstatica = new ErrorFuncionEstatica();
            errorFuncionEstatica.setMensajeError("el identificador de retorno: " + funcion.getNombreIdentificadorRetorno()
                    + " no es compatible con el retorno solicitado por la funcion ");
            errorFuncionEstatica.setFuncionEstaticaError(funcion);
            erroresFuncionesEstaticas.add(errorFuncionEstatica);
        }

        return (declarado || compatible);
    }

    public static boolean identificadorDeclarado(FuncionNoEstatica funcion) {
        System.out.println("entramos al identificar funcion " + funcion.getNombre());
        boolean declarado = false;
        boolean compatible = false;
        for (Variable variable : variablesDeclaradas) {
            if (variable.getNombreVariable().equals(funcion.getNombreIdentificadorRetorno())
                    && variable.getRangoInicial() == funcion.getFilaInicial()
                    && variable.getRangoFinal() == funcion.getFilaFinal()) {
                declarado = true;
                if (variable.getTipoDato().equals(funcion.getTipoRetorno())) {
                    compatible = true;
                }
                break;
            }
        }

        if (!declarado) {
            ErrorFuncionNoEstatica errorFuncionNoEstatica = new ErrorFuncionNoEstatica();
            errorFuncionNoEstatica.setMensajeError("el identificador de retorno: " + funcion.getNombreIdentificadorRetorno()
                    + " no se encuentra declarado en el bloque de la funcion ");
            errorFuncionNoEstatica.setFuncionNoEstatica(funcion);
            erroresFuncionesNoEstaticas.add(errorFuncionNoEstatica);
        } else if (!compatible) {
            ErrorFuncionNoEstatica errorFuncionNoEstatica = new ErrorFuncionNoEstatica();
            errorFuncionNoEstatica.setMensajeError("el identificador de retorno: " + funcion.getNombreIdentificadorRetorno()
                    + " no es compatible con el retorno solicitado por la funcion ");
            errorFuncionNoEstatica.setFuncionNoEstatica(funcion);
            erroresFuncionesNoEstaticas.add(errorFuncionNoEstatica);
        }

        return (declarado || compatible);
    }

    // Método para agregar una función no estática
    public static boolean agregarFuncionNoEstatica(FuncionNoEstatica nuevaFuncionNoEstatica) {
        System.out.println("nuevaFuncionNoEstatica = " + nuevaFuncionNoEstatica);
        if (verificarExistenciaFuncionNoEstatica(nuevaFuncionNoEstatica)) {
            ErrorFuncionNoEstatica errorFuncionNoEstatica = new ErrorFuncionNoEstatica();
            errorFuncionNoEstatica.setMensajeError("La función No estatica ya existe: " + nuevaFuncionNoEstatica.getNombre());
            errorFuncionNoEstatica.setFuncionNoEstatica(nuevaFuncionNoEstatica);
            erroresFuncionesNoEstaticas.add(errorFuncionNoEstatica);
            return false;
        }
        funcionesNoEstaticas.add(nuevaFuncionNoEstatica);
        System.out.println("Función No estatica agregada exitosamente.");
        return true;
    }

    // Método para verificar si ya existe una función estática
    public static boolean verificarExistenciaFuncionNoEstatica(FuncionNoEstatica funcion) {
        for (FuncionNoEstatica existente : funcionesNoEstaticas) {
            // Verificar si tienen el mismo tipo de retorno
            if (!existente.getTipoRetorno().equals(funcion.getTipoRetorno())) {
                continue; // Seguir buscando si el tipo de retorno no coincide
            }
            // Verificar si tienen el mismo nombre
            if (existente.getNombre().equals(funcion.getNombre())) {
                // Verificar si tienen la misma cantidad de parámetros
                if (existente.getParametros().size() == funcion.getParametros().size()) {
                    // Obtener las llaves de los parámetros
                    List<String> llavesExistente = new ArrayList<>(existente.getParametros().keySet());
                    List<String> llavesFuncion = new ArrayList<>(funcion.getParametros().keySet());

                    boolean parametrosCoinciden = true;

                    // Comparar parámetros usando un bucle clásico
                    for (int i = 0; i < llavesExistente.size(); i++) {
                        String llaveExistente = llavesExistente.get(i);
                        String llaveFuncion = llavesFuncion.get(i);

                        String tipoExistente = existente.getParametros().get(llaveExistente);
                        String tipoFuncion = funcion.getParametros().get(llaveFuncion);

//                    System.out.println("Parámetro existente: " + llaveExistente + " -> " + tipoExistente);
//                    System.out.println("Parámetro nuevo: " + llaveFuncion + " -> " + tipoFuncion);
                        // Verificar si los tipos de los parámetros coinciden
                        if (!tipoExistente.equals(tipoFuncion)) {
                            parametrosCoinciden = false;
                            break;
                        }
                    }

                    if (parametrosCoinciden) {
                        return true; // La función ya existe
                    }
                }
            }
        }
        return false; // La función no existe
    }

    public static boolean verificarRetornosFuncionesNoEstaticas() {

        for (FuncionNoEstatica funcion : funcionesNoEstaticas) {
            String tipoRetorno = funcion.getTipoRetorno();
            String retornoAsignado = funcion.getRetornoAsignado();

            if (tipoRetorno != null && retornoAsignado != null) {
                if (retornoAsignado.equals("IDENTIFICADOR")) {
                    //verificar si la variable esta declarada
                    //verificar si el tipo de dato es el correcto

                    if (identificadorDeclarado(funcion)) {
                        continue;
                    }
                }
                if (tipoRetorno.equals(retornoAsignado)) {
                    return false;
                } else if (esValidoRetorno(retornoAsignado)) {
                    ErrorFuncionNoEstatica errorFuncionNoEstatica = new ErrorFuncionNoEstatica();
                    errorFuncionNoEstatica.setMensajeError("El tipo de retorno no coincide con el tipo de dato devuelto: " + funcion.getNombre()
                            + "Retorno esperado: " + funcion.getTipoRetorno() + " Retorno regresado: " + funcion.getRetornoAsignado());
                    errorFuncionNoEstatica.setFuncionNoEstatica(funcion);
                    erroresFuncionesNoEstaticas.add(errorFuncionNoEstatica);
                }
            } else {
                System.out.println("Tipo de retorno no asignado");
            }
        }

        return false;
    }

    //=================================================MANEJO DE LAS VARIABLES 
    // Método para agregar una nueva variable
    public static boolean agregarVariable(Variable nuevaVariable) {
        Estructura.verificarRangoVariables(nuevaVariable);
        if (verificarExistenciaVariable(nuevaVariable)) {
            ErrorVariable errorVariable = new ErrorVariable();
            errorVariable.setMensajeError("La variable '" + nuevaVariable.getNombreVariable()
                    + " ya está declarada");
            errorVariable.setVariableError(nuevaVariable);
            erroresVariables.add(errorVariable);
            return false;
        }
        variablesDeclaradas.add(nuevaVariable);
        return true;
    }

    // Método para verificar si una variable ya está declarada
    public static boolean verificarExistenciaVariable(Variable variable) {
        for (Variable existente : variablesDeclaradas) {
            // Verificar si el nombre y el contexto coinciden
            if (existente.getNombreVariable().equals(variable.getNombreVariable())) {
                if (existente.getRangoInicial() == 0 && existente.getRangoFinal() == 0) {
                    return true;
                }
                if (existente.getRangoInicial() == variable.getRangoInicial() && existente.getRangoFinal() == variable.getRangoFinal()) {
                    return true;
                }
            }
        }
        return false; // La variable no está declarada
    }

    public static void verificarRangoVariables(Variable variable) {
        boolean contextoEncontrado = false;

        // Verificar si pertenece a la función main (estática)
        if (funcionMain.getFilaInicial() <= variable.getFila()
                && funcionMain.getFilaFinal() >= variable.getFila()) {
            variable.setRangoInicial(funcionMain.getFilaInicial());
            variable.setRangoFinal(funcionMain.getFilaFinal());
            contextoEncontrado = true;
        }

        // Verificar si pertenece a una función estática (no estático)
        if (!contextoEncontrado) {
            for (FuncionEstatica funcionEstatica : funcionesEstaticas) {
                if (funcionEstatica.getFilaInicial() <= variable.getFila()
                        && funcionEstatica.getFilaFinal() >= variable.getFila()) {
                    variable.setRangoInicial(funcionEstatica.getFilaInicial());
                    variable.setRangoFinal(funcionEstatica.getFilaFinal());
                    contextoEncontrado = true;
                    break;
                }
            }
        }

        // Verificar si pertenece a una función no estática (no estático)
        if (!contextoEncontrado) {
            for (FuncionNoEstatica funcionNoEstatica : funcionesNoEstaticas) {
                if (funcionNoEstatica.getFilaInicial() <= variable.getFila()
                        && funcionNoEstatica.getFilaFinal() >= variable.getFila()) {
                    variable.setRangoInicial(funcionNoEstatica.getFilaInicial());
                    variable.setRangoFinal(funcionNoEstatica.getFilaFinal());
                    contextoEncontrado = true;
                    break;
                }
            }
        }

        // Si no pertenece a ningún contexto, marcar como indefinido
        if (!contextoEncontrado) {
            //es una variable global
        }
    }

    public static boolean agregarVariableEnUso(Variable variable) {
        verificarRangoVariables(variable);
        return variablesEnUso.add(variable);
    }

    public static void verificarVariablesUsadas() {

        for (Variable usada : variablesEnUso) {
            boolean declarada = false;
            for (Variable declaradaVar : variablesDeclaradas) {
                if (esMismaVariable(usada, declaradaVar)) {
                    declarada = true;
                    break;
                }
            }
            if (!declarada) {
                ErrorVariable errorVariable = new ErrorVariable();
                errorVariable.setMensajeError("La variable '" + usada.getNombreVariable()
                        + " no esta declarada");
                errorVariable.setVariableError(usada);
                erroresVariables.add(errorVariable);
            }
        }

    }

    public static void verificarAsignacionVariables() {

        for (Variable usada : variablesEnUso) {
            for (Variable declaradaVar : variablesDeclaradas) {
                if (esMismaVariable(usada, declaradaVar)) {
                    if (usada.getTipoDato().equals(declaradaVar.getTipoDato())) {
                        break;
                    } else if(esValidoRetorno(usada.getTipoDato())){
                        ErrorVariable errorVariable = new ErrorVariable();
                        errorVariable.setMensajeError("La variable '" + usada.getNombreVariable()
                                + "' no se le asigno un tipo de dato correcto");
                        errorVariable.setVariableError(usada);
                        erroresVariables.add(errorVariable);
                    }
                }
            }
        }

    }

    public static boolean esMismaVariable(Variable varUsada, Variable varDeclarada) {
        if (varUsada.getNombreVariable().equals(varDeclarada.getNombreVariable())
                && varUsada.getRangoInicial() == varDeclarada.getRangoInicial()
                && varUsada.getRangoFinal() == varDeclarada.getRangoFinal()) {
            return true;
        } else if (varUsada.getNombreVariable().equals(varDeclarada.getNombreVariable())
                && varDeclarada.getRangoInicial() == 0
                && varDeclarada.getRangoFinal() == 0) {
            return true;
        }
        return false;
    }

    // ==========================LLAMADA A FUNCIONES
    public static void agregarLlamadaFuncion(LlamadaFuncion llamadaFuncion) {
        if (llamadaFuncion != null) {
            verificarParametroIdentificador(llamadaFuncion);
            llamadasFunciones.add(llamadaFuncion);
            System.out.println("Llamada a función agregada: " + llamadaFuncion.getNombre());
        } else {
            System.out.println("Error: La llamada a función no puede ser nula.");
        }
    }

    public static void verificarParametroIdentificador(LlamadaFuncion llamadaFuncion) {
        for (Map.Entry<String, String> entry : llamadaFuncion.getParametros().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if ("IDENTIFICADOR".equals(value)) {
                System.out.println("La clave correspondiente al valor 'IDENTIFICADOR' es: " + key);
                variableDeclarada(llamadaFuncion, key, value);
            }
        }

    }

    public static void variableDeclarada(LlamadaFuncion llamadaFuncion, String key, String value) {
        boolean noEncontrada = true;
        for (Variable variable : variablesDeclaradas) {
            if (variable.getNombreVariable().equals(key)
                    && variable.getRangoInicial() <= llamadaFuncion.getFilaInicial()
                    && variable.getRangoFinal() >= llamadaFuncion.getFilaInicial()) {
                llamadaFuncion.getParametros().replace(key, value, variable.getTipoDato());
                noEncontrada = false;
                break;
            } 
        }
        if (noEncontrada) {
            ErrorLlamadaFuncion error = new ErrorLlamadaFuncion();
            error.setMensajeError("Llamada a función en fila " + llamadaFuncion.getFilaInicial() + " con parametro: " + key
                    + ", no esta declarada la variable");
            error.setLlamadaErronea(llamadaFuncion);
            erroresLlamadaFuncion.add(error);
        }
    }

    public static void verificarContextoLlamadas() {
        for (LlamadaFuncion llamada : llamadasFunciones) {
            boolean contextoEncontrado = false;

            // Verificar si pertenece a la función main (estática)
            if (funcionMain.getFilaInicial() <= llamada.getFilaInicial()
                    && funcionMain.getFilaFinal() >= llamada.getFilaInicial()) {
                llamada.setContexto("estatico");
                contextoEncontrado = true;
            }

            // Verificar si pertenece a una función estática (no estático)
            if (!contextoEncontrado) {
                for (FuncionEstatica funcionEstatica : funcionesEstaticas) {
                    if (funcionEstatica.getFilaInicial() <= llamada.getFilaInicial()
                            && funcionEstatica.getFilaFinal() >= llamada.getFilaInicial()) {
                        llamada.setContexto("estatico");
                        contextoEncontrado = true;
                        break;
                    }
                }
            }

            // Verificar si pertenece a una función no estática (no estático)
            if (!contextoEncontrado) {
                for (FuncionNoEstatica funcionNoEstatica : funcionesNoEstaticas) {
                    if (funcionNoEstatica.getFilaInicial() <= llamada.getFilaInicial()
                            && funcionNoEstatica.getFilaFinal() >= llamada.getFilaInicial()) {
                        llamada.setContexto("no estatico");
                        contextoEncontrado = true;
                        break;
                    }
                }
            }

            // Si no pertenece a ningún contexto, marcar como indefinido
            if (!contextoEncontrado) {
                llamada.setContexto("indefinido");
                ErrorLlamadaFuncion error = new ErrorLlamadaFuncion();
                error.setMensajeError("Llamada a función en fila " + llamada.getFilaInicial() + " no pertenece a ningún contexto definido.");
                error.setLlamadaErronea(llamada);
                erroresLlamadaFuncion.add(error);
            }
        }
    }

    public static void verificarLlamadasFunciones() {
        for (LlamadaFuncion llamada : llamadasFunciones) {
            boolean funcionEncontrada = false;

            // Verificar en las funciones estáticas
            for (FuncionEstatica funcionEstatica : funcionesEstaticas) {
                if (esLlamadaValida(llamada, funcionEstatica)) {
                    llamada.setContextoFuncionLlamada("estatico");
                    funcionEncontrada = true;
                    break;
                }
            }

            // Verificar en las funciones no estáticas
            if (!funcionEncontrada) {
                for (FuncionNoEstatica funcionNoEstatica : funcionesNoEstaticas) {
                    if (esLlamadaValida(llamada, funcionNoEstatica)) {
                        llamada.setContextoFuncionLlamada("no estatico");
                        funcionEncontrada = true;
                        break;
                    }
                }
            }

            // Si no se encuentra la función, marcar como indefinida
            if (!funcionEncontrada) {
                llamada.setContextoFuncionLlamada("indefinida");
                ErrorLlamadaFuncion error = new ErrorLlamadaFuncion();
                error.setMensajeError("Error: La llamada '" + llamada.getNombre()
                        + "' es erronea, por que no hay metodo con esa firma.");
                error.setLlamadaErronea(llamada);
                erroresLlamadaFuncion.add(error);
            }
        }
    }

    private static boolean esLlamadaValida(LlamadaFuncion llamada, Object funcion) {
        String nombreFuncion;
        Collection<String> tiposParametrosFuncion;

        if (funcion instanceof FuncionEstatica) {
            nombreFuncion = ((FuncionEstatica) funcion).getNombre();
            tiposParametrosFuncion = ((FuncionEstatica) funcion).getParametros().values();
        } else if (funcion instanceof FuncionNoEstatica) {
            nombreFuncion = ((FuncionNoEstatica) funcion).getNombre();
            tiposParametrosFuncion = ((FuncionNoEstatica) funcion).getParametros().values();
        } else {
            return false;
        }

        // Verificar si el nombre de la función coincide
        if (!llamada.getNombre().equals(nombreFuncion)) {
            return false;
        }

        // Verificar si la cantidad de parámetros coincide
        Collection<String> tiposParametrosLlamada = llamada.getParametros().values();
        if (tiposParametrosLlamada.size() != tiposParametrosFuncion.size()) {
            return false;
        }

        // Verificar si los tipos de parámetros coinciden (mismo orden)
        Iterator<String> itLlamada = tiposParametrosLlamada.iterator();
        Iterator<String> itFuncion = tiposParametrosFuncion.iterator();

        while (itLlamada.hasNext() && itFuncion.hasNext()) {
            if (!isCompatibleType(itLlamada.next(), itFuncion.next())) {
                return false; // Si los tipos no coinciden, la función no es válida
            }
        }

        return true; // Todos los tipos coinciden
    }

    // Método auxiliar para verificar compatibilidad de tipos
    private static boolean isCompatibleType(String expectedType, String assignedType) {
        System.out.println("assignedType = " + assignedType);
        System.out.println("expectedType = " + expectedType);
        // Ejemplo simple de compatibilidad
        switch (expectedType) {
            case "ENTERO":
            case "NUMERO_ENTERO":
                return assignedType.equals("NUMERO_ENTERO") || assignedType.equals("ENTERO");
            case "FLOTANTE":
            case "NUMERO_FLOTANTE":
                return assignedType.equals("NUMERO_FLOTANTE") || assignedType.equals("FLOTANTE");
            case "CHAR":
            case "CARACTER":
                return assignedType.equals("CARACTER") || assignedType.equals("CHAR");
            case "BOOL":
                return assignedType.equals("TRUE") || assignedType.equals("FALSE") || assignedType.equals("BOOL");
            case "CADENA":
            case "CADENA_TEXTO":
                return assignedType.equals("CADENA_TEXTO") || assignedType.equals("CADENA");
            default:
                return false;
        }
    }

    public static void verificarContextoDeLlamada() {
        for (LlamadaFuncion llamada : llamadasFunciones) {
            if (llamada.getContexto().equals("estatico")) {
                if (!llamada.getContexto().equals(llamada.getContextoFuncionLlamada()) && !llamada.getContextoFuncionLlamada().equals("indefinida")) {
                    ErrorLlamadaFuncion error = new ErrorLlamadaFuncion();
                    error.setMensajeError("Error: La llamada '" + llamada.getNombre()
                            + "' es un metodo No estatico y actualmente estas en un contexto estatico");
                    error.setLlamadaErronea(llamada);
                    erroresLlamadaFuncion.add(error);
                } else if (llamada.getContextoFuncionLlamada().equals("indefinida")) {
                    ErrorLlamadaFuncion error = new ErrorLlamadaFuncion();
                    error.setMensajeError("Error: La llamada '" + llamada.getNombre()
                            + "' esta en un contexto estatico, pero la funcion no existe");
                    error.setLlamadaErronea(llamada);
                    erroresLlamadaFuncion.add(error);
                }
            } else if (llamada.getContexto().equals("no estatico")) {
                if (!llamada.getContexto().equals(llamada.getContextoFuncionLlamada()) && !llamada.getContextoFuncionLlamada().equals("indefinida")) {
                    ErrorLlamadaFuncion error = new ErrorLlamadaFuncion();
                    error.setMensajeError("Error: La llamada '" + llamada.getNombre()
                            + "' es un metodo Estatico y actualmente estas en un contexto NO estatico");
                    error.setLlamadaErronea(llamada);
                    erroresLlamadaFuncion.add(error);
                } else if (llamada.getContextoFuncionLlamada().equals("indefinida")) {
                    ErrorLlamadaFuncion error = new ErrorLlamadaFuncion();
                    error.setMensajeError("Error: La llamada '" + llamada.getNombre()
                            + "' esta en un contexto no estatico, pero la funcion no existe");
                    error.setLlamadaErronea(llamada);
                    erroresLlamadaFuncion.add(error);
                }
            } else {
                ErrorLlamadaFuncion error = new ErrorLlamadaFuncion();
                error.setMensajeError("Error: La llamada '" + llamada.getNombre()
                        + "' No tiene un contexto Definido");
                error.setLlamadaErronea(llamada);
                erroresLlamadaFuncion.add(error);
            }
        }
    }

    public static void limpiarListas() {
        funcionMain = new FuncionMain();
        funcionesEstaticas.clear();
        funcionesNoEstaticas.clear();
        llamadasFunciones.clear();

        variablesDeclaradas.clear();
        variablesEnUso.clear();

        erroresVariables.clear();
        erroresFuncionesEstaticas.clear();
        erroresFuncionesNoEstaticas.clear();
        erroresLlamadaFuncion.clear();
        System.out.println("Todas las listas han sido limpiadas.");
    }

    public static FuncionMain getFuncionMain() {
        return funcionMain;
    }

    public static void setFuncionMain(FuncionMain funcionMain) {
        Estructura.funcionMain = funcionMain;
    }

    public static List<FuncionEstatica> getFuncionesEstaticas() {
        return funcionesEstaticas;
    }

    public static List<FuncionNoEstatica> getFuncionesNoEstaticas() {
        return funcionesNoEstaticas;
    }

    public static List<Variable> getVariablesDeclaradas() {
        return variablesDeclaradas;
    }

    public static List<Variable> getVariablesEnUso() {
        return variablesEnUso;
    }

    public static List<ErrorVariable> getErroresVariables() {
        return erroresVariables;
    }

    public static List<ErrorFuncionEstatica> getErroresFuncionesEstaticas() {
        return erroresFuncionesEstaticas;
    }

    public static List<ErrorFuncionNoEstatica> getErroresFuncionesNoEstaticas() {
        return erroresFuncionesNoEstaticas;
    }

    public static List<LlamadaFuncion> getLlamadasFunciones() {
        return llamadasFunciones;
    }

    public static List<ErrorLlamadaFuncion> getErroresLlamadaFuncion() {
        return erroresLlamadaFuncion;
    }

    public static List<String> obtenerMensajesDeErrores() {
        List<String> mensajesErrores = new ArrayList<>();

        // Recorrer errores de variables
        for (ErrorVariable error : erroresVariables) {
            mensajesErrores.add(error.toString());
            mensajesErrores.add("\n");
        }

        // Recorrer errores de funciones estáticas
        for (ErrorFuncionEstatica error : erroresFuncionesEstaticas) {
            mensajesErrores.add(error.toString());
            mensajesErrores.add("\n");
        }

        // Recorrer errores de funciones no estáticas
        for (ErrorFuncionNoEstatica error : erroresFuncionesNoEstaticas) {
            mensajesErrores.add(error.toString());
            mensajesErrores.add("\n");
        }

        // Recorrer errores de llamadas a funciones
        for (ErrorLlamadaFuncion error : erroresLlamadaFuncion) {
            mensajesErrores.add(error.toString());
            mensajesErrores.add("\n");
        }

        return mensajesErrores;
    }

}
