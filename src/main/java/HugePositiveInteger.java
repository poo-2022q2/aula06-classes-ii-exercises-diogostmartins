import java.util.Arrays;

public class HugePositiveInteger {
    private final char[] digits;

    public HugePositiveInteger() {
        this.digits = new char[] {'0'};
    }

    public HugePositiveInteger(long value) {
        this.digits = Long.toString(value).toCharArray();
    }

    public HugePositiveInteger(String value) {
        for (var i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                throw new IllegalArgumentException(
                    String.format("%s is not a digit", value.charAt(i)));
            }
        }
        this.digits = trimLeftZeroes(value.toCharArray());
    }

    public HugePositiveInteger(char[] value) {
        for (var i = 0; i < value.length; i++) {
            if (!Character.isDigit(value[i])) {
                throw new IllegalArgumentException(
                    String.format("%s is not a digit", value[i]));
            }
        }
        this.digits = trimLeftZeroes(value);
    }

    private char[] trimLeftZeroes(char[] digits) {
        var i = 0;

        while (i < digits.length - 1 && digits[i] == '0') {
            i++;
        }

        if (i > 0) {
            return Arrays.copyOfRange(digits, i, digits.length);
        }

        return digits;

    }

    public HugePositiveInteger(HugePositiveInteger other) {
        this.digits = Arrays.copyOf(other.digits, other.digits.length);
    }

    public int size() {
        return this.digits.length;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();

        for (var d : digits) {
            builder.append(d);
        }

        return builder.toString();
    }

    public int compareTo(HugePositiveInteger other) {
        if (this.digits.length > other.size()) {
            return 1;
        } else if (this.digits.length < other.size()) {
            return -1;
        } else {
            for (int i = 0; i < digits.length; i++) {
                var a = Character.digit(this.digits[i], 10);
                var b = Character.digit(other.digits[i], 10);

                if (a > b) {
                    return 1;
                } else if (a < b) {
                    return -1;
                }
            }
        }

        return 0;
    }

    public HugePositiveInteger plus(HugePositiveInteger other) {
        return new HugePositiveInteger(plus(this.digits, other.digits));
    }

    /**
     * TODO: it is working only for numbers with the same
     * quantity of digits. Generalize to numbers with 
     * different quantity of digits.
     * @param a
     * @param b
     * @return
     */
    private char[] plus(char[] a, char[] b) {
        var carry = 0;
        var c = new char[Math.max(a.length, b.length)];
        var ai = a.length - 1;
        var bi = b.length - 1;
        var ci = c.length - 1;

        Arrays.fill(c, '0');

        while (ai >= 0 && bi >= 0) {
            var sum = Character.digit(a[ai], 10)
             + Character.digit(b[bi], 10) + carry;
            
            c[ci] = Character.forDigit(sum % 10, 10);
            carry = sum / 10;

            ai--;
            bi--;
            ci--;
        }

        if (carry > 0) {
            var d = new char[c.length + 1];

            d[0] = Character.forDigit(carry, 10);

            for (var i = 1; i < d.length; i++) {
                d[i] = c[i-1];
            }

            return d;
        }

        return c;

    }

    public HugePositiveInteger minus(HugePositiveInteger other) {
        return new HugePositiveInteger();
    }

    public HugePositiveInteger ninesComplement() {
        return new HugePositiveInteger();
    }

    public static void main(String[] args) {
        // var a = new HugePositiveInteger(12345);
        // var b = new HugePositiveInteger("48938393894849");
        // var c = new HugePositiveInteger("000000000048938393894849");
        // var d = new HugePositiveInteger("000000000000000");

        // System.out.println(a);
        // System.out.println(b);
        // System.out.println(c);
        // System.out.println(d);

        // System.out.println();


        // var e = new HugePositiveInteger(1234);
        // var f = new HugePositiveInteger(1234);
        // var g = new HugePositiveInteger(1233);
        // var h = new HugePositiveInteger(233);
        // var i = new HugePositiveInteger(1244);
        // var j = new HugePositiveInteger(12445);

        // System.out.println(e.compareTo(f));
        // System.out.println(e.compareTo(g));
        // System.out.println(e.compareTo(h));
        // System.out.println(e.compareTo(i));
        // System.out.println(e.compareTo(j));

        var a = new HugePositiveInteger("9999");
        var b = new HugePositiveInteger("9999");

        var c = new HugePositiveInteger("1234");
        var d = new HugePositiveInteger("1111");

        System.out.println(a.plus(b));
        System.out.println(c.plus(d));

    }
    
}
