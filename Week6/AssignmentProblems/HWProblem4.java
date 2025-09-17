abstract class Food {
    public final void prepare() {
        wash();
        cook();
        serve();
    }

    protected abstract void wash();
    protected abstract void cook();
    protected abstract void serve();
}

class Pizza extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing vegetables and dough ingredients.");
    }

    @Override
    protected void cook() {
        System.out.println("Baking pizza in the oven.");
    }

    @Override
    protected void serve() {
        System.out.println("Serving pizza with cheese toppings.");
    }
}

class Soup extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing vegetables for soup.");
    }

    @Override
    protected void cook() {
        System.out.println("Boiling vegetables to prepare soup.");
    }

    @Override
    protected void serve() {
        System.out.println("Serving hot soup in a bowl.");
    }
}

public class HWProblem4 {
    public static void main(String[] args) {
        Food f1 = new Pizza();
        System.out.println("Preparing Pizza:");
        f1.prepare();

        System.out.println("\nPreparing Soup:");
        Food f2 = new Soup();
        f2.prepare();
    }
}
