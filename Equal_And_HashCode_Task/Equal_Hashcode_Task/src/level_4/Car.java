package level_4;

import java.util.Objects;

public class Car {

    String plateNumber;
    String color;

    public Car(String plateNumber, String color){
        this.plateNumber=plateNumber;
        this.color=color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Car other = (Car) obj;
        return Objects.equals(plateNumber, other.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber);
    }

    @Override
    public String toString() {
        return "Car{plateNumber='" + plateNumber + "', color='" + color + "'}";
    }





}
