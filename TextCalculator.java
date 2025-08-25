import java.util.*;

public class TextCalculator {

    // ==== (b) Validate expression using ASCII + structure checks ====
    public static boolean isValidExpression(String expr) {
        if (expr == null || expr.trim().isEmpty()) return false;

        // 1) Character whitelist: digits(48-57) + - * / space(32) ( ) 
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            int a = (int) c;
            boolean isDigit = (a >= 48 && a <= 57);
            boolean ok = isDigit || c == '+' || c == '-' || c == '*' || c == '/' || c == ' ' || c == '(' || c == ')';
            if (!ok) return false;
        }

        // 2) Parentheses matching
        int bal = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') bal++;
            if (c == ')') {
                bal--;
                if (bal < 0) return false;
            }
        }
        if (bal != 0) return false;

        // 3) Operator placement (simple rule set; unary minus not supported)
        String compact = removeSpaces(expr);
        if (compact.isEmpty()) return false;

        // cannot start or end with +,*,/ or -
        char first = compact.charAt(0), last = compact.charAt(compact.length() - 1);
        if (isOp(first) || isOp(last)) return false;

        // no two operators adjacent and no ")(" adjacency, and no digit immediately followed by "(" (no implicit multiplication)
        for (int i = 0; i < compact.length() - 1; i++) {
            char c = compact.charAt(i);
            char n = compact.charAt(i + 1);
            if (isOp(c) && isOp(n)) return false;
            if (c == ')' && n == '(') return false;
            if (isDigit(c) && n == '(') return false; // disallow implicit multiply like 2(3+4)
            if (c == ')' && isDigit(n)) return false; // disallow (2)3
        }
        return true;
    }

    private static boolean isOp(char c) { return c == '+' || c == '-' || c == '*' || c == '/'; }
    private static boolean isDigit(char c) { return c >= '0' && c <= '9'; }
    private static String removeSpaces(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) if (s.charAt(i) != ' ') sb.append(s.charAt(i));
        return sb.toString();
    }

    // ==== (c) Parse numbers/operators without split(); store in arrays ====
    // Works for expressions WITHOUT parentheses (use after parentheses are resolved)
    public static Parsed parseSimple(String exprNoParens) {
        ArrayList<Integer> nums = new ArrayList<>();
        ArrayList<Character> ops = new ArrayList<>();

        String s = exprNoParens;
        int i = 0, n = s.length();
        while (i < n) {
            // skip spaces
            while (i < n && s.charAt(i) == ' ') i++;
            if (i >= n) break;

            // read number (multi-digit)
            if (!isDigit(s.charAt(i))) throw new IllegalArgumentException("Expected digit at position " + i);
            int start = i;
            while (i < n && isDigit(s.charAt(i))) i++;
            int val = Integer.parseInt(s.substring(start, i));
            nums.add(val);

            // skip spaces
            while (i < n && s.charAt(i) == ' ') i++;
            if (i < n) {
                char c = s.charAt(i);
                if (isOp(c)) {
                    ops.add(c);
                    i++;
                } else {
                    // anything else is invalid here
                    throw new IllegalArgumentException("Expected operator at position " + i);
                }
            }
        }
        return new Parsed(nums, ops);
    }

    // ==== (d) Evaluate with operator precedence (*, / first), left-to-right ties, log steps ====
    public static int evaluateSimple(String exprNoParens, StringBuilder steps) {
        Parsed p = parseSimple(exprNoParens);
        ArrayList<Integer> nums = p.nums;
        ArrayList<Character> ops = p.ops;

        // helper to stringify current simple expression
        Runnable logState = () -> steps.append("   => ").append(render(nums, ops)).append('\n');

        // First pass: * and /
        for (int i = 0; i < ops.size();) {
            char op = ops.get(i);
            if (op == '*' || op == '/') {
                int a = nums.get(i);
                int b = nums.get(i + 1);
                int r;
                if (op == '/') {
                    if (b == 0) throw new ArithmeticException("Division by zero");
                    r = a / b;
                    steps.append("Compute ").append(a).append(" / ").append(b).append(" = ").append(r).append('\n');
                } else {
                    r = a * b;
                    steps.append("Compute ").append(a).append(" * ").append(b).append(" = ").append(r).append('\n');
                }
                // replace a,b with r; remove op
                nums.set(i, r);
                nums.remove(i + 1);
                ops.remove(i);
                logState.run();
            } else {
                i++;
            }
        }

        // Second pass: + and -
        while (!ops.isEmpty()) {
            int a = nums.get(0);
            int b = nums.get(1);
            char op = ops.get(0);
            int r = (op == '+') ? (a + b) : (a - b);
            steps.append("Compute ").append(a).append(' ').append(op).append(' ').append(b)
                 .append(" = ").append(r).append('\n');

            nums.set(0, r);
            nums.remove(1);
            ops.remove(0);
            steps.append("   => ").append(render(nums, ops)).append('\n');
        }

        return nums.get(0);
    }

    private static String render(List<Integer> nums, List<Character> ops) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.size(); i++) {
            sb.append(nums.get(i));
            if (i < ops.size()) sb.append(' ').append(ops.get(i)).append(' ');
        }
        return sb.toString();
    }

    // ==== (e) Handle parentheses (evaluate innermost first) ====
    public static int evaluateWithParentheses(String expr, StringBuilder steps) {
        String cur = expr;
        steps.append("Original: ").append(expr).append('\n');

        while (cur.indexOf('(') != -1) {
            int l = cur.lastIndexOf('(');
            int r = cur.indexOf(')', l);
            if (r == -1) throw new IllegalArgumentException("Mismatched parentheses");

            String inside = cur.substring(l + 1, r);
            steps.append("Evaluate inner (").append(inside).append(")\n");
            int inner = evaluateSimple(inside, steps);

            // replace parentheses with the result
            String before = cur.substring(0, l);
            String after = cur.substring(r + 1);
            cur = before + inner + after;
            steps.append("Replace (").append(inside).append(") -> ").append(inner).append('\n');
            steps.append("   => ").append(cur).append('\n');
        }

        // No parentheses left
        steps.append("Evaluate remaining: ").append(cur).append('\n');
        return evaluateSimple(cur, steps);
    }

    // ==== (f) Display steps ====
    public static void displayProcess(String expression, boolean valid, int result, String steps) {
        System.out.println("==============================================");
        System.out.println("Expression : " + expression);
        System.out.println("Valid      : " + valid);
        System.out.println("---- Steps ----");
        System.out.print(steps);
        if (valid) {
            System.out.println("Final Result: " + result);
        } else {
            System.out.println("Final Result: (invalid expression)");
        }
        System.out.println("==============================================\n");
    }

    // ==== (g) Main: process multiple expressions ====
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many expressions? ");
        int n = Integer.parseInt(sc.nextLine());

        for (int k = 1; k <= n; k++) {
            System.out.print("Enter expression " + k + ": ");
            String expr = sc.nextLine();

            boolean valid = isValidExpression(expr);
            StringBuilder steps = new StringBuilder();

            if (!valid) {
                displayProcess(expr, false, 0, "Validation failed.\n");
                continue;
            }

            try {
                int ans = evaluateWithParentheses(expr, steps);
                displayProcess(expr, true, ans, steps.toString());
            } catch (Exception ex) {
                displayProcess(expr, false, 0, "Error: " + ex.getMessage() + "\n");
            }
        }
        sc.close();
    }

    // ==== helper: parsed structure ====
    static class Parsed {
        ArrayList<Integer> nums;
        ArrayList<Character> ops;
        Parsed(ArrayList<Integer> n, ArrayList<Character> o) { this.nums = n; this.ops = o; }
    }
}
