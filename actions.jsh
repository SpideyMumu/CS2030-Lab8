    Function<Room, Room> takeSword = x -> {
        if (!x.hasSword()) {
            System.out.println("--> There is no sword.");    
        } else {
            if (x.getSword()) {
                System.out.println("--> You already have sword.");
            } else {
                System.out.println("--> You have taken sword.");    
            }   
        } return x.takeSword();
    };

    Function<Room, Room> killTroll = x -> {
        if (!x.getThings().stream().anyMatch(y -> y.identify().equals("Troll"))) {
            System.out.println("--> There is no troll.");
        } else if (!x.getSword())  {
            System.out.println("--> You have no sword.");
        } else { 
            System.out.println("--> Troll is killed."); 
            return x.killTroll();
        }   
        return x;
    };

    Function<Room, Room> dropSword = x -> {
        System.out.println("--> You have dropped sword.");    
        return x.dropSword();
    }
