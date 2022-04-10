import java.util.function.Function;
import java.util.Optional;

class Room {
    private final ImList<Thing> things;
    private final String name;
    private final boolean swordTaken;
    private final Optional<Room> prevRoom;

    private Room(String name, ImList<Thing> things, boolean s, Optional<Room> room) {
        this.name = name;
        this.things = things;
        this.swordTaken = s;
        this.prevRoom = room;
    }

    Room(String name) {
        this(name, ImList.<Thing>of(), false, Optional.empty());
    }

    Room(String name, Room room) {
        this(name, room.getThings(), room.getSword(), Optional.<Room>of(room));
    } 

    Room add(Thing thing) {
        return new Room(this.name, this.things.add(thing), this.swordTaken, this.prevRoom);
    }

    Room tick() {
        return new Room(this.name, things.map(x -> x.tick()), this.swordTaken, this.prevRoom);
    }

    Room tick(Function<Room, Room> function) {
        return function.apply(this).tick();
    }

    boolean hasSword() {
        return this.things.stream()
            .anyMatch(x -> x.identify().equals("Sword"));
    }
  
    Room takeSword() {
        if (this.hasSword()) {
            return new Room(this.name, this.things, true, this.prevRoom);
        }
        return this;
    }

    Room killTroll() {
        return new Room(this.name, 
                this.things.filter(x -> !x.identify().equals("Troll")),
                this.swordTaken, this.prevRoom);
    }

    Room dropSword() {
        return new Room(this.name, this.things,
                    false, this.prevRoom);
    }
    
    private Room removeSword() {
        return new Room(this.name, 
                this.things.filter(x -> !x.identify().equals("Sword")),
                false, this.prevRoom);
    }

    Room go(Function<Room, Room> f) {
        Room nextRoom = f.apply(this); 
        if (this.swordTaken) {
            ImList<Thing> newList = ImList.<Thing>of();
            // add new items to ImList with sword
            if (nextRoom.hasSword()) {
                newList = newList.addAll(nextRoom.getThings());
            } else {
                newList = newList.add(new Sword()).addAll(nextRoom.getThings());
            }
            return new Room(nextRoom.getName(), newList, 
                    this.getSword(), Optional.<Room>of(this.removeSword()));
            // need to change to prevRoom without sword
        }
        return new Room(nextRoom.getName(), nextRoom.getThings(), 
                nextRoom.getSword(), Optional.<Room>of(this));
    }
    
    Room back() {
        if (this.swordTaken) {
            return this.prevRoom.orElse(this).add(new Sword()).takeSword().tick();
        } else if (this.prevRoom.orElse(this) != this) {
            return this.prevRoom.orElse(this).tick();
        }
        return this;
    }

    boolean getSword() {
        return this.swordTaken;
    }

    ImList<Thing> getThings() {
        return this.things;
    }

    private String getName() {
        return this.name;
    }

    public String toString() {
        return this.things.map(x -> "\n" + x)
            .reduce("@" + this.name, (thing1, thing2) -> thing1 + thing2);
    }

}
