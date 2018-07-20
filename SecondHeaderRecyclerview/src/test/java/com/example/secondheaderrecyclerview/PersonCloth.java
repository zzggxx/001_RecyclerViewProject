package com.example.secondheaderrecyclerview;

public abstract class PersonCloth extends Person {

    private Person mPerson;

    public PersonCloth(Person person) {
        mPerson = person;
    }

    @Override
    public void dressed() {
        mPerson.dressed();
    }
}
