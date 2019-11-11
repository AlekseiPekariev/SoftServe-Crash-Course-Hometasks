import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HomeCat homeCat1 = new HomeCat("HomeCatName1", "CatBreed2", "mastername");
        homeCat1.layOnSofa();
        homeCat1.spoilShoes();
        homeCat1.play("Laser point");

        HomeCat homeCat2 = new HomeCat("HomeCatName2", "CatBreed2", "mastername");
        homeCat2.layOnSofa();
        homeCat2.spoilShoes();
        homeCat2.play("ball");

        HomeCat homeCat3 = new HomeCat("HomeCatName3", "CatBreed2", "mastername");
        homeCat3.layOnSofa();
        homeCat3.spoilShoes();
        homeCat3.play("Master");

        WildCat wildCat1 = new WildCat("WildCatName1", "CatBreed2");
        wildCat1.hatePeople();
        wildCat1.play();

        WildCat wildCat2 = new WildCat("WildCatName2", "CatBreed2");
        wildCat2.hatePeople();
        wildCat2.play();

        List<Cat> listOfCats = new ArrayList<>(Arrays.asList(homeCat1, homeCat2, homeCat3, wildCat1, wildCat2));
        for (Cat nextCat : listOfCats) {
            System.out.println("Cat name is: " + nextCat.getName() + ". Cat breed is: " + nextCat.getBreed() + ".");

        }
        System.out.println("Amount of wool on the sofa: " + Master.amountOfWool);
        System.out.println("Amount of money on the shoes: " + Master.moneyOnShoes);
        System.out.println("Amount of cat enemies on the streets: " + Master.catEnemiesAmount);


    }


}
