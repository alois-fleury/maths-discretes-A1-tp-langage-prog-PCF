package interp;

public class IntVal implements Value {
    public final int value;
    public IntVal(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}