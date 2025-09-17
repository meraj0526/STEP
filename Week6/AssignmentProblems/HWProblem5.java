class BasicMath {
    public int calculate(int a, int b) {
        return a + b;
    }

    public double calculate(double a, double b) {
        return a + b;
    }

    public int calculate(int a, int b, int c) {
        return a + b + c;
    }
}

class AdvancedMath extends BasicMath {
    public int calculate(int a, int b, int c, int d) {
        return a + b + c + d;
    }

    public double calculate(double a, double b, double c) {
        return a * b * c;
    }

    public double calculate(int a, double b) {
        return a - b;
    }
}

public class HWProblem5 {
    public static void main(String[] args) {
        BasicMath bm = new BasicMath();
        System.out.println("BasicMath (int,int): " + bm.calculate(5, 3));
        System.out.println("BasicMath (double,double): " + bm.calculate(2.5, 3.5));
        System.out.println("BasicMath (int,int,int): " + bm.calculate(1, 2, 3));

        AdvancedMath am = new AdvancedMath();
        System.out.println("AdvancedMath (int,int,int,int): " + am.calculate(1, 2, 3, 4));
        System.out.println("AdvancedMath (double,double,double): " + am.calculate(2.0, 3.0, 4.0));
        System.out.println("AdvancedMath (int,double): " + am.calculate(10, 4.5));
    }
}
