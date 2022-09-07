package hr.tvz.konjetic.hardwareapp.model;

import hr.tvz.konjetic.hardwareapp.enums.Type;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class HardwareCommand {
    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Code must not be empty")
    private String code;

    @DecimalMin(message = "Price can not be lower than 0.01", value = "0.00", inclusive = false)
    @NotNull(message = "Price must be entered")
    private Double price;

    @NotBlank(message = "Type must be entered")
    @Pattern(message = "Entered type is not valid", regexp = "^(CPU|RAM|GPU|MBO|OTHER|STORAGE)")
    private String type;

    @NotNull(message = "Number of available items must be entered")
    @PositiveOrZero(message = "Number of available items must be a positive integer")
    private Integer availableNum;

    public HardwareCommand(String name, String code, Double price, String type, Integer availableNum) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.availableNum = availableNum;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }

    public Type getType() {
        Type typeEnum = Type.CPU;
        List<Type> types =
                new ArrayList<Type>(EnumSet.allOf(Type.class));

        for(int i = 0; i < types.size(); i++){
            if(type.equals(types.get(i).toString())){
                typeEnum = types.get(i);
            }
        }

        return typeEnum;
    }

    public Integer getAvailableNum() {
        return availableNum;
    }
}
