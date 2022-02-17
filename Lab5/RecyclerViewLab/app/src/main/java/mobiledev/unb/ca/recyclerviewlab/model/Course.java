package mobiledev.unb.ca.recyclerviewlab.model;

import androidx.annotation.NonNull;

public class Course {

    private final String id;
    private final String name;
    private final String description;

    private Course (Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    // Only need to include getters
    public String getTitle() {
        return id + ": " + name;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {
        private final String id;
        private final String name;
        private final String description;

        public Builder(@NonNull String id, @NonNull String name, @NonNull String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public Course build() {
            return new Course(this);
        }
    }
}
