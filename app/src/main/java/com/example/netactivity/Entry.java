package com.example.netactivity;

public class Entry {
    public final String kod;
    public String country;
    public String rate;
    public String quantity;
 
    // TODO 3. Rozsirit dalsi udaje ve tride, ktere se budou vest pro kazdou menu
    // TODO 3. To zahrnuje i upraveni konstruktoru
            
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
