package com.example.netactivity;

public class Entry {
    public final String kod;
    public String country;
    public String rate;
    public String quantity;
            
    Entry(String kod) {
        this.kod = kod;
    }

    Entry(String kod, String country, String rate, String quantity) {
        this.kod = kod;
        this.country = country;
        this.rate = rate;
        this.quantity = quantity;
    }
}
