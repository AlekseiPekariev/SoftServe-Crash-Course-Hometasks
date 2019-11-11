public class HomeCat extends Cat implements Playable {
    private String master;

    public HomeCat(String name, String breed, String master) {
        this.setName(name);
        this.setBreed(breed);
        this.master = master;
    }

    public void layOnSofa() {
        Master.amountOfWool++;

    }

    public void spoilShoes() {
        Master.moneyOnShoes -= 1000;
    }


    @Override
    public void play(String toy) {
        System.out.println("Home cat plays with " + toy);
    }

    @Override
    public void play() {
        System.out.println("Home is just running around the flat");

    }
}
