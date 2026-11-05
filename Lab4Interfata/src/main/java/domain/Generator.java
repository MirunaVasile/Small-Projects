package domain;

import repository.RepositoryException;
import repository.SQLComandaRepository;
import repository.SQLTortRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;

public class Generator {

    static SQLTortRepository tort_repo = new SQLTortRepository();
    static SQLComandaRepository comanda_repo = new SQLComandaRepository();

    public static void generateComenzi() throws RepositoryException {
      //  RandomGenerator generator = new RandomGenerator();
        for (int i = 1; i < 100; i++) {
            int id = i+1;
            int nr_torturi = new java.util.Random().nextInt(4)+1;
            List<Tort> lista_torturi = new ArrayList<Tort>();
            for(int j = 0; j<nr_torturi; j++){
                int id_tort = new java.util.Random().nextInt(10)+100;
                Tort tort = tort_repo.findById(id_tort);
                lista_torturi.add(tort);

            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try{
                Date d1 = dateFormat.parse("01-01-2024");
                Date d2 = dateFormat.parse("31-12-2024");
                Date date = new Date(ThreadLocalRandom.current().nextLong(d1.getTime(), d2.getTime()));
                Comanda comanda = new Comanda(id,lista_torturi,date);
                comanda_repo.add(comanda);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws RepositoryException {
        Generator generator = new Generator();
        generator.generateComenzi();

    }
}
