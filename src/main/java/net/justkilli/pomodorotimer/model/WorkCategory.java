package net.justkilli.pomodorotimer.model;

/**
 * The WorkCategory class represents a category of work.
 * It encapsulates the category's ID, name, and description.
 */
public record WorkCategory(int id, String name, String description) {

    @Override
    public String toString() {
        return String.format("%d. %s     |   %s", id, name, description);
    }

}
