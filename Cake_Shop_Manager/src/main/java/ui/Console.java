package ui;

import domain.Comanda;
import domain.Tort;
import repository.RepositoryException;
import service.ServiceComanda;
import service.ServiceException;
import service.ServiceTort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Console {
    private final ServiceTort serviceTort;
    private final ServiceComanda serviceComanda;

    public Console(ServiceTort tortService, ServiceComanda comandaService) {

        this.serviceTort = tortService;
        this.serviceComanda = comandaService;
    }



    public void start() throws RepositoryException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n------MENIU-------");

            System.out.println("\n-->PENTRU TORT:");
            System.out.println("1. Adauga tort");
            System.out.println("2. Afiseaza toate torturile");
            System.out.println("3. Sterge tort");
            System.out.println("4. Modifica tort");
            System.out.println("5. Cauta tort");


            System.out.println("\n-->PENTRU COMANDA:");
            System.out.println("6. Adauga comanda");
            System.out.println("7. Afiseaza toate comenzile");
            System.out.println("8. Sterge comanda");
            System.out.println("9. Modifica comanda");
            System.out.println("10. Cauta comanda");



            System.out.println("\n0. Iesire");
            System.out.println("\nIntroduceti comanda:");
            int command = Integer.parseInt(scanner.nextLine());

            if (command ==0) {
                break;

            } else if (command == 1) {
                adaugaTort(scanner);

            }else if (command == 2) {
                afiseazaTorturi(scanner);

            }else if (command == 3) {
                stergeTort(scanner);

            }else if (command == 4) {
                modificaTort(scanner);

            }else if (command == 5) {
                cautaTort(scanner);

            }else if (command == 6) {
                adaugaComanda(scanner);

            }else if (command == 7) {
                afiseazaComenzi(scanner);

            }else if (command == 8) {
                stergeComanda(scanner);

            }else if (command == 9) {
                modificaComanda(scanner);

            }else if (command == 10) {
                cautaComanda(scanner);

            }
            else {
                System.out.println("Coamanda invalida!");
            }
        }

    }

    private void stergeComanda(Scanner scanner) {
        System.out.println("Introduceti id-ul comenazii de sters:");
        int id;
        while (true) {try{
            id = Integer.parseInt(scanner.nextLine());
            break;
        }catch (NumberFormatException e){
            System.out.println("Id-ul trebuie sa fie un numar intreg!");
        }}
        try {
            serviceComanda.deleteComanda(id);
        } catch (RepositoryException e) {
            System.out.println("Eroare: "+e.getMessage());
        }

    }

    private void cautaComanda(Scanner scanner) {
        System.out.println("Introduceti id-ul comenzii cautate:");
        int id;
        while (true) {try{
            id = Integer.parseInt(scanner.nextLine());
            break;
        }catch (NumberFormatException e){
            System.out.println("Id-ul trebuie sa fie un numar intreg!");
        }}
        try {
            System.out.println("Comanda cautata:");
            Comanda comanda = serviceComanda.findComandabyId(id);
            System.out.println(comanda.toString());
        } catch (RepositoryException e) {
            System.out.println("Eroare: "+e.getMessage());
        }
    }

    private void adaugaComanda(Scanner scanner) throws RepositoryException {
        System.out.println("Introduceti id-ul comenzii de adaugat:");
        int id;
        while (true) {try{
            id = Integer.parseInt(scanner.nextLine());
            break;
        }catch (NumberFormatException e){
            System.out.println("Id-ul trebuie sa fie un numar intreg!");
        }}



        Date data = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        while (true){
            try {
                System.out.println("Introduceti data comenzii (dd-MM-yyyy):");
                String dataString = scanner.nextLine();
                data = dateFormat.parse(dataString);
                break;
            } catch (NumberFormatException | ParseException e) {
                System.out.println("Invalid date format! Please enter date in dd-MM-yyyy format.");
            }}

        System.out.println("Introduceti numarul de torturi din comanda:");
        int nr = Integer.parseInt(scanner.nextLine());
        List<Tort> listaTorturi = new ArrayList<>();

        for(int i = 1; i<=nr; i++){
            System.out.println("Introduceti id-ul tortului " + i + ":");
            int idTort = Integer.parseInt(scanner.nextLine());
            try {
                Tort tortNou = serviceTort.findTortbyId(idTort);
                listaTorturi.add(tortNou);
            }catch (RepositoryException e){
                System.out.println("Eroare: "+e.getMessage());
            }

        }

        try{
            serviceComanda.addComanda(id,data,listaTorturi);
        }catch (ServiceException | RepositoryException e){
            System.out.println("Eroare: "+e.getMessage());
        }

    }

    private void modificaComanda(Scanner scanner) {
        System.out.println("Introduceti id-ul comenzii de modificat:");
        int id;
        while (true) {try{
            id = Integer.parseInt(scanner.nextLine());
            break;
        }catch (NumberFormatException e){
            System.out.println("Id-ul trebuie sa fie un numar intreg!");
        }}
        try {
            System.out.println("Introduceti noua data a comenzii de modificat (dd-MM-yyyy):");
            String dataString = scanner.nextLine();

            Date dataNoua = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            try {
                dataNoua = dateFormat.parse(dataString);
            } catch (ParseException e) {
                System.out.println("Invalid  format!");
            }
            int nr = 0;
        try {System.out.println("Introduceti numarul de torturi din comanda:");
            nr = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            System.out.println("Invalid number format! Please enter number in dd-MM-yyyy format.");
        }

            List<Tort> listaTorturiNou = new ArrayList<>();

            for(int i = 1; i<=nr; i++) {
                System.out.println("Introduceti id-ul tortului " + i + ":");
                int idTort = Integer.parseInt(scanner.nextLine());
                try {
                    Tort tortNou = serviceTort.findTortbyId(idTort);
                    listaTorturiNou.add(tortNou);
                } catch (RepositoryException e) {
                    System.out.println("Eroare: " + e.getMessage());
                }
            }



            serviceComanda.updateComanda(id, dataNoua, listaTorturiNou);
        } catch (RepositoryException | ServiceException e) {
            System.out.println("Eroare: "+e.getMessage());
        }
    }

    private void afiseazaComenzi(Scanner scanner) {
        System.out.println("\nLista de comenzi:");
        for(Comanda comanda : serviceComanda.getAllcomenzi()){
            System.out.println(comanda.toString());
        }
        System.out.println("\n");
    }

    private void cautaTort(Scanner scanner) {
        System.out.println("Introduceti id-ul tortului cautat:");
        int id;
        while (true) {try{
            id = Integer.parseInt(scanner.nextLine());
            break;
        }catch (NumberFormatException e){
            System.out.println("Id-ul trebuie sa fie un numar intreg!");
        }}
        try {
            System.out.println("Tortul cautat:");
            Tort tort = serviceTort.findTortbyId(id);
            System.out.println(tort.toString());
        } catch (RepositoryException e) {
            System.out.println("Eroare: "+e.getMessage());
        }
    }

    private void modificaTort(Scanner scanner) {
        System.out.println("Introduceti id-ul tortului de modificat:");
        int id;
        while (true) {try{
            id = Integer.parseInt(scanner.nextLine());
            break;
        }catch (NumberFormatException e){
            System.out.println("Id-ul trebuie sa fie un numar intreg! ");
        }}
        try {
            System.out.println("Introduceti noul tip al tortului de modificat:");
            String tipNou = scanner.nextLine();
            serviceTort.updateTort(id, tipNou);
        } catch (RepositoryException |ServiceException e) {
            System.out.println("Eroare: "+e.getMessage());
        }
    }

    private void stergeTort(Scanner scanner) {
        System.out.println("Introduceti id-ul tortului de sters:");
        int id;
        while (true) {try{
            id = Integer.parseInt(scanner.nextLine());
            break;
        }catch (NumberFormatException e){
            System.out.println("Id-ul trebuie sa fie un numar intreg!");
        }}
        try {
            serviceTort.deleteTort(id);
        } catch (RepositoryException e) {
            System.out.println("Eroare: "+e.getMessage());
        }

    }

    private void afiseazaTorturi(Scanner scanner) {
        System.out.println("\nLista de torturi:");
        for(Tort tort : serviceTort.getAlltortuti()){
            System.out.println(tort.toString());
        }
        System.out.println("\n");
    }

    private void adaugaTort(Scanner scanner) throws RepositoryException {
        System.out.println("Introduceti id-ul tortului de adaugat:");
        int id;
        while (true) {try{
            id = Integer.parseInt(scanner.nextLine());
            break;
        }catch (NumberFormatException e){
            System.out.println("Id-ul trebuie sa fie un numar intreg!");
        }}
        System.out.println("Introduceti tipul tortului:");
        String tip = scanner.nextLine();
        try {
            serviceTort.addTort(id, tip);
        } catch (RepositoryException |ServiceException e) {
            System.out.println("Eroare: "+e.getMessage());
        }

    }
}
