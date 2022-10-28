package arithmetic;

import core.Group;

public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne> {

    @Override
    public PlusOrMinusOne binaryOperation(PlusOrMinusOne one, PlusOrMinusOne other) {
       return PlusOrMinusOne.valueOf(transform(one.getMeaning()* other.getMeaning()));
    }

    @Override
    public PlusOrMinusOne inverseOf(PlusOrMinusOne t)
    {
        return PlusOrMinusOne.valueOf(transform((1/(t.getMeaning()))));
    }

    @Override
    public PlusOrMinusOne identity() {
        return PlusOrMinusOne.ONE;
    }

    @Override
    public PlusOrMinusOne exponent(PlusOrMinusOne t, int k) {
        return Group.super.exponent(t, k);
    }

    private String transform(int x)
    {
        if(x==-1)
        {
            return "MINUS_ONE";
        }
        return "ONE";
    }
}
