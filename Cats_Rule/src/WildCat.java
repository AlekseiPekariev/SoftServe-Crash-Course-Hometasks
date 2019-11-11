public class WildCat extends Cat implements Playable {

    public WildCat(String name, String breed) {
        this.setName(name);
        this.setBreed(breed);
        Master.catEnemiesAmount++;
    }

    public void hatePeople() {
        System.out.println("Human, my name is " + this.getName() + ", keep your neck covered!..");
    }

    @Override
    public void play(String toy) {
        System.out.println("Wild cat plays with " + toy);

    }

    @Override
    public void play() {
        System.out.println("WildCats never plays...");

    }
}
