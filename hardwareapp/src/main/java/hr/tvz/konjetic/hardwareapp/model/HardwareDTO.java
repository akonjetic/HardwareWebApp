package hr.tvz.konjetic.hardwareapp.model;

public class HardwareDTO {
    private final String name;
    private final Double price;
    private final String code;

    public HardwareDTO(String name, Double price, String code){
        this.name = name;
        this.price = price;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "HardwareDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", code='" + code + '\'' +
                '}';
    }
}
