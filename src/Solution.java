
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Filter of user input in text filds
 */
class MyDocumentFilter extends DocumentFilter {
    private final String bannedSymbols = "[^0123456789.eE-]";
    
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        string = string.replaceAll(bannedSymbols, "");
        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        text = text.replaceAll(bannedSymbols, "");
        super.replace(fb, offset, length, text, attrs);
    }
}

interface LaguerreConst {
    double[][] Laguerre_x = {                     // Roots of the Laguerre polynoms
        {0.585786, 3.414214},
        {0.415775, 2.294280, 6.289945},
        {0.322548, 1.745761, 4.536620, 9.395071},
        {0.263560, 1.413403, 3.596426, 7.085810, 12.640801},
        {0.222847, 1.188932, 2.992736, 5.775144, 9.837467, 15.982874},
        {0.193044, 1.026665, 2.567877, 4.900353, 8.182153, 12.734180, 19.395728},
        {0.170280, 0.903702, 2.251087, 4.266700, 7.045905, 10.758516, 15.740679, 22.863132},
        {0.152322, 0.807220, 2.005135, 3.783474, 6.204957, 9.372985, 13.466237, 18.833598, 26.374072}
    };
    
    double[][] Laguerre_A = {                     // Coefs for the Gauss-Laguerre quadrature
        {8.535533e-01, 1.464466e-01},
        {7.110930e-01, 2.785177e-01, 1.038926e-02},
        {6.031541e-01, 3.574187e-01, 3.888790e-02, 5.392947e-04},
        {5.217556e-01, 3.986668e-01, 7.594245e-02, 3.611759e-03, 2.336997e-05},
        {4.589647e-01, 4.170008e-01, 1.133734e-01, 1.039920e-02, 2.610172e-04, 8.985479e-07},
        {4.093190e-01, 4.218313e-01, 1.471263e-01, 2.063351e-02, 1.074010e-03, 1.586546e-05, 3.170315e-08},
        {3.691886e-01, 4.187868e-01, 1.757950e-01, 3.334349e-02, 2.794536e-03, 9.076509e-05, 8.485747e-07, 1.048001e-09},
        {3.361264e-01, 4.112140e-01, 1.992875e-01, 4.746056e-02, 5.599627e-03, 3.052498e-04, 6.592123e-06, 4.110769e-08, 3.290874e-11}
    };
}

public class Solution extends javax.swing.JFrame implements LaguerreConst {
    int maxN = 10;
    private Function[] functions;                               // Array of all source functions
    
    static ArrayList<Double> Factorial = new ArrayList<>();     // List of factorials
    ArrayList BinomialCoef = new ArrayList();                   // List of binomial coefficients
    ArrayList coefsL = new ArrayList();                         // List of Laguerre polynoms coefficients (only for name creation)
    ArrayList<Function> L = new ArrayList<>();                  // Laguerre polynoms
    
    private double nPrecision;                                  // Variable for save text field value
    
    
    /**
     * Creates new form Solution
     */
    public Solution() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupCreationMethod = new javax.swing.ButtonGroup();
        panelTitle = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        labelSubtitle = new javax.swing.JLabel();
        btnShowPolynoms = new javax.swing.JButton();
        panelSource = new javax.swing.JPanel();
        labelFunction = new javax.swing.JLabel();
        comboFunction = new javax.swing.JComboBox<>();
        labelBound = new javax.swing.JLabel();
        labelNodes = new javax.swing.JLabel();
        spinnerNodes = new javax.swing.JSpinner();
        spinnerBound = new javax.swing.JSpinner();
        panelApprox = new javax.swing.JPanel();
        labelPower = new javax.swing.JLabel();
        spinnerPower = new javax.swing.JSpinner();
        btnApproximate = new javax.swing.JButton();
        editError = new javax.swing.JTextField();
        labelError = new javax.swing.JLabel();
        panelObtain = new javax.swing.JPanel();
        labelPrecision = new javax.swing.JLabel();
        editPrecision = new javax.swing.JTextField();
        btnPrecision = new javax.swing.JButton();
        panelResult = new javax.swing.JPanel();
        scrollInfo = new javax.swing.JScrollPane();
        textInfo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Approximation of function using Laguerre polynomes");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(0, 0, 204));
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Approximation of function");

        labelSubtitle.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelSubtitle.setForeground(new java.awt.Color(102, 0, 0));
        labelSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSubtitle.setText("using Laguerre polynoms");

        btnShowPolynoms.setText("show");
        btnShowPolynoms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPolynomsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelSubtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShowPolynoms)
                .addGap(235, 235, 235))
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSubtitle)
                    .addComponent(btnShowPolynoms))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        panelSource.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelFunction.setText("Source function:");

        labelBound.setText("Right bound of drowing:");

        labelNodes.setText("Number of nodes for integration (by Gauss-Laguerre quadratures):");

        spinnerNodes.setModel(new javax.swing.SpinnerNumberModel(5, 2, 9, 1));

        spinnerBound.setModel(new javax.swing.SpinnerNumberModel(15, 10, 100, 5));

        javax.swing.GroupLayout panelSourceLayout = new javax.swing.GroupLayout(panelSource);
        panelSource.setLayout(panelSourceLayout);
        panelSourceLayout.setHorizontalGroup(
            panelSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSourceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFunction)
                    .addGroup(panelSourceLayout.createSequentialGroup()
                        .addGroup(panelSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSourceLayout.createSequentialGroup()
                                .addComponent(labelBound)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerBound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(comboFunction, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNodes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerNodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelSourceLayout.setVerticalGroup(
            panelSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSourceLayout.createSequentialGroup()
                .addComponent(labelFunction, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(comboFunction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNodes, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerNodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelSourceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBound)
                    .addComponent(spinnerBound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelApprox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelPower.setText("Power of approximation polynom:");

        spinnerPower.setModel(new javax.swing.SpinnerNumberModel(5, 2, null, 1));

        btnApproximate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnApproximate.setForeground(new java.awt.Color(0, 0, 153));
        btnApproximate.setText("Approximate");
        btnApproximate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproximateActionPerformed(evt);
            }
        });

        editError.setEditable(false);
        editError.setBackground(new java.awt.Color(255, 255, 255));

        labelError.setForeground(new java.awt.Color(0, 0, 153));
        labelError.setText("Approximation error:");

        javax.swing.GroupLayout panelApproxLayout = new javax.swing.GroupLayout(panelApprox);
        panelApprox.setLayout(panelApproxLayout);
        panelApproxLayout.setHorizontalGroup(
            panelApproxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelApproxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelApproxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelApproxLayout.createSequentialGroup()
                        .addComponent(labelPower)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerPower, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelApproxLayout.createSequentialGroup()
                        .addComponent(btnApproximate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelApproxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelError)
                            .addComponent(editError, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelApproxLayout.setVerticalGroup(
            panelApproxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelApproxLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(panelApproxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPower)
                    .addComponent(spinnerPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelApproxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelApproxLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(labelError)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnApproximate, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        panelObtain.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelPrecision.setText("Obtain a given precision:");

        editPrecision.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        editPrecision.setText("1.0e-6");
        editPrecision.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                editPrecisionFocusLost(evt);
            }
        });
        editPrecision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPrecisionActionPerformed(evt);
            }
        });

        btnPrecision.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPrecision.setForeground(new java.awt.Color(0, 0, 153));
        btnPrecision.setText("Obtain");
        btnPrecision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecisionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelObtainLayout = new javax.swing.GroupLayout(panelObtain);
        panelObtain.setLayout(panelObtainLayout);
        panelObtainLayout.setHorizontalGroup(
            panelObtainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelObtainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelPrecision)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editPrecision, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrecision, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
        );
        panelObtainLayout.setVerticalGroup(
            panelObtainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelObtainLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(panelObtainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPrecision, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editPrecision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(btnPrecision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelResult.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        textInfo.setColumns(20);
        textInfo.setRows(5);
        textInfo.setToolTipText("Information panel");
        scrollInfo.setViewportView(textInfo);

        javax.swing.GroupLayout panelResultLayout = new javax.swing.GroupLayout(panelResult);
        panelResult.setLayout(panelResultLayout);
        panelResultLayout.setHorizontalGroup(
            panelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResultLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollInfo)
                .addContainerGap())
        );
        panelResultLayout.setVerticalGroup(
            panelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelResultLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelResult, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(panelSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelApprox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelObtain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelApprox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSource, javax.swing.GroupLayout.PREFERRED_SIZE, 109, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelObtain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(panelResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * For calculating:
     ** ... the table of factorials
     * ... the binomial coefs (via Pascal triangle)
     * ... the coefficients of Laguerre polynoms (via binomial coefs & factorials)
     * ... the Laguerre polynoms (using recurtion method)
     */
    private void calcWorkData(int from, int to) {
        int i, j;
        
        for(i = from; i <= to; i++) {
            // factorial
            Factorial.add( Factorial.get(i-1) * i );
            
            // binomial coefs
            double[] bc = new double[i+1];
            bc[0] = 1;
            bc[i] = 1;
            double[] bc0 = (double[]) BinomialCoef.get(i-1);
            for(j = 1; j < i; j++) 
                bc[j] = bc0[j-1] + bc0[j];
            BinomialCoef.add(bc);
            // ===
            
            // Laguerre polynoms coefs
            double[] cl = new double[i+1];
            double iPow = 1;
            bc = (double[]) BinomialCoef.get(i);
            for (j = 0; j <= i; j++) {
                cl[j] = iPow * bc[j] / Factorial.get(j);
                iPow *= -1;
            }
            coefsL.add(cl);
            // ===
            
            // Laguerre polynoms
            Function Lminus1 = L.get(i-1);
            Function Lminus2 = L.get(i-2);
            double[] coefs = (double[])coefsL.get(i);
            L.add( Functions.Laguerre(i, Lminus1, Lminus2, coefs) );            // Laguerre polynom L(k) via L(k-1) & L(k-2) (coefs - only for name of polynom)
            // ===
        }
    }
    
    /**
     * Show all Laguerre polynoms
     */
    private void showAllLaguerrePolynoms() {
        textInfo.setText("The approximation based on the Laguerre polynoms\n");
        textInfo.append("(created by recurtion method)\n\n");
        for(int k = 0; k < L.size(); k++)
            textInfo.append( String.format("L(%d) = %s\n", k, L.get(k).getName()) );
    }
    
    /**
     * Add polynoms to list depends on spinner value (if needed)
     */
    private void addSomePolynoms() {
        int n = (int) spinnerPower.getValue();
        if (n > maxN) {
            calcWorkData(maxN+1, n);
            maxN = n;
        }
    }
    
    /**
     * Calculate the integral via Gauss-Laguerre quadratures
     */
    private double calcLaguerre(Function func) {
        int n = (int) spinnerNodes.getValue();
        double F = 0;

        for (int i = 0; i < n; i++) {
            double xi = Laguerre_x[n-2][i];
            double Ai = Laguerre_A[n-2][i];
            F +=  Ai * func.f(xi);
        }
        return F;
    }
    
    /**
     * Create the approximation polynom for selected function
     */
    private HashMap makeApproximation() {
        addSomePolynoms();
        
        int i, j;
        int n = (int) spinnerPower.getValue();
        double[] approxCoefs = new double[n+1];
        double[] c = new double[n+1];
        
        Function sourceFunction = functions[ comboFunction.getSelectedIndex() ];
        String integrationMethod = "Gauss-Laguerre quadratures on " + spinnerNodes.getValue() + " nodes";
        
        for(i = 0; i <= n; i++) {
            c[i] = calcLaguerre( Functions.createProduct(sourceFunction, L.get(i)) );
            double[] cl = (double[]) coefsL.get(i);
            for (j = 0; j <= i; j++)
                approxCoefs[j] += c[i] * cl[j];
        }
            
        Function approxPolynom = Functions.createApproximationPolynom(c, L, approxCoefs);               // approxCoefs - only for name of polynom)
        Function errorFunctionToCalc = Functions.createErrorFunction(sourceFunction, approxPolynom);
        Function errorFunctionToDraw = Functions.createErrorFunctionExp(sourceFunction, approxPolynom);
        double errorValue = calcLaguerre(errorFunctionToCalc);
        
        HashMap result = new HashMap();
        result.put("sourceFunction", sourceFunction);
        result.put("approxPolynomPow", n);
        result.put("approxPolynom", approxPolynom);
        result.put("errorFunction", errorFunctionToDraw);
        result.put("errorValue", errorValue);
        result.put("drawingBound", (int) spinnerBound.getValue());
        result.put("integrationMethod", integrationMethod);
        
        return result;
    }
    
    /**
     * Check the value of the precision text field
     */
    private void checkPrecision() {
        try {
            double x = Double.parseDouble(editPrecision.getText());
            nPrecision = x;
        } catch (NumberFormatException e) {
            editPrecision.setText("" + nPrecision);
        }
    }
    
    /**
     * Append results of approximation to text area
     */
    private void showResults(HashMap result, boolean showMethod) {
        String integrationMethod = (String) result.get("integrationMethod");
        int pow = (int) result.get("approxPolynomPow");
        Function approxPolynom = (Function) result.get("approxPolynom");
        double error = (double) result.get("errorValue");
        
        if (showMethod) textInfo.append(integrationMethod + ":\n\n");
        textInfo.append("Approximation polynom (power = " + pow + "):\n");
        textInfo.append("p(x) = " + approxPolynom.getName() + "\n"); 
        textInfo.append("approximation error = " + error + "\n\n");
        
        editError.setText( "" + error);
    }
    
    private void btnApproximateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproximateActionPerformed
        HashMap result = makeApproximation();
        textInfo.setText("");
        showResults(result, true);
        Draw.doIt(result);
    }//GEN-LAST:event_btnApproximateActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        final int functionsCount = 4;
        
        // Fill array of the functions
        functions = new Function[functionsCount];
        
        functions[0] = Functions.f1();
        functions[1] = Functions.f2();
        functions[2] = Functions.f3();
        functions[3] = Functions.f4();
        
        // Form combo box from the names of the functions
        String[] comboList = new String[functionsCount];
        for (int i = 0; i < functionsCount; i++) 
            comboList[i] = "f(x) = " + functions[i].getName();
        comboFunction.setModel(new DefaultComboBoxModel<>(comboList));
        comboFunction.setSelectedIndex(0);
        // ===
        
        // Set filter to text fields
        ((AbstractDocument) editPrecision.getDocument()).setDocumentFilter(new MyDocumentFilter());
        
        checkPrecision();
        
        Factorial.add(1.0);
        Factorial.add(1.0);
        
        BinomialCoef.add(new double[]{1});
        BinomialCoef.add(new double[]{1,1});
        
        coefsL.add(new double[]{1});
        coefsL.add(new double[]{1,-1});
        
        L.add( Functions.L0() );
        L.add( Functions.L1() );
        
        calcWorkData(2,maxN);
        showAllLaguerrePolynoms();
    }//GEN-LAST:event_formWindowOpened

    private void btnShowPolynomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPolynomsActionPerformed
        addSomePolynoms();
        showAllLaguerrePolynoms();
    }//GEN-LAST:event_btnShowPolynomsActionPerformed

    private void editPrecisionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editPrecisionFocusLost
        checkPrecision();
    }//GEN-LAST:event_editPrecisionFocusLost

    private void editPrecisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPrecisionActionPerformed
        checkPrecision();
    }//GEN-LAST:event_editPrecisionActionPerformed

    private void btnPrecisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrecisionActionPerformed
        final int MAX_POW = 20;
        
        double error = 0;
        HashMap result;
        boolean drawResult = true;
        
        textInfo.setText("");
        
        int i = 2;
        do {
            spinnerPower.setValue(i);
            result = makeApproximation();
            error = (double) result.get("errorValue");
            
            showResults(result, i!=2);
            
            i++;
            if (i == MAX_POW) {
                textInfo.append("!!! CAN'T OBTAIN THE GIVEN PRECISION !!!"); 
                drawResult = false;
                break;
            }
        } while (error > nPrecision);
        
        if (drawResult) Draw.doIt(result);
    }//GEN-LAST:event_btnPrecisionActionPerformed

    // <editor-fold defaultstate="collapsed" desc="Variables declaration">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApproximate;
    private javax.swing.JButton btnPrecision;
    private javax.swing.JButton btnShowPolynoms;
    private javax.swing.JComboBox<String> comboFunction;
    private javax.swing.JTextField editError;
    private javax.swing.JTextField editPrecision;
    private javax.swing.ButtonGroup groupCreationMethod;
    private javax.swing.JLabel labelBound;
    private javax.swing.JLabel labelError;
    private javax.swing.JLabel labelFunction;
    private javax.swing.JLabel labelNodes;
    private javax.swing.JLabel labelPower;
    private javax.swing.JLabel labelPrecision;
    private javax.swing.JLabel labelSubtitle;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JPanel panelApprox;
    private javax.swing.JPanel panelObtain;
    private javax.swing.JPanel panelResult;
    private javax.swing.JPanel panelSource;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JScrollPane scrollInfo;
    private javax.swing.JSpinner spinnerBound;
    private javax.swing.JSpinner spinnerNodes;
    private javax.swing.JSpinner spinnerPower;
    private javax.swing.JTextArea textInfo;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
}