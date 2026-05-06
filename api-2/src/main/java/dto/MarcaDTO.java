package dto;

public class MarcaDTO {

    public String code;
    public String name;
    public String vehicleType;

    @Override
    public String toString() {
        return "MarcaDTO{name='" + name + "', code='" + code + "'}";
    }
}
