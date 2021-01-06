import java.nio.charset.StandardCharsets;
import java.util.*;

class AsciiCharSequence implements CharSequence {

    private final byte[] symbols;

    AsciiCharSequence(byte[] symbols) {
        this(symbols, 0, symbols.length);
    }

    AsciiCharSequence(byte[] symbols, int fromInclusive, int toExclusive) {
        this.symbols = Arrays.copyOfRange(symbols, fromInclusive, toExclusive);
    }

    @Override
    public int length() {
        return symbols.length;
    }

    @Override
    public char charAt(int index) {
        return (char) symbols[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(symbols, start, end);
    }

    @Override
    public String toString() {
        return new String(symbols, StandardCharsets.US_ASCII);
    }
}