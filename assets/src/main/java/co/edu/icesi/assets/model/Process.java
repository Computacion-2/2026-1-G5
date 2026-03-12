package co.edu.icesi.assets.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "asset_manager", name = "process")
@Data
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "process_seq")
    @SequenceGenerator(name = "process_seq", initialValue = 1)
    private Integer id;

    private String name;
    private String description;
    private String state;
    private Integer workSpace;

    @OneToMany(mappedBy = "process")
    private List<ProcessAsset> assets;

    @OneToMany(mappedBy = "process")
    private List<Execution> executions;
}