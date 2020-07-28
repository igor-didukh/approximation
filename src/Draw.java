
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

class Draw {
    private static int screenWidth, screenHeight;
    
    /**
     * Recieve screen resolution
     */
    static {
    GraphicsDevice[] screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        for (GraphicsDevice graphicsDevice : screenDevices) {
            screenWidth = graphicsDevice.getDefaultConfiguration().getBounds().width;
            screenHeight = graphicsDevice.getDefaultConfiguration().getBounds().height;
        }
    }
    
    /**
     * Create title from string
     */
    private static TextTitle createTitle(String s, int fontSize) {
        return new TextTitle( s, new Font("Arial", Font.PLAIN, fontSize) );
    }
    
    /**
     * Draw axises
     * @return Plot object (for renderer)
     */
    private static XYPlot setPlot(JFreeChart chart) {
        XYPlot xyPlot = (XYPlot) chart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        return xyPlot;
    }
    
    /**
     * Drowing charts for selected function, approximation polynom and approximation error
     */
    static void doIt(HashMap args) {
        int pow  = (int) args.get("approxPolynomPow");
        double b  = (int) args.get("drawingBound");
        double errorValue  = (double) args.get("errorValue");
        
        String integrationMethod = (String) args.get("integrationMethod");
        
        Function sourceFunction = (Function) args.get("sourceFunction");
        Function approxPolynom = (Function) args.get("approxPolynom");
        Function errorFunction = (Function) args.get("errorFunction");
        
        final int count = 100;
        final double a = 0;
        double h = (b - a) / (count - 1);
        
        String  fSourceName = sourceFunction.getName(),
                fErrorName = errorFunction.getName();
        
        XYSeriesCollection  datasetMain = new XYSeriesCollection(), 
                            datasetError = new XYSeriesCollection();
        
        XYSeries seriesMainSource = new XYSeries(fSourceName), 
                 seriesMainApproximation = new XYSeries("approximation polynom p(x)"), 
                 seriesError = new XYSeries(fErrorName);

        seriesMainSource.add( a, sourceFunction.f(a) );
        seriesMainApproximation.add( a, approxPolynom.f(a) );
        seriesError.add( a, errorFunction.f(a) );
        
        for (int i = 1; i < count; i++) {
            double x = a + i * h;
            double y1 = sourceFunction.f(x);
            double y2 = approxPolynom.f(x);
            double y3 = errorFunction.f(x);
            
            seriesMainSource.add( x, y1 );
            seriesMainApproximation.add( x, y2 );
            seriesError.add( x, y3 );
        }
        
        datasetMain.addSeries(seriesMainSource);
        datasetMain.addSeries(seriesMainApproximation);
        JFreeChart jFreeChartMain = ChartFactory.createXYLineChart("", "X", "Y", datasetMain, PlotOrientation.VERTICAL, true, false, false);
        
        TextTitle tt = createTitle("f(x) = " + fSourceName, 18);
        jFreeChartMain.setTitle(tt);
        setPlot(jFreeChartMain);
        
        datasetError.addSeries(seriesError);
        JFreeChart jFreeChartError = ChartFactory.createXYLineChart("", "X", "Y", datasetError, PlotOrientation.VERTICAL, false, false, false);
        
        tt = createTitle("\nApproximation error function = " + fErrorName, 14);
        jFreeChartError.setTitle(tt);
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        
        setPlot(jFreeChartError).setRenderer(renderer);
        
        ChartPanel CPMain = new ChartPanel(jFreeChartMain);
        ChartPanel CPError = new ChartPanel(jFreeChartError);
        
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        
        JFrame frame = new JFrame("Approximation of " + fSourceName);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        Container contPane = frame.getContentPane();
        contPane.setLayout(gbl);
        
        JTextArea textInfo = new JTextArea();
        textInfo.setFont( new Font("Consolas", 0, 12) );
        textInfo.setEditable(false);
        textInfo.setText("Result of approximation:\n");
        textInfo.append( String.format("   - power of polynom: %d\n", pow) );
        textInfo.append( String.format("   - integration method: %s\n", integrationMethod) );
        textInfo.append( "   - approximation error: " + errorValue );
        
        contPane.add(textInfo);
        
        c.gridx = 0; 
        c.gridy = GridBagConstraints.RELATIVE; 
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(6, 6, 3, 6);
        gbl.setConstraints(CPMain, c);
        contPane.add(CPMain);
        
        c.gridx = 0; 
        c.gridy = GridBagConstraints.RELATIVE; 
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.5;
        c.insets = new Insets(3, 6, 6, 6);
        gbl.setConstraints(CPError, c);
        contPane.add(CPError);
        
        int frameWidth = 600, frameHeight = 700;
        int posX = (screenWidth - frameWidth) / 2;
        int posY = (screenHeight - frameHeight) / 2;
        frame.setPreferredSize(new Dimension(frameWidth, frameHeight));
        frame.setBounds(posX, posY, frameWidth, frameHeight);
        frame.pack();
        frame.setVisible(true);          
    }        
}