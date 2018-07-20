package com.example.secondheaderrecyclerview;

public class ExpensiveCloth extends PersonCloth {

    public ExpensiveCloth(Person person) {
        super(person);
    }

    private void dressedLeather() {
        System.out.println("穿皮衣");
    }

    private void dressedJean() {
        System.out.println("穿牛仔裤");
    }

    @Override
    public void dressed() {
        super.dressed();
        dressedLeather();
        dressedJean();
    }
}
