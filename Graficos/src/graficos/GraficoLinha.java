package graficos;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoLinha {
	private JFreeChart grafico;
	public GraficoLinha(String nome) {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		ds.addValue(40.5, "maximo", "1");
		ds.addValue(38.2, "maximo", "2");
		ds.addValue(37.3, "maximo", "3");
		ds.addValue(31.5, "maximo", "4");
		ds.addValue(35.7, "maximo", "5");
		ds.addValue(42.5, "maximo", "6");
		// cria o gráfico
		this.grafico = ChartFactory.createLineChart(nome, "Tempo", "Valor", ds, PlotOrientation.VERTICAL, true, true, false);
	}
	public JFreeChart getGrafico(){
		return this.grafico;
	}
}
