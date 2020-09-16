package org.serratec.telas;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import org.serratec.sistema.BancoDeDados;
import org.serratec.sistema.Navegador;

public class RelatoriosSalarios<CategoryDataset> extends JPanel {
	
    JLabel labelTitulo, labelDescricao;
    
    public RelatoriosSalarios(){
        criarComponentes();
        criarEventos();
        Navegador.habilitaMenu();
    }

    private void criarComponentes() {
        setLayout(null);
        
        labelTitulo = new JLabel("Relatorios", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelDescricao = new JLabel("Esse grafico representa a quantidade de funcionarios por faixas de salarial", 
                JLabel.CENTER);
        
        CategoryDataset dadosGrafico = this.criarDadosGrafico();
        
        JFreeChart someChart = ChartFactory.createBarChart3D("", null, "Quantidade de funcionarios", 
                dadosGrafico, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) someChart.getCategoryPlot();
        
        plot.setBackgroundPaint(null);
        plot.setOutlinePaint(null);
        someChart.setBackgroundPaint(null);
        
        plot.getRangeAxis().setLowerBound(0);
        plot.getRangeAxis().setRange(new Range(0, 5));
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        plot.getRangeAxis().setAutoRange(false);
        Font font3 = new Font(labelTitulo.getFont().getName(), Font.PLAIN, 10); 
        plot.getRangeAxis().setLabelFont(font3);
        
        plot.getDomainAxis().setVisible(false);
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemMargin(-2);
        
        ChartPanel chartPanel = new ChartPanel(someChart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(660, 340);
            }
        };
        
        labelTitulo.setBounds(20, 20, 660, 40);
        labelDescricao.setBounds(20, 50, 660, 40);
        chartPanel.setBounds(20, 100, 660, 340);
        
        add(labelTitulo);
        add(labelDescricao);
        add(chartPanel);
        
        setVisible(true);
    }

    private void criarEventos() {
        
    }

    @SuppressWarnings("empty-statement")
    private CategoryDataset criarDadosGrafico() {
        
        DefaultCategoryDataset dados = new DefaultCategoryDataset();
           
        // conexÃ£o
        Connection conexao;
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;
        
        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);
            
            // criando a instruÃ§Ã£o SQL 
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT";
            query = query + " COUNT(case when salario < 1000 then 1 end) AS faixa1,";
            query = query + " COUNT(case when salario >= 1000 AND salario < 2000 then 1 end) AS faixa2,";
            query = query + " COUNT(case when salario >= 2000 AND salario < 3000 then 1 end) AS faixa3,";
            query = query + " COUNT(case when salario >= 3000 AND salario < 4000 then 1 end) AS faixa4,";
            query = query + " COUNT(case when salario >= 4000 AND salario < 5000 then 1 end) AS faixa5,";
            query = query + " COUNT(case when salario >= 5000 then 1 end) AS faixa6";
            query = query + " FROM funcionarios";
            resultados = instrucaoSQL.executeQuery(query);

            while (resultados.next()) {
                dados.addValue(resultados.getInt("faixa1"), "Ate R$ 1.000,00", "< R$ 1.000,00");
                dados.addValue(resultados.getInt("faixa2"), "De R$ 1.000,00 ate R$ 2.000,00", "R$ 1.000,00 - R$ 2.000,00");
                dados.addValue(resultados.getInt("faixa3"), "De R$ 2.000,00 ate R$ 3.000,00", "R$ 2.000,00 - R$ 3.000,00");
                dados.addValue(resultados.getInt("faixa4"), "De R$ 3.000,00 ate R$ 4.000,00", "R$ 3.000,00 - R$ 4.000,00");
                dados.addValue(resultados.getInt("faixa5"), "De R$ 4.000,00 ate R$ 5.000,00", "R$ 4.000,00 - R$ 5.000,00");
                dados.addValue(resultados.getInt("faixa6"), "A partir de R$ 5.000,00", "> R$ 5.000,00");
            }
            
            return dados;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatario.\n\n"+ex.getMessage());
            org.serratec.sistema.Navegador.inicio();
        }
        
        return null;
    }

}
