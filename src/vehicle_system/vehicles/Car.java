package vehicle_system.vehicles;

public class Car implements Vehicle {
    private String brand;
    private boolean isInAction = false;

    public Car(String brand) {
        this.brand = brand;
    }

    @Override
    public void start() {
        if (!isInAction) {
            System.out.println("Машина " + brand + " начала движение!");
            isInAction = true;
        } else {
            System.out.println("Машина " + brand + " уже движется!");
        }
    }

    @Override
    public void stop() {
        if (isInAction) {
            System.out.println("Машина " + brand + " закончила движение!");
            isInAction = false;
        } else {
            System.out.println("Машина " + brand + " уже закончила движение!");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Тип транспорта: машина. Марка машины: " + brand);
    }
}
