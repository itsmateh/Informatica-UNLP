package P3.EJ4;

public class AreaEmpresa {
    public String id;
    public double tardanza;

    public AreaEmpresa(String id, double tardanza) {
        this.id = id;
        this.tardanza = tardanza;
    }

    public String getId() {
        return id;
    }

    public double getTardanza() {
        return tardanza;
    }
}
