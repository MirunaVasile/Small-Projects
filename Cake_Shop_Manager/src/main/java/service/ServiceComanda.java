package service;

import domain.Comanda;
import domain.Tort;
import repository.IRepository;
import repository.RepositoryException;

import java.text.ParseException;
import java.time.Month;
import java.util.*;

public class ServiceComanda {
    IRepository<Comanda> comandaRepo;

    public ServiceComanda(IRepository comandaRepo) throws ParseException, RepositoryException {
        this.comandaRepo = comandaRepo;
    }

    public Collection<Comanda> getAllcomenzi (){
        return comandaRepo.getAll();
    }

    public void addComanda (int id, Date data, List<Tort> listaTorturi) throws RepositoryException, ServiceException {
        if(id<0)
            throw new ServiceException("id ul nu poate fi nul");

        if(listaTorturi.isEmpty()){
            throw new ServiceException("Niciun tort in comanda!");
        }
        Comanda comanda = new Comanda(id, listaTorturi, data);
        for(Tort t : comanda.getTort()){

        }
        comandaRepo.add(comanda);
    }

    public Comanda findComandabyId (int id) throws RepositoryException {
        return comandaRepo.findById(id);
    }

    public void deleteComanda (int id) throws RepositoryException {
        comandaRepo.remove(id);
    }

    public void updateComanda (int id, Date dataNoua, List<Tort> listaTorturiNoua) throws RepositoryException {
        if(listaTorturiNoua.isEmpty()) {
            throw new ServiceException("Niciun tort in comanda!");
        }
        Comanda comandaNoua = new Comanda(id, listaTorturiNoua, dataNoua);
        comandaRepo.update(id, comandaNoua);
    }

    // New method to calculate the number of torturi sold per month
    public Map<Month, Integer> getTorturiSoldPerMonth() {
        Map<Month, Integer> torturiSoldPerMonth = new HashMap<>();
        Collection<Comanda> comenzi = getAllcomenzi(); // Get all orders from the repository

        for (Comanda comanda : comenzi) {
            // Get the month from the order's date
            Month month = comanda.getData().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().getMonth();
            int numarTorturi = comanda.getTort().size();  // Get the number of torturi in this order

            // Update the torturi count for the given month
            torturiSoldPerMonth.put(month, torturiSoldPerMonth.getOrDefault(month, 0) + numarTorturi);
        }

        return torturiSoldPerMonth;
    }

    // New method to calculate the number of torturi ordered per day
    public Map<Date, Integer> getTorturiOrderedPerDay() {
        Map<Date, Integer> torturiOrderedPerDay = new HashMap<>();
        Collection<Comanda> comenzi = getAllcomenzi(); // Get all orders from the repository

        for (Comanda comanda : comenzi) {
            // Get the date from the order's date (without time part)
            Date orderDate = comanda.getData();
            // Normalize date to remove the time part (optional based on your requirements)
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(orderDate);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date normalizedDate = calendar.getTime();

            int numarTorturi = comanda.getTort().size();  // Get the number of torturi in this order

            // Update the torturi count for the given date
            torturiOrderedPerDay.put(normalizedDate, torturiOrderedPerDay.getOrDefault(normalizedDate, 0) + numarTorturi);
        }

        return torturiOrderedPerDay;
    }


    // New method to calculate the most ordered cakes
    public Map<Integer, String> getMostOrderedCakes() {
        Map<Integer, String> torturiOrdered = new HashMap<>();
        Collection<Comanda> comenzi = getAllcomenzi(); // Get all orders from the repository

        // Iterate through all orders
        for (Comanda comanda : comenzi) {
            // Iterate through all cakes in each order
            for (Tort tort : comanda.getTort()) {
                int tortId = tort.getId();  // Get the cake id
                String tortType = tort.getType(); // Get the cake type

                // Check if this cake is already in the map
                if (torturiOrdered.containsKey(tortId)) {
                    // If the cake is already in the map, increment its order count
                    String currentValue = torturiOrdered.get(tortId);
                    // Split the current value into "Type, Order Count"
                    String[] parts = currentValue.split(", ");
                    int orderCount = Integer.parseInt(parts[1].split(": ")[1]);
                    orderCount++;

                    // Update the value with the new order count
                    torturiOrdered.put(tortId, tortType + ", Orders: " + orderCount);
                } else {
                    // If the cake is not yet in the map, add it with order count 1
                    torturiOrdered.put(tortId, tortType + ", Orders: 1");
                }
            }
        }

        // Return the map where the key is the cake id, and the value is "Type, Orders: x"
        // Sort the map entries by the order count in descending order
        return torturiOrdered.entrySet()
                .stream()
                .sorted((entry1, entry2) -> {
                    // Extract order counts from the values
                    int count1 = Integer.parseInt(entry1.getValue().split(", ")[1].split(": ")[1]);
                    int count2 = Integer.parseInt(entry2.getValue().split(", ")[1].split(": ")[1]);
                    return Integer.compare(count2, count1); // Descending order
                })
                .collect(
                        LinkedHashMap::new, // Use LinkedHashMap to preserve the sorted order
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        Map::putAll
                );
    }

}
