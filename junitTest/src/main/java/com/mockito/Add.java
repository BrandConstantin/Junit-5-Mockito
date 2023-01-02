package com.mockito;

public class Add {
    private ValidNumber validNumber;

    public Add(ValidNumber valid) {
        this.validNumber = valid;
    }

    public int add(Object a, Object b) {
        if (validNumber.check(a) && validNumber.check(b)) {
            return (Integer) a + (Integer) b;
        }

        return 0;
    }
}
