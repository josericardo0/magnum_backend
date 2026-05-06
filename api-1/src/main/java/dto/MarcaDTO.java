package dto;


public class MarcaDTO {
    public String code;
    public String name;

    @Override
    public String toString() {
        return "MarcaDTO{name='" + name + "', code='" + code + "'}";
    }

    public MarcaDTO() {}
}