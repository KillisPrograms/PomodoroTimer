package net.justkilli.pomodorotimer.model;

public record WorkCategory(int id, String name, String description) {

    @Override
    public String toString() {
        return String.format("%d. %s     |   %s", id, name, description);
    }

}
