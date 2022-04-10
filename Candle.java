class Candle implements Thing {
    private final int state;

    private static final int FLICK = 0;
    private static final int SHORTER = 1;
    private static final int BURNING = 2;

    Candle(int state) {
        this.state = state;
    }

    Candle() {
        this(0);
    }

    public String identify() {
        return "Candle";
    }

    public Candle tick() {
        return new Candle(this.state + 1);
    }

    /*public int getState() {
        return this.state;
    }*/

    public String toString() {
        switch (state) {
            case 0:
                return "Candle flickers.";
            case 1:
                return "Candle is getting shorter.";
            case 2:
                return "Candle is about to burn out.";
            default:
                return "Candle has burned out.";
        }
    }

}
