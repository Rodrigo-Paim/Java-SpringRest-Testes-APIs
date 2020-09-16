package org.serratec.sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.serratec.entidades.Cargo;
import org.serratec.entidades.Funcionario;
import org.serratec.telas.CargosConsultar;
import org.serratec.telas.CargosEditar;
import org.serratec.telas.CargosInserir;
import org.serratec.telas.FuncionariosConsultar;
import org.serratec.telas.FuncionariosEditar;
import org.serratec.telas.FuncionariosInserir;
import org.serratec.telas.Inicio;
import org.serratec.telas.Login;
import org.serratec.telas.RelatoriosCargos;
import org.serratec.telas.RelatoriosSalarios;

public class Navegador {
    
    // Menu
    private static boolean menuConstruido;
    private static boolean menuHabilitado;
    private static JMenuBar menuBar;
    private static JMenu menuArquivo, menuFuncionarios, menuCargos, menuRelatorios;
    private static JMenuItem miSair, miFuncionariosConsultar, miFuncionariosCadastrar, miCargosConsultar;
    private static JMenuItem miCargosCadastrar, miRelatoriosCargos, miRelatoriosSalarios;
    
    public static void login(){
        Sistema.tela = new Login();
        Sistema.frame.setTitle("Funcionarios Xico Coxinha");
        Navegador.atualizarTela();
    }
    
    public static void inicio(){
        Sistema.tela = new Inicio();
        Sistema.frame.setTitle("Funcionarios Xico Coxinha");
        Navegador.atualizarTela();
    }
    
    public static void funcionariosCadastrar(){
        Sistema.tela = new FuncionariosInserir();   
        Sistema.frame.setTitle("Funcionarios Xico Coxinha - Cadastrar funcionarios");     
        Navegador.atualizarTela();
    }
    
    public static void funcionariosConsultar(){
        Sistema.tela = new FuncionariosConsultar();
        Sistema.frame.setTitle("Funcionarios Xico Coxinha - Consultar funcionarios");     
        Navegador.atualizarTela();
    }
    
    public static void funcionariosEditar(Funcionario funcionario){
        Sistema.tela = new FuncionariosEditar(funcionario);  
        Sistema.frame.setTitle("Funcionarios Xico Coxinha - Editar funcionarios");           
        Navegador.atualizarTela();
    }
    
    public static void cargosCadastrar(){
        Sistema.tela = new CargosInserir();
        Sistema.frame.setTitle("Funcionarios Xico Coxinha - Cadastrar cargos");
        Navegador.atualizarTela();
    }
    
    public static void cargosConsultar(){
        Sistema.tela = new CargosConsultar();  
        Sistema.frame.setTitle("Funcionarios Xico Coxinha - Consultar cargos");      
        Navegador.atualizarTela();
    }
    
    public static void cargosEditar(Cargo cargo){
        Sistema.tela = new CargosEditar(cargo);      
        Sistema.frame.setTitle("Funcionarios Xico Coxinha - Editar cargos");  
        Navegador.atualizarTela();
    }
    
    public static void relatoriosCargos(){   
        Sistema.tela = new RelatoriosCargos();
        Sistema.frame.setTitle("Funcionarios Xico Coxinha - Relatórios");    
        Navegador.atualizarTela();
    }
    
    public static void relatoriosSalarios(){
        Sistema.tela = new RelatoriosSalarios();
        Sistema.frame.setTitle("Funcionarios Xico Coxinha - Relatórios");    
        Navegador.atualizarTela();
    }
    
    // Metodo que remove a tela atual e adiciona a proxima tela
    private static void atualizarTela(){
        Sistema.frame.getContentPane().removeAll();
        Sistema.tela.setVisible(true);
        Sistema.frame.add(Sistema.tela);
        
        Sistema.frame.setVisible(true);
    }
    
    private static void construirMenu(){
        if(!menuConstruido){
            menuConstruido = true;
            
            menuBar = new JMenuBar();
            
            // menu Arquivo
            menuArquivo = new JMenu("Arquivo");
            menuBar.add(menuArquivo);
            miSair = new JMenuItem("Sair");
            menuArquivo.add(miSair);
            
            // menu FuncionÃ¡rios
            menuFuncionarios = new JMenu("Funcuionario");
            menuBar.add(menuFuncionarios);
            miFuncionariosCadastrar = new JMenuItem("Cadastrar");
            menuFuncionarios.add(miFuncionariosCadastrar);
            miFuncionariosConsultar = new JMenuItem("Consultar");
            menuFuncionarios.add(miFuncionariosConsultar);
            
            // menu Cargos
            menuCargos = new JMenu("Cargos");
            menuBar.add(menuCargos);
            miCargosCadastrar = new JMenuItem("Cadastrar");
            menuCargos.add(miCargosCadastrar);
            miCargosConsultar = new JMenuItem("Consultar");
            menuCargos.add(miCargosConsultar);
            
            // menu Relatorios
            menuRelatorios = new JMenu("Relatórios");
            menuBar.add(menuRelatorios);
            miRelatoriosCargos = new JMenuItem("Funcionarios por cargos");
            menuRelatorios.add(miRelatoriosCargos);
            miRelatoriosSalarios = new JMenuItem("Salarios dos funcionarios");
            menuRelatorios.add(miRelatoriosSalarios);
            
            criarEventosMenu();
            
        }
    }
    
    public static void habilitaMenu(){
        if(!menuConstruido) construirMenu();
        if(!menuHabilitado){
            menuHabilitado = true;
            Sistema.frame.setJMenuBar(menuBar);
        }
    }
    
    public static void desabilitaMenu(){
        if(menuHabilitado){
            menuHabilitado = false;
            Sistema.frame.setJMenuBar(null);
        }        
    }

    private static void criarEventosMenu() {
        miSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Funcionario
        miFuncionariosCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionariosCadastrar();
            }
        });
        miFuncionariosConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionariosConsultar();
            }
        });
        
        // Cargos
        miCargosCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargosCadastrar();
            }
        });
        miCargosConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargosConsultar();
            }
        });
        
        miRelatoriosCargos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                relatoriosCargos();
            }
        });
        
        miRelatoriosSalarios.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                relatoriosSalarios();
            }
        });
    }
}
