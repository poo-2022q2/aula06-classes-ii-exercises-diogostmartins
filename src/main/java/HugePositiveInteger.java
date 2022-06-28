import java.util.Arrays;

public class HugePositiveInteger {
    private final char[] digits;

    public HugePositiveInteger() {
        digits = new char[] { '0'};
    }

    public HugePositiveInteger(long value) {
        digits = Long.valueOf(value).toString().toCharArray();
    }

    public HugePositiveInteger(HugePositiveInteger other) {
        digits = Arrays.copyOf(other.digits, 
        other.digits.length);
    }

    public HugePositiveInteger(String value) {
        for (var i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                throw new IllegalArgumentException(
                    String.format("%s is not a digit", 
                    value.charAt(i)));
            }
        }

        digits = trimLeftZeroes(value.toCharArray());
    }

    private char[] trimLeftZeroes(char[] digits) {
        int i = 0;

        while (i < digits.length - 1 && digits[i] == '0') {
            i++;
        }

        return Arrays.copyOfRange(digits, i, digits.length);
    } 

    @Override
    public String toString() {
        var builder = new StringBuilder();

        for (var i = 0; i < digits.length; i++) {
            builder.append(digits[i]);
        }

        return builder.toString();
    }

    public int size() {
        return digits.length;
    }

    public int compareTo(HugePositiveInteger other) {
        // TODO
        // this > other -> 1
        // this < other -> -1
        // this == other -> 0

        if (this.size() > other.size()) {
            return 1;
        }
        if (this.size() < other.size()) {
            return -1;
        }

        for (var i = 0; i < this.size(); i++) {
            if (this.digits[i] > other.digits[i]) {
                return 1;
            }
            if (this.digits[i] < other.digits[i]) {
                return -1;
            }
        }


        return 0;
    }

    public HugePositiveInteger plus(HugePositiveInteger other) {
        // TODO
        return new HugePositiveInteger();
    }

    public HugePositiveInteger minus(HugePositiveInteger other) {
        // TODO
        return new HugePositiveInteger();
    }

    public HugePositiveInteger ninesComplement() {
        // TODO
        return new HugePositiveInteger();
    }

    public static void main(String[] args) {
        var a = new HugePositiveInteger("00001234");
        var b = new HugePositiveInteger("000000");

        var c = new HugePositiveInteger(12);
        var d = new HugePositiveInteger(123);


        System.out.println(a);
        System.out.println(b);

        System.out.println();

        System.out.println(c.compareTo(d));
    }


}
