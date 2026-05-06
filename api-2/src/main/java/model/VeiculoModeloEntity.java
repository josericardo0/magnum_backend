package model;

import jakarta.persistence.*;

@Entity
@Table(name = "veiculo_modelo")
public class VeiculoModeloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String marcaCodigo;
    public String marcaNome;

    public String modeloCodigo;
    public String modeloNome;
}