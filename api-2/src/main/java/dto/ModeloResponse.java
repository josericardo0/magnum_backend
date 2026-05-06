package dto;

import java.util.List;

public class ModeloResponse {

    public List<Model> models;

    public static class Model {
        public String code;
        public String name;
    }
}
