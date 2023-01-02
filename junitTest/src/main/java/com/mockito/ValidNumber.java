package com.mockito;

public class ValidNumber {
    public ValidNumber() {
    }

    public boolean check(Object o) {
        if (o instanceof Integer) {
            if ((Integer) o >= 0) {
                return true;
            }
        }

        return false;
    }

    public boolean checkZero(Object o) {
        if (o instanceof Integer) {
            if ((Integer) o == 0) {
                throw new ArithmeticException("No podemos aceptar un cero");
            } else {
                return true;
            }
        }

        return false;
    }

    public int doubleToInt(Object o) {
        if (o instanceof Double) {
            return ((Double) o).intValue();
        }

        return 0;
    }
}
