package com.example.myapplication;

public class Room {
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private boolean isAvailable;

    public Room(String name, String description, String imageUrl, double price, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Matches filter criteria
    public boolean matchesFilter(String availability, double minPrice, double maxPrice) {
        boolean matchesAvailability = availability.equals("All") ||
                (availability.equals("Available") && isAvailable) ||
                (availability.equals("Not Available") && !isAvailable);
        boolean matchesPrice = price >= minPrice && price <= maxPrice;

        return matchesAvailability && matchesPrice;
    }

    // Override toString for debugging/logging
    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
