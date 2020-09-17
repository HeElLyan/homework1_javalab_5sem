package ru.he.services;

import org.apache.commons.lang3.SerializationUtils;
import ru.he.models.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReaderService {

    public byte[] readClient() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Client client = new Client();

            System.out.println("=========================================");

            System.out.print("First name: ");
            client.setName(reader.readLine());

            System.out.print("Last name: ");
            client.setLastName(reader.readLine());

            System.out.print("Passport number: ");
            client.setPassportNumber(reader.readLine());

            System.out.print("Date of issue: ");
            client.setPassportDate(reader.readLine());

            System.out.print("Age: ");
            client.setAge(reader.readLine());

            System.out.println("===========================================");

            System.out.println(client.toString());

            return SerializationUtils.serialize(client);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
