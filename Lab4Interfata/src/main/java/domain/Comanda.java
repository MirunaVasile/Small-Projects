package domain;

import java.util.Date;
import java.util.List;

public class Comanda extends Entity{

    private Date data;
    public Date getData() {return data;}
    public void setData(Date data) {this.data = data;}

    private List<Tort> tort;
    public List<Tort> getTort() {return tort;}
    public void setTort(List<Tort> tort) {this.tort = tort;}


    public Comanda(int id, List<Tort> tort, Date data) {
        super(id);
        this.tort = tort;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Comanda " +
                "id=" + getId() +
                "; data=" + data +
                "; tort=" + tort;
    }

//    // Serialize to CSV format
//    public String toCsv() {
//        String tortsCsv = tort.stream()
//                .map(t -> t.getId() + ":" + t.getType())
//                .collect(Collectors.joining(";"));
//        return getId() + "," + tortsCsv + "," + data.getTime();
//    }
//
//    // Deserialize from CSV format
//    public static Comanda fromCsv(String csv) {
//        String[] parts = csv.split(",");
//        int id = Integer.parseInt(parts[0]);
//        List<Tort> torturi = new ArrayList<>();
//        for (String tortPart : parts[1].split(";")) {
//            String[] tortData = tortPart.split(":");
//            int tortId = Integer.parseInt(tortData[0]);
//            String tortType = tortData[1];
//            torturi.add(new Tort(tortId, tortType));
//        }
//        Date data = new Date(Long.parseLong(parts[2]));
//        return new Comanda(id, torturi, data);
//    }
}
