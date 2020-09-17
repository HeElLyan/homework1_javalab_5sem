package ru.he.models;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Client implements Serializable {

    public Client(){
        this.id = UUID.randomUUID();
    }

    private UUID id;
    private String name;
    private String lastName;
    private String passportNumber;
    private String passportDate;
    private String age;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportDate='" + passportDate + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
