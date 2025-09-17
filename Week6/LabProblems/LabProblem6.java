class Box {
    public void pack() {
        System.out.println("Box is being packed.");
    }

    public void unpack() {
        System.out.println("Box is being unpacked.");
    }
}

class GiftBox extends Box {
    @Override
    public void pack() {
        super.pack();  
        System.out.println("Gift wrapping the box with decorative paper and ribbon.");
    }

    @Override
    public void unpack() {
        super.unpack();  
        System.out.println("Unwrapping the gift paper before opening the box.");
    }
}

public class LabProblem6 {
    public static void main(String[] args) {
        System.out.println("Testing normal Box:");
        Box b = new Box();
        b.pack();
        b.unpack();

        System.out.println("\nTesting GiftBox:");
        GiftBox gb = new GiftBox();
        gb.pack();
        gb.unpack();
    }
}
