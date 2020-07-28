
import java.util.ArrayList;

/**
 * Base function interface
 */
interface Function {
    String getName();
    double f(double x);
}

/**
 * All functions
 */
class Functions {
    final static double EPS = 0.001;
    
    /**
     * Create name for polynom
     * @param a - coefs
     * @param readableName - for Laguerre polynoms: true - 1/factorial * (...), false - simple name
     */
    static String createName(double[] a, boolean readableName) {
        int n = a.length;
        
        double fact = readableName ? Solution.Factorial.get(n-1) : 1;

        String s = "", sign = "", coef = "", power = "";
        
        for (int i = a.length-1; i >= 0; i--) {
            if (a[i] !=0) {
                if (i == a.length-1 & a[i] > 0) 
                    sign = "";
                else 
                    sign = a[i] < 0 ? " - " : " + ";
                
                double ai = Math.abs(a[i] * fact);
                if (i != 0 & Math.abs(ai-1) < EPS) 
                    coef = "";
                else 
                    coef = (readableName) ? String.format("%.0f", ai) : "" + ai;
                
                if (i == 0) 
                    power = "";
                else                 
                    power = (i == 1) ? "x" : "x^" + i;
                
            }
            s += "" + sign + coef + power;
        }

        return (readableName) ? s = String.format("1/%.0f * [%s]", fact, s) : s;
    }
    
    /**
    * Source functions
    */
    static Function f1() {
        return new Function() {
            @Override
            public String getName() {
                return "log(x+1)";
            }

            @Override
            public double f(double x) {
                return Math.log(x+1);
            }
        };
    }
    
    static Function f2() {
        return new Function() {
            @Override
            public String getName() {
                return "sqrt(x)";
            }

            @Override
            public double f(double x) {
                return Math.sqrt(x);
            }
        };
    }
    
    static Function f3() {
        return new Function() {
            @Override
            public String getName() {
                return "atan(x)";
            }

            @Override
            public double f(double x) {
                return Math.atan(x);
            }
        };
    }
    
    static Function f4() {
        double[] c = {-1,3,-5,2,1};
	
        return new Function() {
            @Override
            public String getName() {
                return createName(c, false);
            }

            @Override
            public double f(double x) {
                double result = c[c.length-1];
                for (int i = c.length-2; i >= 0; i--) {
                    result *= x;
                    result += c[i];
                }
                return result;
            }
        };
    }
    // End of source functions
    
    /**
     * Create polynom defined by its coefs 
     */
    static Function createPoly(double[] L, boolean readableName) {
        return new Function() {
            @Override
            public String getName() {
                return createName(L, readableName);
            }

            @Override
            public double f(double x) {
                double result = L[L.length-1];
                for (int i = L.length-2; i >= 0; i--) {
                    result *= x;
                    result += L[i];
                }
                return result;
            }
        };
    }
    
    /**
    * Create first Laguerre polynom
    */
    static Function L0() {
        return new Function() {
            @Override
            public String getName() {
               return "1";
            }

            @Override
            public double f(double x) {
               return 1;
            }
        };
   }

    /**
     * Create second Laguerre polynom
     */
    static Function L1() {
        return new Function() {
            @Override
            public String getName() {
                return "1-x";
            }

            @Override
            public double f(double x) {
                return 1-x;
            }
        };
    }
    
    /**
     * Create Laguerre polynom Lk (recurtion method)
     */
    static Function Laguerre(double k, Function Lkminus1, Function Lkminus2, double[] coefsForName) {
        return new Function() {
            @Override
            public String getName() {
                return createName(coefsForName, true);
            }

            @Override
            public double f(double x) {
                double y1 = Lkminus1.f(x);
                double y2 = Lkminus2.f(x);
                return 1 / k * ( (2*k-x-1) * y1 - (k-1) * y2 );
            }
        };
    }
    
    /**
     * Create the integrand as result of: f(x) * g(x) (for coefficients of approximation polynom calculation)
     */
    static Function createProduct(Function func1, Function func2) {
        return new Function() {
            @Override
            public String getName() {
                return String.format( "[%s] * [%s]", func1.getName(), func2.getName() );
            }
            
            @Override
            public double f(double x) {
                return func1.f(x) * func2.f(x);
            }
        };
    }
    
    /**
     * Create the integrand as result of: (f(x) - g(x))^2 (for error calculation)
     */
    static Function createErrorFunction(Function func1, Function func2) {
        return new Function() {
            @Override
            public String getName() {
                //return String.format( "([%s] - [%s])^2", func1.getName(), func2.getName() );
                return "[f(x)-p(x)]^2";
            }
            
            @Override
            public double f(double x) {
                double d = func1.f(x) - func2.f(x);
                return d * d;
            }
        };
    }
    
    /**
     * Create the function as result of: exp(-x) * [f(x) - g(x)]^2 (for error function drawing)
     */
    static Function createErrorFunctionExp(Function func1, Function func2) {
        return new Function() {
            @Override
            public String getName() {
                //return String.format( "exp(-x) * ([%s] - [%s])^2", func1.getName(), func2.getName() );
                return "exp(-x) * [f(x)-p(x)]^2";
            }
            
            @Override
            public double f(double x) {
                double d = func1.f(x) - func2.f(x);
                return Math.exp(-x) * d * d;
            }
        };
    }
    
    /**
     * Create approximation polynom
     */
    static Function createApproximationPolynom(double[] c, ArrayList<Function> L, double[] coefsForName) {
        return new Function() {
            @Override
            public String getName() {
                return createName(coefsForName, false);
            }

            @Override
            public double f(double x) {
                double res = 0;
                for (int i = 0; i < c.length; i++) 
                    res += c[i] * L.get(i).f(x);
                return res;
            }
        };
    }
}