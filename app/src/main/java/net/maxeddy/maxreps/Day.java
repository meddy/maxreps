package net.maxeddy.maxreps;

public class Day {
    private int number;
    private String workout;

    public Day(int number, String workout) {
        this.number = number;
        this.workout = workout;
    }

    public String getLabel() {
        return "Day " + this.number;
    }
}
