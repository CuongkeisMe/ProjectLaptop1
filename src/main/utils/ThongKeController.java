
package main.utils;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import main.entity.ThongKe;
import main.repository.ThongKeDTRepositories;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ACER
 */
public class ThongKeController {

    ThongKeDTRepositories tksv = new ThongKeDTRepositories();

    public void setDateToChart(JPanel jpnItem, int year) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 1; i <= 12; i++) {
            double tongTien = 0;
            if (tksv.ThongKeChart(i, year) == null) {
                tongTien = 0;
            } else {
                ThongKe tk = tksv.ThongKeChart(i, year);
                tongTien = tk.getDoanhThu();
            }
            dataset.addValue(tongTien, "Thống Kê", "Tháng" + " " + i);
        }

        JFreeChart chart = ChartFactory.createBarChart("Thống Kê Doanh Thu", "Thời Gian", "Doanh Thu", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
}
