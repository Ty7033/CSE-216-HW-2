package arithmetic;

public enum PlusOrMinusOne {
    ONE(1), MINUS_ONE(-1);

    private final int meaning;

    PlusOrMinusOne(int meaning)
    {
        this.meaning=meaning;
    }

    public int getMeaning() {
        return meaning;
    }

    public String toString()
    {
         switch(this)
         {
            case ONE:return "1";
            case MINUS_ONE: return "-1";
            default: throw new IllegalArgumentException();
         }

    }
}
