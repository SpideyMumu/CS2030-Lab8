class Troll implements Thing {
    private final int state;

    private static final int LURK = 0;
    private static final int HUNGRY = 1;
    private static final int V_HUNGRY = 2;
    private static final int SUP_HUNGRY = 3;
    
    Troll(int state) {
        this.state = state;
    }

    Troll() {
        this(0);
    }

    public String identify() {
        return "Troll";
    }

    public Troll tick() {
        return new Troll(this.state + 1);
    }

    /*public int getState() {
        return this.state;
    }*/

    public String toString() {
        switch (state) {
            case LURK:
                return "Troll lurks in the shadows.";
            case HUNGRY:
                return "Troll is getting hungry.";
            case V_HUNGRY:
                return "Troll is VERY hungry.";
            case SUP_HUNGRY:
                return "Troll is SUPER HUNGRY and is about to ATTACK!";
            default:
                return "Troll attacks!";
        }
    }
}
