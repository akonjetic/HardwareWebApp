package hr.tvz.konjetic.hardwareapp.model;

import hr.tvz.konjetic.hardwareapp.enums.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "hardware")
public class Hardware implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "product_name")
    String name;
    String code;
    Double price;
    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    Type type;
    Integer availableNum;

    @OneToMany(mappedBy = "hardware", fetch = FetchType.EAGER)
    List<Review> reviews;

    public Hardware(){}

    public Hardware(String name, String code, Double price, Type type, Integer availableNum) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.availableNum = availableNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(Integer availableNum) {
        this.availableNum = availableNum;
    }
}
