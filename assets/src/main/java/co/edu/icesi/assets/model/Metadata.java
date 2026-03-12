package co.edu.icesi.assets.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "asset_manager", name = "meta_data")
@Data
public class Metadata {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meta_data_seq")
    @SequenceGenerator(name = "meta_data_seq", initialValue = 1)
    private Integer id;

    private String name;
    private String value;
    private String description;

    @ManyToOne
    @JoinColumn(name = "asset")
    private Asset asset;


    
}
