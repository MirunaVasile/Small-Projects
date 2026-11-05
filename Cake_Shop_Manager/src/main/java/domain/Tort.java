package domain;


public class Tort extends Entity {

    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Tort(int id, String type) {
        super(id);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Id = " + getId() +
                ", Tip = " + type + "; ";
    }

//    // Serialize to CSV format
//    public String toCsv() {
//        return getId() + "," + type;
//    }
//
//    // Deserialize from CSV format
//    public static Tort fromCsv(String csv) {
//        String[] parts = csv.split(",");
//        int id = Integer.parseInt(parts[0]);
//        String type = parts[1];
//        return new Tort(id, type);
//    }



}
