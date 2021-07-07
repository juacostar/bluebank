package com.example.bluebank.model;


import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @Column(name = "number")
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Double value;

    public Account() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String generateNumber(){
        String n = "";
            for(int i=0; i<10; i++){
                n += Math.round(Math.random()*10);
        }
        return n;
    }
}
