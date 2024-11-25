
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import estructura.Estructura;
import estructura.FuncionEstatica;
import estructura.FuncionMain;
import estructura.FuncionNoEstatica;
import estructura.LlamadaFuncion;
import estructura.Variable;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author yisus
 */
public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;
    private HashMap<String, Boolean> funciones; // Nombre de la función y si es estática
    private List<String> mensajesErroneos;

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
    }

    private void init() {
        title = "Compilador";
        setLocationRelativeTo(null); //centrar
        setTitle(title);
        directorio = new Directory(this, jtpCode, title, ".comp");
        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        Functions.setLineNumberOnJTextComponent(jtpCode);
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();
            colorAnalysis();
        });
        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        mensajesErroneos = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        Functions.setAutocompleterJTextComponent(new String[]{"System", "out", "println", "Math", "String", "Integer", "protected", "public", "return", "static"}, jtpCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarC.setText("Guardar como");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnGuardarC))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jtpCode);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjecutar)
                .addContainerGap())
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompilar)
                    .addComponent(btnEjecutar))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(255, 255, 255));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                        .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New();
        clearFields();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) {
            colorAnalysis();
            clearFields();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        btnCompilar.doClick();
        if (codeHasBeenCompiled) {
            if (!errors.isEmpty() && !mensajesErroneos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE);
            } else {
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                System.out.println(codeBlock);
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                System.out.println(blocksOfCode);

            }
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void compile() {
        //Grammar gramatica = new Grammar(tokens, errors);
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        semanticAnalysis();
        printConsole();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() {
        // Extraer tokens
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void syntacticAnalysis() {
        Grammar gramatica = new Grammar(tokens, errors);

        /*Eliminacion de errores*/
        gramatica.delete(new String[]{"ERROR", "ERROR1", "ERROR 2"}, 1);

        //Operaciones Anidados Relacional
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            // Grupo para expresiones relacionales
            gramatica.group("EXP_RELACIONAL", " (NUMERO_ENTERO| NUMERO_FLOTANTE | IDENTIFICADOR) OPERADOR_RELACIONAL (NUMERO_ENTERO| NUMERO_FLOTANTE | IDENTIFICADOR | TRUE | FALSE)");
            gramatica.group("EXP_RELACIONAL", "(PARENTESIS_A EXP_RELACIONAL PARENTESIS_C) | EXP_RELACIONAL ");
            gramatica.group("EXP_RELACIONAL", "(PARENTESIS_A)? EXP_RELACIONAL (OPERADOR_RELACIONAL)? (EXP_RELACIONAL)+ (PARENTESIS_C)? ");
        });

        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("EXP_ARITMETICA", "(IDENTIFICADOR | NUMERO_ENTERO | NUMERO_FLOTANTE | EXP_ARITMETICA) "
                    + "(SUMA | RESTA | MULTIPLICACION | DIVISION | MODULO) "
                    + "(IDENTIFICADOR | NUMERO_ENTERO | NUMERO_FLOTANTE | EXP_ARITMETICA)  ");
            gramatica.group("EXP_ARITMETICA", "(PARENTESIS_A EXP_ARITMETICA PARENTESIS_C)  | EXP_ARITMETICA");
            gramatica.group("EXP_ARITMETICA", "(PARENTESIS_A)? EXP_ARITMETICA (SUMA | RESTA | MULTIPLICACION | DIVISION | MODULO) (EXP_ARITMETICA)+  (PARENTESIS_C)? ");
//            gramatica.group("EXP_ARITMETICA", " (EXP_ARITMETICA (SUMA | RESTA | MULTIPLICACION | DIVISION | MODULO) EXP_ARITMETICA  ");
            gramatica.group("ASIG_ENTERO", "ASIGNACION_IGUAL  EXP_ARIMETICA");
        });

        //Operaciones Anidados Logicas
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("EXP_LOGICA", " IDENTIFICADOR OPERADOR_LOGICO IDENTIFICADOR");
            gramatica.group("EXP_LOGICA", "(EXP_LOGICA | EXP_RELACIONAL) OPERADOR_LOGICO (EXP_LOGICA | EXP_RELACIONAL)");
            gramatica.group("EXP_LOGICA", "(PARENTESIS_A EXP_LOGICA PARENTESIS_C) | EXP_LOGICA");
            gramatica.group("EXP_LOGICA", "(PARENTESIS_A)? EXP_LOGICA (OPERADOR_LOGICO) (EXP_LOGICA)+ (PARENTESIS_C)? ");
        });

        gramatica.group("DEC_ENTERO", "DATO_ENTERO IDENTIFICADOR", true, identProd);
        gramatica.group("DEC_FLOTANTE", "DATO_FLOTANTE IDENTIFICADOR", true, identProd);
        gramatica.group("DEC_BOOL", "DATO_BOOL IDENTIFICADOR", true, identProd);
        gramatica.group("DEC_CADENA", "DATO_CADENA IDENTIFICADOR", true, identProd);
        gramatica.group("DEC_CHAR", "DATO_CARACTER IDENTIFICADOR", true, identProd);

        gramatica.group("FUNCION", "(DEC_ENTERO | DEC_FLOTANTE | DEC_CADENA | DEC_BOOL | DEC_CHAR ) PARENTESIS_A", true, identProd);

        gramatica.group("ASIG_ENTERO", "ASIGNACION_IGUAL (NUMERO_ENTERO | IDENTIFICADOR | EXP_ARITMETICA )", true, identProd);
        gramatica.group("VAR_ENTERO", "(IDENTIFICADOR ASIG_ENTERO| DEC_ENTERO ASIG_ENTERO | DEC_ENTERO ) PUNTO_COMA ", true, identProd);

        gramatica.group("ASIG_FLOTANTE", "ASIGNACION_IGUAL (NUMERO_FLOTANTE | IDENTIFICADOR | EXP_ARITMETICA)", identProd);
        gramatica.group("VAR_FLOTANTE", "(IDENTIFICADOR ASIG_FLOTANTE| DEC_FLOTANTE| DEC_FLOTANTE ASIG_FLOTANTE ) PUNTO_COMA ", true, identProd);

        gramatica.group("ASIG_BOOL", "ASIGNACION_IGUAL (TRUE | FALSE | EXP_RELACIONAL | EXP_LOGICA)", identProd);
        gramatica.group("VAR_BOOL", "(IDENTIFICADOR ASIG_BOOL| DEC_BOOL| DEC_BOOL ASIG_BOOL ) PUNTO_COMA ", true, identProd);

        gramatica.group("ASIG_CADENA", "ASIGNACION_IGUAL CADENA_TEXTO", identProd);
        gramatica.group("VAR_CADENA", "(IDENTIFICADOR ASIG_CADENA| DEC_CADENA| DEC_CADENA ASIG_CADENA ) PUNTO_COMA ", true, identProd);

        gramatica.group("ASIG_CHAR", "ASIGNACION_IGUAL CARACTER", identProd);
        gramatica.group("VAR_CHAR", "(IDENTIFICADOR ASIG_CHAR| DEC_CHAR| DEC_CHAR ASIG_CHAR ) PUNTO_COMA ", true, identProd);

        /// ====================== ERRORES SEMANTICOS
        gramatica.group("ERR_VAR_ENTERO", "(DEC_ENTERO (ASIG_FLOTANTE | ASIG_CADENA |ASIG_BOOL | ASIG_CHAR)) (PUNTO_COMA)? ", true,
                30, "Error Semantico {}: asignacion invalida a un tipo entero [# , %]");

        gramatica.group("ERR_VAR_FLOTANTE", "(DEC_FLOTANTE (ASIG_CHAR | ASIG_CADENA |ASIG_BOOL)) (PUNTO_COMA)? ", true,
                31, "Error Semantico {}: asignacion invalida a un tipo flotante [# , %]");

        gramatica.group("ERR_VAR_BOOL", "(DEC_BOOL (ASIG_ENTERO | ASIG_CADENA |ASIG_FLOTANTE | ASIG_CHAR)) (PUNTO_COMA)? ", true,
                32, "Error Semantico {}: asignacion invalida a un tipo booleano [# , %]");

        gramatica.group("ERR_VAR_CHAR", "(DEC_CHAR (ASIG_ENTERO | ASIG_CADENA |ASIG_FLOTANTE | ASIG_BOOL)) (PUNTO_COMA)? ", true,
                33, "Error Semantico {}: asignacion invalida a un tipo character [# , %]");

        gramatica.group("ERR_VAR_CADENA", "(DEC_CADENA (ASIG_ENTERO | ASIG_CHAR |ASIG_FLOTANTE | ASIG_BOOL)) (PUNTO_COMA)? ", true,
                34, "Error Semantico {}: asignacion invalida a un tipo cadena [# , %]");

        gramatica.group("POS", "(IDENTIFICADOR DECREMENTAL) | (IDENTIFICADOR INCREMENTAL)");
        gramatica.group("PRE", "(INCREMENTAL IDENTIFICADOR) | (DECREMENTAL IDENTIFICADOR)");

        gramatica.group("CALL_FUNC", "IDENTIFICADOR PARENTESIS_A ( (IDENTIFICADOR||NUMERO_ENTERO||NUMERO_FLOTANTE||CARACTER||TRUE||FALSE||CADENA_TEXTO)"
                + " (COMA (IDENTIFICADOR||NUMERO_ENTERO||NUMERO_FLOTANTE||CARACTER||TRUE||FALSE||CADENA_TEXTO))*)? PARENTESIS_C PUNTO_COMA", true, identProd);

        //=========CONCATENAR
        gramatica.group("EXP_CONCATENACION", " (CADENA_TEXTO | IDENTIFICADOR |NUMERO_ENTERO|NUMERO_FLOTANTE| EXP_CONCATENACION) SUMA (CADENA_TEXTO |NUMERO_ENTERO|NUMERO_FLOTANTE| IDENTIFICADOR | EXP_CONCATENACION)+ ", true, identProd
        );

        // Definir System.out.println
        gramatica.group("CONSOLA", "SYSTEM PUNTO OUT PUNTO PRINTLN PARENTESIS_A ( EXP_ARIMETICA | CADENA_TEXTO | IDENTIFICADOR |EXP_CONCATENACION) PARENTESIS_C PUNTO_COMA");
        gramatica.group("CONSOLA", "SYSTEM PUNTO OUT PUNTO PRINTLN PARENTESIS_A ( EXP_ARIMETICA | CADENA_TEXTO | IDENTIFICADOR) PARENTESIS_C ", true,
                2, "Error sintáctico: falta punto y coma [# , %]");

        gramatica.group("OPERADOR_TERMINARIO", "");

        gramatica.group("BLOQUE", "LLAVE_A  LLAVE_C");
        gramatica.group("BLOQUE", "LLAVE_A (CONSOLA | VAR_BOOL | VAR_CADENA | VAR_CHAR | VAR_FLOTANTE | VAR_ENTERO| CALL_FUNC )* LLAVE_C");

        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("CONDICIONAL_IF", "IF (PARENTESIS_A)? (EXP_LOGICA| EXP_RELACIONAL | IDENTIFICADOR) (PARENTESIS_C)? BLOQUE");
            gramatica.group("ELSE_IF", "ELSE CONDICIONAL_IF");
            gramatica.group("ELSE", "ELSE BLOQUE");
            gramatica.group("CONDICIONAL_IF", "CONDICIONAL_IF (ELSE_IF)* (ELSE)? ");

            //errores del if
            gramatica.group("CONDICIONAL_IF", "IF (PARENTESIS_A)? (EXP_LOGICA| EXP_RELACIONAL | IDENTIFICADOR) (PARENTESIS_C)?  ", true,
                    3, "Error sintáctico {}: falta bloque en la declaración if [# , %]");
            gramatica.group("CONDICIONAL_IF", "IF (PARENTESIS_A)? (EXP_ARIMETICA) (PARENTESIS_C)? BLOQUE", true,
                    20, "Error sintactico {}: Bloque if no declarado correctamente");
            gramatica.group("CONDICIONAL_IF", "IF (PARENTESIS_A)?  (PARENTESIS_C)? BLOQUE", true,
                    21, "Error sintactico {}: Bloque if no declarado correctamente");

        });


        /* Estructura WHILE */
        gramatica.group("BUCLE_WHILE", "WHILE (PARENTESIS_A)? (EXP_LOGICA | EXP_RELACIONAL | IDENTIFICADOR) (PARENTESIS_C)? BLOQUE");

        gramatica.group("BUCLE_WHILE", "WHILE   BLOQUE", true,
                4, "Error sintáctico: falta declaración de while [# , %]");

        //Bucle For
        gramatica.group("BUCLE_FOR", "FOR PARENTESIS_A  VAR_ENTERO "
                + " (EXP_LOGICA | EXP_RELACIONAL) PUNTO_COMA (POS | PRE) PARENTESIS_C BLOQUE ");
        gramatica.group("STRING_ARR", "DATO_CADENA CORCHETE_A CORCHETE_C");

        //BLOQUES DE CODIGO
        gramatica.group("BLOQUE", "LLAVE_A (CONSOLA | VAR_BOOL | VAR_CADENA | VAR_CHAR | VAR_FLOTANTE | VAR_ENTERO| CALL_FUNC |"
                + " CONDICIONAL_IF | BUCLE_WHILE ] BUCLE_FOR )* LLAVE_C");
        gramatica.group("BLOQUE_RETORNO", "LLAVE_A (CONSOLA | VAR_BOOL | VAR_CADENA | VAR_CHAR | VAR_FLOTANTE | VAR_ENTERO| CALL_FUNC |"
                + " CONDICIONAL_IF | BUCLE_WHILE ] BUCLE_FOR  )* "
                + " RETURN (NUMERO_ENTERO| CADENA_TEXTO | NUMERO_FLOTANTE | TRUE | FALSE | IDENTIFICADOR |"
                + " EXP_RELACIONAL | EXP_ARIMETICA | EXP_LOGICA ) PUNTO_COMA LLAVE_C");

        //funcion main
        gramatica.group("FUNCION_MAIN", "PUBLIC STATIC VOID MAIN "
                + " PARENTESIS_A STRING_ARR IDENTIFICADOR PARENTESIS_C BLOQUE ", true, identProd);

        //funcion normal
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("PARAMETROS", "PARENTESIS_A (DEC_ENTERO | DEC_FLOTANTE | DEC_CADENA | DEC_BOOL | DEC_CHAR ) "
                    + "( (COMA) (DEC_ENTERO | DEC_FLOTANTE | DEC_CADENA | DEC_BOOL | DEC_CHAR))* PARENTESIS_C", true, identProd);

            gramatica.group("PARAMETROS", "(DEC_ENTERO | DEC_FLOTANTE | DEC_CADENA | DEC_BOOL | DEC_CHAR ) "
                    + "( (COMA) (DEC_ENTERO | DEC_FLOTANTE | DEC_CADENA | DEC_BOOL | DEC_CHAR))* PARENTESIS_C", true, identProd);

        });
        //=================FUNCIONES NO ESTATICAS 
        gramatica.group("FUNCION_NOPARAMS", "PUBLIC VOID IDENTIFICADOR  "
                + " PARENTESIS_A PARENTESIS_C BLOQUE ", true, identProd);

        gramatica.group("FUNCION_NOPARAMS_RETORNO", "PUBLIC FUNCION  "
                + " PARENTESIS_C BLOQUE_RETORNO ", true, identProd);

        gramatica.group("FUNCION_PARAMS", "PUBLIC VOID IDENTIFICADOR  "
                + " PARAMETROS BLOQUE ", true, identProd);

        gramatica.group("FUNCION_PARAMS_RETORNO", "PUBLIC FUNCION  "
                + " PARAMETROS BLOQUE_RETORNO ", true, identProd);

        //================== FUNCIONES ESTATICAS
        gramatica.group("FUNCION_EST_NOPARAMS", "PUBLIC STATIC VOID IDENTIFICADOR  "
                + " PARENTESIS_A PARENTESIS_C BLOQUE ", true, identProd);

        gramatica.group("FUNCION_EST_NOPARAMS_RETORNO", "PUBLIC STATIC FUNCION  "
                + " PARENTESIS_C BLOQUE_RETORNO ", true, identProd);

        gramatica.group("FUNCION_EST_PARAMS", "PUBLIC STATIC VOID IDENTIFICADOR  "
                + " PARAMETROS BLOQUE ", true, identProd);

        gramatica.group("FUNCION_EST_PARAMS_RETORNO", "PUBLIC STATIC FUNCION  "
                + " PARAMETROS BLOQUE_RETORNO ", true, identProd);

        //constructor
        gramatica.group("FUNCION_CONST_PARAMS", " PUBLIC IDENTIFICADOR PARAMETROS BLOQUE ", true, identProd);
        gramatica.group("FUNCION_CONST_NOPARAMS", "PUBLIC IDENTIFICADOR PARENTESIS_A PARENTESIS_C BLOQUE ", true, identProd);

        //clases
        gramatica.group("BLOQUE_CLASE", " LLAVE_A ( FUNCION_NOPARAMS | FUNCION_NOPARAMS_RETORNO | FUNCION_PARAMS | "
                + "FUNCION_PARAMS_RETORNO | FUNCION_EST_NOPARAMS | FUNCION_EST_NOPARAMS_RETORNO |FUNCION_EST_PARAMS | "
                + " FUNCION_EST_PARAMS_RETORNO | FUNCION_MAIN )+ LLAVE_C ", true, identProd);
        gramatica.group("CLASE", " PUBLIC CLASS IDENTIFICADOR BLOQUE_CLASE", true, identProd);

        //llamada a funciones
//        gramatica.group("CALL_FUNC", "IDENTIFICADOR PARENTESIS_A ( (IDENTIFICADOR||NUMERO_ENTERO||NUMERO_FLOTANTE||CARACTER||TRUE||FALSE||CADENA_TEXTO)"
//                + " (COMA (IDENTIFICADOR||NUMERO_ENTERO||NUMERO_FLOTANTE||CARACTER||TRUE||FALSE||CADENA_TEXTO))*)? PARENTESIS_C PUNTO_COMA", true, identProd);
        /// ====================== ERRORES SINTACTICOS 
        gramatica.group("ERR_EXP_RELACIONAL", "PARENTESIS_A EXP_RELACIONAL  ", true,
                6, "Error sintáctico {}: falta cierre de paréntesis en expresión relacional [# , %]");
        gramatica.delete("ERR_EXP_RELACIONAL", 9, " Error {}: Expresión relacional no esta declarada correctamente [# , %]");

        gramatica.group("VAR_ENTERO", " (IDENTIFICADOR ASIG_ENTERO)  | (DEC_ENTERO ASIG_ENTERO)", true,
                8, "Error {}: Falta punto y coma en declaracion  [# , %]");
        //gramatica.group("ERR_VAR_ENTERO", "(ASIG_ENTERO PUNTO_COMA)| ASIG_ENTERO ", true, 9, "Error {}: Falta decarar  [# , %]");
        gramatica.group("VAR_ENTERO", "(IDENTIFICADOR ) PUNTO_COMA ", true,
                7, "Error {}: Falta asignación o tipo de dato [# , %]");
//        gramatica.delete("VAR_ENTERO", 10, " × Error sintáctico {}: La variable entera no está declarada correctamente [#, %]");

        gramatica.group("VAR_FLOTANTE", " IDENTIFICADOR ASIG_FLOTANTE  | DEC_FLOTANTE ASIG_FLOTANTE ", true,
                11, "Error {}: Falta punto y coma en declaracion  [# , %]");
        //gramatica.group("ERR_VAR_FLOTANTE", "(ASIG_FLOTANTE PUNTO_COMA)| ASIG_FLOTANTE ", true,12, "Error {}: Falta decarar  [# , %]");
        gramatica.group("VAR_FLOTANTE", "(IDENTIFICADOR ) PUNTO_COMA ", true,
                13, "Error {}: Falta asignación o tipo de dato [# , %]");
//        gramatica.delete("VAR_FLOTANTE", 14, " × Error sintáctico {}: La variable flotante no está declarada correctamente [#, %]");

        gramatica.group("VAR_BOOL", " IDENTIFICADOR ASIG_BOOL | DEC_BOOL ASIG_BOOL ", true,
                15, "Error {}: Falta punto y coma en declaracion  [# , %]");
        //gramatica.group("ERR_VAR_BOOL", "(ASIG_BOOL PUNTO_COMA)| ASIG_BOOL ", true,16, "Error {}: Falta decarar  [# , %]");
        gramatica.group("VAR_BOOL", "(IDENTIFICADOR ) PUNTO_COMA ", true,
                17, "Error {}: Falta asignación o tipo de dato [# , %]");
//        gramatica.delete("VAR_BOOL", 18, " × Error sintáctico {}: La variable booleana no está declarada correctamente [#, %]");

        gramatica.group("VAR_CADENA", " IDENTIFICADOR ASIG_CADENA  | DEC_CADENA ASIG_CADENA ", true,
                19, "Error {}: Falta punto y coma en declaracion  [# , %]");
        //gramatica.group("ERR_VAR_CADENA", "(ASIG_CADENA PUNTO_COMA)| ASIG_CADENA ", true,20, "Error {}: Falta decarar  [# , %]");
        gramatica.group("VAR_CADENA", "(IDENTIFICADOR ) CADENA ", true,
                21, "Error {}: Falta asignación o tipo de dato [# , %]");
//        gramatica.delete("VAR_CADENA", 22, " × Error sintáctico {}: La variable cadena no está declarada correctamente [#, %]");

        gramatica.group("VAR_CHAR", " IDENTIFICADOR ASIG_CHAR  | DEC_CHAR ASIG_CHAR ", true,
                23, "Error {}: Falta punto y coma en declaracion  [# , %]");
        //        gramatica.group("ERR_VAR_CHAR", "(ASIG_CHAR PUNTO_COMA)| ASIG_CHAR ", true,24, "Error {}: Falta decarar  [# , %]");
        gramatica.group("VAR_CHAR", "(IDENTIFICADOR ) CHAR ", true,
                25, "Error {}: Falta asignación o tipo de dato [# , %]");
//        gramatica.delete("VAR_CHAR", 26, " × Error sintáctico {}: La variable carácter no está declarada correctamente [#, %]");

        System.out.println("Console.log('imprimiendo producciones'");
        gramatica.show();

    }

    public void retornarExpresion(Production production) {
        production.lexemeRank(production.getSizeTokens() - 3);
        for (int i = 0; i < production.getSizeTokens(); i++) {

        }
    }

    private void semanticAnalysis() {

        System.out.println("\n\n\n");
        for (Production production : identProd) {
            String productionName = production.getName();
            System.out.println("productionName = " + productionName);
//            for (int i = 0; i < production.getSizeTokens(); i++) {
//                System.out.println("Lexema " + i + ": " + production.lexemeRank(i));
//            }
        }
        System.out.println("\n\n\n");
        Estructura.limpiarListas();

        for (Production production : identProd) {
            FuncionEstatica funcionEstatica;
            FuncionNoEstatica funcionNoEstatica;
            FuncionMain funcionMain;
            String productionName = production.getName();

            // Funcion Estatica Con parametros sin retorno
            if (productionName.equals("FUNCION_EST_PARAMS")) {
                funcionEstatica = new FuncionEstatica();
                funcionEstatica.setNombre(production.lexemeRank(3));
                funcionEstatica.setTipoRetorno(production.lexicalCompRank(2)); //void 
                funcionEstatica.setRetorno(false); //void 
                funcionEstatica.setFilaInicial(production.getLine());
                funcionEstatica.setFilaFinal(production.getFinalLine());
                funcionEstatica.setColumna(production.getColumn());

                // Extraer y validar los parámetros (tipo y nombre)
                for (int i = 5; i < production.getSizeTokens() - 2; i += 2) {
                    if (production.lexicalCompRank(i).equals("PARENTESIS_C")) {
                        break;
                    }
                    if (production.lexicalCompRank(i).equals("COMA")) {
                        i--;
                        continue;
                    }
                    String paramType = production.lexicalCompRank(i);  // Tipo de dato
                    paramType = paramType.substring(5);
                    funcionEstatica.agregarParametro(production.lexemeRank(i + 1), paramType);
                }
                Estructura.agregarFuncionEstatica(funcionEstatica);

            } // Funcion Estatica Con parametros con retorno
            else if (productionName.equals("FUNCION_EST_PARAMS_RETORNO")) {
                System.out.println("================retorno: " + production.lexicalCompRank(production.getSizeTokens() - 3));
                funcionEstatica = new FuncionEstatica();
                funcionEstatica.setNombre(production.lexemeRank(3));
                funcionEstatica.setTipoRetorno(production.lexicalCompRank(2).substring(5)); //int boolean etc....
                funcionEstatica.setRetorno(true); //void 
                funcionEstatica.setRetornoAsignado(regresarTipoDato(production.lexicalCompRank(production.getSizeTokens() - 3)));
                funcionEstatica.setFilaInicial(production.getLine());
                funcionEstatica.setFilaFinal(production.getFinalLine());
                funcionEstatica.setColumna(production.getColumn());

                // Extraer y validar los parámetros (tipo y nombre)
                for (int i = 5; i < production.getSizeTokens() - 2; i += 2) {
                    if (production.lexicalCompRank(i).equals("PARENTESIS_C")) {
                        break;
                    }
                    if (production.lexicalCompRank(i).equals("COMA")) {
                        i--;
                        continue;
                    }
                    String paramType = production.lexicalCompRank(i);  // Tipo de dato
                    paramType = paramType.substring(5);
                    funcionEstatica.agregarParametro(production.lexemeRank(i + 1), paramType);
                }
                if (funcionEstatica.getRetornoAsignado().equals("IDENTIFICADOR")) {
                    System.out.println("producion identificador => " + production.lexemeRank(production.getSizeTokens() - 3));
                    funcionEstatica.setNombreIdentificadorRetorno(production.lexemeRank(production.getSizeTokens() - 3));
                }
//                System.out.println("name: " + production.getName());
//                    System.out.println("name: " + production.getTokens());
//                    System.out.println("name: " + production.toString());
//                    System.out.println("name: " + production.tokenRank(0));
                Estructura.agregarFuncionEstatica(funcionEstatica);

            }

            // Funcion Estatica Sin parametros sin retorno
            if (productionName.equals("FUNCION_EST_NOPARAMS")) {
                funcionEstatica = new FuncionEstatica();
                funcionEstatica.setNombre(production.lexemeRank(3));
                funcionEstatica.setTipoRetorno(production.lexicalCompRank(2)); //void 
                funcionEstatica.setRetorno(false); //void 
                funcionEstatica.setFilaInicial(production.getLine());
                funcionEstatica.setFilaFinal(production.getFinalLine());
                funcionEstatica.setColumna(production.getColumn());
                Estructura.agregarFuncionEstatica(funcionEstatica);

            } // Funcion Estatica Sin parametros con retorno
            else if (productionName.equals("FUNCION_EST_NOPARAMS_RETORNO")) {
                System.out.println("================retorno: " + production.lexicalCompRank(production.getSizeTokens() - 3));
                funcionEstatica = new FuncionEstatica();
                funcionEstatica.setNombre(production.lexemeRank(3));
                funcionEstatica.setTipoRetorno(production.lexicalCompRank(2).substring(5)); //int boolean etc....
                funcionEstatica.setRetorno(true); //void 
                funcionEstatica.setRetornoAsignado(regresarTipoDato(production.lexicalCompRank(production.getSizeTokens() - 3)));
                funcionEstatica.setFilaInicial(production.getLine());
                funcionEstatica.setFilaFinal(production.getFinalLine());
                funcionEstatica.setColumna(production.getColumn());
                if ((production.lexicalCompRank(production.getSizeTokens() - 3)).equals("IDENTIFICADOR")) {
                    funcionEstatica.setNombreIdentificadorRetorno(production.lexemeRank(production.getSizeTokens() - 3));
                }
                Estructura.agregarFuncionEstatica(funcionEstatica);

            }

            // Funcion Con parametros sin retorno
            if (productionName.equals("FUNCION_PARAMS")) {
                funcionNoEstatica = new FuncionNoEstatica();
                funcionNoEstatica.setNombre(production.lexemeRank(2));
                funcionNoEstatica.setTipoRetorno(production.lexicalCompRank(1)); //void 
                funcionNoEstatica.setRetorno(false); //void 
                funcionNoEstatica.setFilaInicial(production.getLine());
                funcionNoEstatica.setFilaFinal(production.getFinalLine());
                funcionNoEstatica.setColumna(production.getColumn());

                // Extraer y validar los parámetros (tipo y nombre)
                for (int i = 4; i < production.getSizeTokens() - 2; i += 2) {
                    if (production.lexicalCompRank(i).equals("PARENTESIS_C")) {
                        break;
                    }
                    if (production.lexicalCompRank(i).equals("COMA")) {
                        i--;
                        continue;
                    }
                    String paramType = production.lexicalCompRank(i);  // Tipo de dato
                    paramType = paramType.substring(5);
                    funcionNoEstatica.agregarParametro(production.lexemeRank(i + 1), paramType);
                }
                Estructura.agregarFuncionNoEstatica(funcionNoEstatica);

            } // Funcion Con parametros con retorno
            else if (productionName.equals("FUNCION_PARAMS_RETORNO")) {
                System.out.println("================retorno: " + production.lexicalCompRank(production.getSizeTokens() - 3));
                funcionNoEstatica = new FuncionNoEstatica();
                funcionNoEstatica.setNombre(production.lexemeRank(2));
                funcionNoEstatica.setTipoRetorno(production.lexicalCompRank(1).substring(5)); //int boolean etc....
                funcionNoEstatica.setRetorno(true); //void 
                funcionNoEstatica.setRetornoAsignado(regresarTipoDato(production.lexicalCompRank(production.getSizeTokens() - 3)));
                funcionNoEstatica.setFilaInicial(production.getLine());
                funcionNoEstatica.setFilaFinal(production.getFinalLine());
                funcionNoEstatica.setColumna(production.getColumn());

                // Extraer y validar los parámetros (tipo y nombre)
                for (int i = 4; i < production.getSizeTokens() - 2; i += 2) {
                    if (production.lexicalCompRank(i).equals("PARENTESIS_C")) {
                        break;
                    }
                    if (production.lexicalCompRank(i).equals("COMA")) {
                        i--;
                        continue;
                    }
                    String paramType = production.lexicalCompRank(i);  // Tipo de dato
                    paramType = paramType.substring(5);
                    funcionNoEstatica.agregarParametro(production.lexemeRank(i + 1), paramType);
                }
                if (funcionNoEstatica.getRetornoAsignado().equals("IDENTIFICADOR")) {
                    System.out.println("producion identificador => " + production.lexemeRank(production.getSizeTokens() - 3));
                    funcionNoEstatica.setNombreIdentificadorRetorno(production.lexemeRank(production.getSizeTokens() - 3));
                }
                Estructura.agregarFuncionNoEstatica(funcionNoEstatica);

            }

            // Funcion Sin parametros sin retorno
            if (productionName.equals("FUNCION_NOPARAMS")) {
                funcionNoEstatica = new FuncionNoEstatica();
                funcionNoEstatica.setNombre(production.lexemeRank(2));
                funcionNoEstatica.setTipoRetorno(production.lexicalCompRank(1)); //void 
                funcionNoEstatica.setRetorno(false); //void 
                funcionNoEstatica.setFilaInicial(production.getLine());
                funcionNoEstatica.setFilaFinal(production.getFinalLine());
                funcionNoEstatica.setColumna(production.getColumn());
                Estructura.agregarFuncionNoEstatica(funcionNoEstatica);

            } // Funcion Sin parametros con retorno
            else if (productionName.equals("FUNCION_NOPARAMS_RETORNO")) {
                System.out.println("================retorno: " + production.lexicalCompRank(production.getSizeTokens() - 3));
                funcionNoEstatica = new FuncionNoEstatica();
                funcionNoEstatica.setNombre(production.lexemeRank(2));
                funcionNoEstatica.setTipoRetorno(production.lexicalCompRank(1).substring(5)); //int boolean etc....
                funcionNoEstatica.setRetorno(true); //void 
                funcionNoEstatica.setRetornoAsignado(regresarTipoDato(production.lexicalCompRank(production.getSizeTokens() - 3)));
                funcionNoEstatica.setFilaInicial(production.getLine());
                funcionNoEstatica.setFilaFinal(production.getFinalLine());
                funcionNoEstatica.setColumna(production.getColumn());
                if (funcionNoEstatica.getRetornoAsignado().equals("IDENTIFICADOR")) {
                    System.out.println("producion identificador => " + production.lexemeRank(production.getSizeTokens() - 3));
                    funcionNoEstatica.setNombreIdentificadorRetorno(production.lexemeRank(production.getSizeTokens() - 3));
                }
                Estructura.agregarFuncionNoEstatica(funcionNoEstatica);

            }

            if (productionName.equals("FUNCION_MAIN")) {
                funcionMain = new FuncionMain();
                funcionMain.setNombre(production.lexemeRank(3));
                funcionMain.setFilaInicial(production.getLine());
                funcionMain.setFilaFinal(production.getFinalLine());
                funcionMain.setColumna(production.getColumn());
                Estructura.setFuncionMain(funcionMain);

            }

        }

        for (Production production : identProd) {
            Variable variableDeclarada = new Variable();
            Variable variableEnUso = new Variable();
            LlamadaFuncion llamadaFuncion;
            String productionName = production.getName();

            // Manejo de declaraciones
            if (productionName.startsWith("DEC_") && (production.getColumn() < 8 || production.getColumn() > 16)) {
                System.out.println("productionName = " + productionName);
                System.out.println("production.lexemeRank(0,-1) = " + production.lexemeRank(0, -1));
                System.out.println("production.getColumn() = " + production.getColumn());
                variableDeclarada.setNombreVariable(production.lexemeRank(1));
                variableDeclarada.setTipoDato(productionName.substring(4));
                variableDeclarada.setFila(production.getLine());
                variableDeclarada.setColumna(production.getColumn());
                Estructura.agregarVariable(variableDeclarada);
            } // Manejo de asignaciones
            else if (productionName.startsWith("VAR_") && !production.lexicalCompRank(0).startsWith("DATO_")) {
                variableEnUso.setNombreVariable(production.lexemeRank(0));
                variableEnUso.setTipoDato(regresarTipoDato(production.lexicalCompRank(2)));
                variableEnUso.setFila(production.getLine());
                variableEnUso.setColumna(production.getColumn());
                Estructura.agregarVariableEnUso(variableEnUso);
            }

            if (productionName.equals("CALL_FUNC") && production.getSizeTokens() == 4) {
                llamadaFuncion = new LlamadaFuncion();
                llamadaFuncion.setNombre(production.lexemeRank(0));
                llamadaFuncion.setFilaInicial(production.getLine());
                llamadaFuncion.setColumna(production.getColumn());
                Estructura.agregarLlamadaFuncion(llamadaFuncion);
            } else //caso contrario
            if (productionName.equals("CALL_FUNC") && production.getSizeTokens() > 4) {
                llamadaFuncion = new LlamadaFuncion();
                llamadaFuncion.setNombre(production.lexemeRank(0));
                // Extraer los tipos de parámetros pasados en la llamada
                for (int i = 2; i < production.getSizeTokens() - 2; i += 2) { // Asumiendo que los parámetros están en índices impares
                    llamadaFuncion.agregarParametro(production.lexemeRank(i), regresarTipoDato(production.lexicalCompRank(i)));

                }
                llamadaFuncion.setFilaInicial(production.getLine());
                llamadaFuncion.setColumna(production.getColumn());
                Estructura.agregarLlamadaFuncion(llamadaFuncion);
            }
            verificarConcatenacion(production, Estructura.getVariablesDeclaradas());

        }

        Estructura.verificarVariablesUsadas();
        Estructura.verificarAsignacionVariables();

        Estructura.verificarRetornosFuncionesEstaticas();
        Estructura.verificarRetornosFuncionesNoEstaticas();

        Estructura.verificarContextoLlamadas();
        Estructura.verificarLlamadasFunciones();
        Estructura.verificarContextoDeLlamada();

        mensajesErroneos = Estructura.obtenerMensajesDeErrores();

        System.out.println("\nVariables Declaradas");
        Estructura.getVariablesDeclaradas().forEach(variable -> {
            System.out.println(variable);
        });

        System.out.println("\nVariables En Uso");
        Estructura.getVariablesEnUso().forEach(variable -> {
            System.out.println(variable);
        });

        System.out.println("\nErrores en variables");
        Estructura.getErroresVariables().forEach(variable -> {
            System.out.println(variable);
        });

        System.out.println("\n\nFunciones Estaticas declaradas");
        Estructura.getFuncionesEstaticas().forEach(funcion -> {
            System.out.println(funcion);
        });

        System.out.println("\nErrores Funciones Estaticas declaradas");
        Estructura.getErroresFuncionesEstaticas().forEach(funcion -> {
            System.out.println(funcion);
        });

        System.out.println("\n\nFunciones NO ESTATICAS declaradas");
        Estructura.getFuncionesNoEstaticas().forEach(funcion -> {
            System.out.println(funcion);
        });

        System.out.println("\nErrores Funciones NO ESTATICAS declaradas");
        Estructura.getErroresFuncionesNoEstaticas().forEach(funcion -> {
            System.out.println(funcion);
        });

        System.out.println("\n\nFuncion Main");
        System.out.println(Estructura.getFuncionMain());

        System.out.println("\n\nllamadas a Funciones");
        Estructura.getLlamadasFunciones().forEach(llamada -> {
            System.out.println(llamada);
        });

        System.out.println("\nErrores Llamadas a Funciones");
        Estructura.getErroresLlamadaFuncion().forEach(llamada -> {
            System.out.println(llamada);
        });

//        System.out.println("Analisis Semantico");
//
////        for(Production production: identProd){
////            System.out.println("Producción: " + production);
////            for (int i = 0; i < production.getSizeTokens(); i++) {
////                System.out.println("Lexema " + i + ": " + production.lexemeRank(i));
////            }
////        }
//        HashMap<String, String> declaredVariables = new HashMap<>();
//        //conocer si la variable esta anteriormente declarada y conocer si se le asigna un dato de tipo compatible
//        for (Production production : identProd) {
//            String productionName = production.getName();
//
//            // Manejo de declaraciones
//            if (productionName.startsWith("DEC_")) {
//                System.out.println("\nproductionName = " + productionName);
//                System.out.println("escrito en interfaz: " + production.lexemeRank(0, -1));
//                System.out.println("Estructura: " + production.lexicalCompRank(0, -1));
//
//                System.out.println("DECLARACIONES ");
//                String varName = production.lexemeRank(1); // El nombre del identificador
//                System.out.println("varName = " + varName);
//                String varType = productionName.substring(4); // Extraer el tipo del nombre de la producción (ej. "ENTERO", "FLOTANTE")
//                System.out.println("varType = " + varType);
//                if (declaredVariables.containsKey(varName)) {
//                    // Error: Variable ya declarada
//                    errors.add(new ErrorLSSL(1, " × Error semántico {}: variable ya declarada [#, %]", production, true));
//                } else {
//                    // Registrar la variable
//                    declaredVariables.put(varName, varType);
//                }
//            } // Manejo de asignaciones
//            else if (productionName.startsWith("VAR_") && !production.lexicalCompRank(0).startsWith("DATO_")) {
//
//                System.out.println("\nproductionName = " + productionName);
//                System.out.println("escrito en interfaz: " + production.lexemeRank(0, -1));
//                System.out.println("Estructura: " + production.lexicalCompRank(0, -1));
//
//                System.out.println("ASIGNACIONES");
//                String varName = production.lexemeRank(0); // El identificador usado en la asignación
//                System.out.println("varName = " + varName);
//                if (!declaredVariables.containsKey(varName)) {
//                    // Error: Variable no declarada
//                    errors.add(new ErrorLSSL(2, " × Error semántico {}: variable no declarada antes de ser usada [#, %]", production, true));
//                } else {
//                    // Verificar compatibilidad de tipos (opcional)
//                    String expectedType = declaredVariables.get(varName);
//                    System.out.println("expectedType = " + expectedType);
//                    String assignedValueType = production.lexicalCompRank(production.getSizeTokens() - 2); // Último componente léxico
//                    System.out.println("assignedValueType = " + assignedValueType);
//                    if (!isCompatibleType(expectedType, assignedValueType)) {
//                        errors.add(new ErrorLSSL(3, " × Error semántico {}: asignación incompatible con el tipo de dato [#, %]", production, true));
//                    }
//                }
//            }
//        }
//
//        System.out.println("\n\n=======Verificacion ahora de Funciones SIN PARAMETROS ");
//        HashMap<String, String> declaredFunctions = new HashMap<>();
//
//        for (Production production : identProd) {
//            String productionName = production.getName();
//            System.out.println("\nproductionName = " + productionName);
//            System.out.println("escrito en interfaz: " + production.lexemeRank(0, -1));
//            System.out.println("Estructura: " + production.lexicalCompRank(0, -1));
//
//            if (productionName.startsWith("FUNCION_NOPARAMS")) {
//                String functionName = production.lexemeRank(2); // Asumiendo que el nombre de la función está en el índice 2
//                System.out.println("functionName = " + functionName);
//                String returnType = production.lexicalCompRank(1); // Tipo de retorno después de 'PUBLIC' y opcionalmente 'STATIC'
//                System.out.println("returnType = " + returnType);
//
//                if (declaredFunctions.containsKey(functionName)) {
//                    // Error: Variable ya declarada
//                    errors.add(new ErrorLSSL(1, " × Error semántico {}: funcion ya declarada [#, %]", production, true));
//                } else {
//                    // Registrar la variable
//                    declaredFunctions.put(functionName, returnType); // Guarda la función
//                }
//
//            } else if (productionName.equals("CALL_FUNC") && production.getSizeTokens() == 4) {
//                String functionName = production.lexemeRank(0); // Nombre de la función llamada
//                System.out.println("functionName = " + functionName);
//                if (!declaredFunctions.containsKey(functionName)) {
//                    errors.add(new ErrorLSSL(5, " × Error semántico {}: la función '" + functionName + "' no está declarada [#, %]", production, true));
//                }
//            }
//
//        }
//
//        System.out.println("\n\n=======Verificacion ahora de Funciones CON PARAMETROS ");
//        HashMap<String, List<String>> functionParams = new HashMap<>();
//
//        // Validación de funciones con parámetros
//        for (Production production : identProd) {
//            String productionName = production.getName();
//
//            // Declaración de funciones con parámetros
//            if (productionName.startsWith("FUNCION_PARAMS")) {
//                String functionName = production.lexemeRank(2); // Nombre de la función
//                System.out.println(functionName);
//                List<String> paramTypes = new ArrayList<>();
//
//                // Extraer y validar los parámetros (tipo y nombre)
//                for (int i = 4; i < production.getSizeTokens() - 2; i += 2) {
//                    if (production.lexicalCompRank(i).equals("PARENTESIS_C")) {
//                        break;
//                    }
//                    if (production.lexicalCompRank(i).equals("COMA")) {
//                        i--;
//                        continue;
//                    }
//                    String paramType = production.lexicalCompRank(i);  // Tipo de dato
//                    paramType = paramType.substring(5);
////identificar que este dentro de los parentesis, posteriormente identificar que sea un tipo de dato seguido de nombre 
//                    String paramName = production.lexemeRank(i + 1);  // Nombre del parámetro
//
//                    // Agregar el parámetro si es válido
//                    paramTypes.add(paramType);
//                }
//
//                functionParams.put(functionName, paramTypes); // Registrar parámetros
//                System.out.println("Function '" + functionName + "' declared with parameters: " + paramTypes);
//            } else if (productionName.equals("CALL_FUNC") && production.getSizeTokens() > 4) {
//
//                String functionName = production.lexemeRank(0); // Nombre de la función
//
//                if (functionParams.get(functionName) == null) {
//                    // Error: Variable ya declarada
//                    errors.add(new ErrorLSSL(1, " × Error semántico {}: funcion NO declarada [#, %]", production, true));
//                    break;
//                }
//
//                // Validar los parámetros
//                List<String> expectedParams = functionParams.get(functionName);
//
//                List<String> actualParams = new ArrayList<>();
//                List<String> nombreParams = new ArrayList<>();
//
//                // Extraer los tipos de parámetros pasados en la llamada
//                for (int i = 2; i < production.getSizeTokens() - 2; i += 2) { // Asumiendo que los parámetros están en índices impares
//                    actualParams.add(production.lexicalCompRank(i));
//                    nombreParams.add(production.lexemeRank(i));
//
//                }
//
//                System.out.println("Esperados" + expectedParams + "\nactuales" + actualParams);
//                System.out.println("actualParams = " + actualParams);
//
//                // Comparar cantidad de parámetros
//                if (expectedParams.size() != actualParams.size()) {
//                    errors.add(new ErrorLSSL(
//                            6,
//                            "× Error semántico: la función '" + functionName + "' esperaba " + expectedParams.size()
//                            + " parámetros, pero se encontraron " + actualParams.size() + ". Revisa la cantidad de argumentos proporcionados.",
//                            production,
//                            true
//                    ));
//                    continue;
//                }
//
//                // Comparar tipos de parámetros
//                for (int i = 0; i < expectedParams.size(); i++) {
//                    System.out.println("----------" + actualParams.get(i));
//                    System.out.println("----------" + expectedParams.get(i));
//                    if (!isCompatibleType(expectedParams.get(i), actualParams.get(i), nombreParams.get(i), production, declaredVariables)) {
//                        errors.add(new ErrorLSSL(
//                                7,
//                                "× Error semántico: el parámetro " + (i + 1) + " de la función '" + functionName
//                                + "' esperaba un valor de tipo '" + expectedParams.get(i) + "', pero se proporcionó un valor de tipo '"
//                                + actualParams.get(i) + "'. Corrige el tipo de dato del argumento.",
//                                production,
//                                true
//                        ));
//                    }
//                }
//                if (production.getSizeTokens() < expectedParams.size()) {
//                    errors.add(new ErrorLSSL(
//                            8,
//                            "× Error semántico: producción malformada para '" + productionName + "'. Verifica que la definición de la función o su llamada sea correcta.",
//                            production,
//                            true
//                    ));
//                    continue;
//                }
//            }
//        }
    }

    // Función para verificar expresiones de concatenación
    private void verificarConcatenacion(Production production, List<Variable> variablesDeclaradas) {
        String productionName = production.getName();

        if (productionName.equals("EXP_CONCATENACION")) {
            String leftOperand = production.lexicalCompRank(0);
            if (!isValidOperand(leftOperand)) {
                errors.add(new ErrorLSSL(
                        9, "× Error semántico: el operando izquierdo no es válido en la concatenación. Esperado 'CADENA_TEXTO', 'IDENTIFICADOR' . [#, %]",
                        production, true
                ));
            }
//            String operator = production.lexemeRank(2);
//            if (!operator.equals("+")) {
//                errors.add(new ErrorLSSL(
//                        10, "× Error semántico: operador inválido en concatenación. Se esperaba '+'.", production, true
//                ));
//            }
            String rightOperand = production.lexicalCompRank(2);
            if (!isValidOperand(rightOperand)) {
                errors.add(new ErrorLSSL(
                        11, "× Error semántico: el operando derecho no es válido en la concatenación. Esperado cadena de texto o identificador.",
                        production, true
                ));
            }
            // Validar que identificadores dercho estén declarados
            if (leftOperand.equals("IDENTIFICADOR") || rightOperand.equals("IDENTIFICADOR")) {
                boolean valor = false;
                for (Variable variable : variablesDeclaradas) {

                    if (variable.getNombreVariable().equals(production.lexemeRank(0)) || variable.getNombreVariable().equals(production.lexemeRank(2))) {

                        valor = true;
                        break;
                    }

                }
                if (!valor) {
                    errors.add(new ErrorLSSL(
                            12, "× Error semántico: el identificador  no está declarado.  [#, %]", production, true
                    //                            13, "× Error semántico: el identificador usado en la concatenacion no está declarado.", production, true
                    ));
                }
            }
//            if (rightOperand.equals("IDENTIFICADOR") && !declaredVariables.containsKey(production.lexemeRank(3))) {
//                errors.add(new ErrorLSSL(
//                        13, "× Error semántico: el identificador '" + production.lexemeRank(3) + "' no está declarado.", production, true
//                ));
//            }
        }
    }

    // Función auxiliar para validar operandos
    private boolean isValidOperand(String operand) {
        return operand.equals("CADENA_TEXTO") || operand.equals("IDENTIFICADOR") || operand.equals("EXP_CONCATENACION");
    }

    private String regresarTipoDato(String valor) {

        switch (valor) {
            case "NUMERO_ENTERO":
                return "ENTERO";
            case "NUMERO_FLOTANTE":
                return "FLOTANTE";
            case "CARACTER":
                return "CHAR";
            case "TRUE":
            case "FALSE":
                return "BOOL";
            case "CADENA_TEXTO":
                return "CADENA";
            default:
                return valor;
        }

    }

    // Método auxiliar para verificar compatibilidad de tipos
    private boolean isCompatibleType(String expectedType, String assignedType) {
        // Ejemplo simple de compatibilidad
        switch (expectedType) {
            case "ENTERO":
            case "NUMERO_ENTERO":
                return assignedType.equals("NUMERO_ENTERO") || assignedType.equals("IDENTIFICADOR");
            case "FLOTANTE":
            case "NUMERO_FLOTANTE":
                return assignedType.equals("NUMERO_FLOTANTE") || assignedType.equals("IDENTIFICADOR");
            case "CHAR":
            case "CARACTER":
                return assignedType.equals("CARACTER") || assignedType.equals("IDENTIFICADOR");
            case "BOOL":
                return assignedType.equals("TRUE") || assignedType.equals("FALSE") || assignedType.equals("IDENTIFICADOR");
            case "CADENA":
            case "CADENA_TEXTO":
                return assignedType.equals("CADENA_TEXTO") || assignedType.equals("IDENTIFICADOR");
            default:
                return false;
        }
    }

    private boolean isCompatibleType(String expectedType, String assignedType, String nombreParam, Production production, HashMap<String, String> declaredVariables) {
        // Ejemplo simple de compatibilidad
        if (assignedType.equals("IDENTIFICADOR")) {
            if (!declaredVariables.containsKey(nombreParam)) {
                // Error: Variable no declarada
                errors.add(new ErrorLSSL(9, " × Error semántico {}: variable '" + nombreParam + "' no declarada antes de ser usada [#, %]", production,
                        true));
            }
        }
        switch (expectedType) {
            case "ENTERO":
                return assignedType.equals("NUMERO_ENTERO") || assignedType.equals("IDENTIFICADOR");
            case "FLOTANTE":
                return assignedType.equals("NUMERO_FLOTANTE") || assignedType.equals("IDENTIFICADOR");
            case "CHAR":
                return assignedType.equals("CARACTER") || assignedType.equals("IDENTIFICADOR");
            case "BOOL":
                return assignedType.equals("TRUE") || assignedType.equals("FALSE") || assignedType.equals("IDENTIFICADOR");
            case "CADENA":
                return assignedType.equals("CADENA_TEXTO") || assignedType.equals("IDENTIFICADOR");
            default:
                return false;
        }
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    private void fillTableTokens() {
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblTokens, data);
        });
    }

    private void printConsole() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0 || !mensajesErroneos.isEmpty()) {
            String strErrors = "\n";
            if (sizeErrors > 0) {
                Functions.sortErrorsByLineAndColumn(errors);
                for (ErrorLSSL error : errors) {
                    String strError = String.valueOf(error);
                    strErrors += strError + "\n";
                }
            }
            if (!mensajesErroneos.isEmpty()) {
                for (String error : mensajesErroneos) {
                    strErrors += error;
                }
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            jtaOutputConsole.setText("Compilación terminada...");
        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void clearFields() {
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}
