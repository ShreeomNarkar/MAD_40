package com.example.nauka;

import java.io.Serializable;
import java.util.List;

public class Yacht implements Serializable {
    public String name, subtitle, capacity, description, rating;
    public int price;
    public List<String> amenities;

    public Yacht() {}
}