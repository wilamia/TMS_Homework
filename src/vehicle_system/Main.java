package vehicle_system;

import vehicle_system.garage.Garage;
import vehicle_system.vehicles.Bike;
import vehicle_system.vehicles.Car;
import vehicle_system.vehicles.Motorcycle;
import vehicle_system.vehicles.Vehicle;

import java.util.Scanner;

/*Описание: Создайте систему для управления транспортными средствами (машины, велосипеды, мотоциклы). Каждое транспортное средство должно иметь
методы для начала движения и остановки. Также должны быть методы для отображения информации о транспортном средстве.
Требования:
1. Создайте интерфейс Vehicle, который должен содержать методы:
◦ void start(): начинает движение.
◦ void stop(): останавливает движение.
◦ void displayInfo(): выводит информацию о транспортном средстве.
2. Создайте три класса, реализующих интерфейс Vehicle:
◦ Класс Car, который имеет свойство brand (марка машины).
◦ Класс Bike, который имеет свойство type (тип велосипеда).
◦ Класс Motorcycle, который имеет свойство engineCapacity (объем двигателя).
3. Создайте класс Garage, который будет хранить список транспортных средств и предоставлять методы для начала и остановки всех транспортных
средств в гараже.
4. Создайте метод main, в котором создайте несколько транспортных средств и добавьте их в гараж. Затем вызовите методы для старта и остановки.
*/
public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Garage garage = new Garage();
        editGarage(garage, scn);
    }

    public static void editGarage(Garage garage, Scanner scn) {
        boolean running = true;
        while (running) {
            System.out.println("Выберите действие, выполняемое с гаражом, где\n" +
                    "1 - Добавить новый транспорт\n" +
                    "2 - Запустить весь транспорт\n" +
                    "3 - Остановить весь транспорт\n" +
                    "4 - Запустить определенный транспорт\n" +
                    "5 - Остановить определенный транспорт\n" +
                    "6 - Посмотреть содержание гаража\n" +
                    "Другое - Выйти из программы управления гаражом\n");
            int type = scn.nextInt();
            switch (type) {
                case 1:
                    addVehicle(garage, scn);
                    break;
                case 2:
                    garage.startAll();
                    break;
                case 3:
                    garage.stopAll();
                    break;
                case 4:
                    startConcreteVehicle(garage, scn);
                    break;
                case 5:
                    stopConcreteVehicle(garage, scn);
                    break;
                case 6:
                    garage.displayInfo();
                    break;
                default:
                    running = false;
                    break;
            }
        }
    }

    public static void addVehicle(Garage garage, Scanner scn) {
        Vehicle vehicle = chooseVehicle(scn);
        garage.addVehicle(vehicle);
    }

    public static Vehicle chooseVehicle(Scanner scn) {
        System.out.println("Выберите вид транспорта, где\n" +
                "1 - велосипед\n" +
                "2 - мотоцикл\n" +
                "Другое - машина\n");
        int type = scn.nextInt();
        switch (type) {
            case 1:
                return new Bike(enterType(scn));
            case 2:
                return new Motorcycle(enterEngineCapacity(scn));
            default:
                return new Car(enterBrand(scn));
        }
    }

    public static String enterBrand(Scanner scn) {
        scn.nextLine();
        System.out.println("Введите марку автомобиля:");
        return scn.nextLine();
    }

    public static String enterType(Scanner scn) {
        scn.nextLine();
        System.out.println("Введите тип велосипеда:");
        return scn.nextLine();
    }

    public static int enterEngineCapacity(Scanner scn) {
        System.out.println("Введите емкость двигателя мотоцикла:");
        return scn.nextInt();
    }

    public static void stopConcreteVehicle(Garage garage, Scanner scn) {
        System.out.println("Напишите номер транспорта из списка для остановки:");
        garage.displayInfo();
        int id = scn.nextInt() - 1;
        garage.stopConcrete(id);
    }

    public static void startConcreteVehicle(Garage garage, Scanner scn) {
        System.out.println("Напишите номер транспорта из списка для запуска:");
        garage.displayInfo();
        int id = scn.nextInt() - 1;
        garage.startConcrete(id);
    }
}
