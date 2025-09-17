class Weather {
    String type;

    Weather(String type) {
        this.type = type;
        System.out.println("Weather constructor: " + type);
    }

    void showWeather() {
        System.out.println("General weather: " + type);
    }
}

class Storm extends Weather {
    Storm() {
        super("Storm");
        System.out.println("Storm constructor");
    }

    @Override
    void showWeather() {
        System.out.println("It is stormy!");
    }
}

class Thunderstorm extends Storm {
    Thunderstorm() {
        super();
        System.out.println("Thunderstorm constructor");
    }

    @Override
    void showWeather() {
        System.out.println("It is a thunderstorm with lightning!");
    }
}

class Sunshine extends Weather {
    Sunshine() {
        super("Sunshine");
        System.out.println("Sunshine constructor");
    }

    @Override
    void showWeather() {
        System.out.println("It is sunny and bright!");
    }
}

public class WeatherTest {
    public static void main(String[] args) {
        Weather[] weathers = new Weather[3];
        weathers[0] = new Storm();
        weathers[1] = new Thunderstorm();
        weathers[2] = new Sunshine();

        System.out.println("\n--- Polymorphic Behavior ---");
        for (Weather w : weathers) {
            w.showWeather();
        }
    }
}
