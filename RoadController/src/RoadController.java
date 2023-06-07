import core.*;
import core.Camera;

import java.util.Scanner;

public class RoadController {
    private static double passengerCarMaxWeight = 3500.0; // kg
    private static int passengerCarMaxHeight = 2000; // mm
    private static int controllerMaxHeight = 4000; // mm

    private static int passengerCarPrice = 50; // DOL
    private static int cargoCarPrice = 75; // DOL
    private static int vehicleAdditionalPrice = 60; // DOL

    public static void main(String[] args) {
        System.out.println("How many cars generation?");
        Scanner scanner = new Scanner(System.in);
        int carsCount = scanner.nextInt();

        for (int i = 0; i < carsCount; i++) {
            Car car = Camera.getNextCar();
            System.out.println(car);

            //Skip free
            if (car.isSpecial) {
                openWay();
                continue;
            }

            //Search height and weight and how much is the fare
            int price = calculatePrice(car);
            if (price == -1) {
                continue;
            }

            System.out.println("total amount due: " + price + " dollars");
        }
    }

    /**
     * Fare calculation based on weight and height
     */
    private static int calculatePrice(Car car) {
        int carHeight = car.height;
        int price = 0;
        if (carHeight > controllerMaxHeight) {
            blockWay("the height of your vehicle exceeds the height of the checkpoint");
            return -1;
        } else if (carHeight > passengerCarMaxHeight) {
            double weight = car.weight;
            //Truck car
            if (weight > passengerCarMaxWeight) {
                price = passengerCarPrice;
                if (car.hasVehicle) {
                    price = price + vehicleAdditionalPrice;
                }
            }
            //Usual car
            else {
                price = cargoCarPrice;
            }
        } else {
            price = passengerCarPrice;
        }
        return price;
    }

    /**
     Opening
     */
    private static void openWay() {
        System.out.println("Barrier open... Good luck!");
    }

    /**
     Impossibility message
     */
    private static void blockWay(String reason) {
        System.out.println("entry is impossible: " + reason);
    }
}