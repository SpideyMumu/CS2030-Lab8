class Sword implements Thing {
    /*private final int state;

    Sword(int state) {
        this.state = state;
    }

    Sword() {
        this(0);
    }

    public int getState() {
        return this.state;
    }*/

    public String identify() {
        return "Sword";
    }

    public Sword tick() {
        return this; //new Sword(this.state + 1);
    }

    public String toString() {
        return "Sword is shimmering.";
    }
}
