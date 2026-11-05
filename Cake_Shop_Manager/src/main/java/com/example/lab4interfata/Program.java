package com.example.lab4interfata;

import domain.Comanda;
import domain.Tort;
import repository.*;
import service.ServiceComanda;
import service.ServiceTort;
import ui.Console;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;


public class Program {
    public static void main(String[] args) throws IOException, RepositoryException, ParseException {


        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream("src/main/java/Properties")){
            properties.load(input);
        }catch (IOException e){
            System.out.println(e.getMessage());
            return;
        }

        String repositoryType1 = properties.getProperty("Repository1");
        String repositoryType2 = properties.getProperty("Repository2");


     //   SQLTortRepository tortRepository = new SQLTortRepository();
      //  SQLComandaRepository comandaRepository = new SQLComandaRepository();


        IRepository<Tort> tortRepository ;
        IRepository<Comanda> comandaRepository;

        switch (repositoryType1.toLowerCase())
        {
            case "sql":
                tortRepository = new SQLTortRepository();
                break;
            case "memory":
                tortRepository = new Repository<Tort>();
                break;
            default:
                System.out.println("Invalid Repository");
                return;
        }


        switch (repositoryType2.toLowerCase())
        {
            case "sql":
                comandaRepository = new SQLComandaRepository();
                break;
            case "memory":
                comandaRepository = new Repository<Comanda>();
                break;
            default:
                System.out.println("Invalid Repository");
                return;
        }

        ServiceTort tortService = new ServiceTort(tortRepository);
        ServiceComanda comandaService = new ServiceComanda(comandaRepository);
        Console consola = new Console(tortService,comandaService);
        consola.start();
    }


}
