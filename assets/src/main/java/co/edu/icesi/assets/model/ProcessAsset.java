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
@Table(schema = "asset_manager", name = "process_asset")
@Data
public class ProcessAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "process_seq")
    @SequenceGenerator(name = "process_seq", initialValue = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
    @ManyToOne
    @JoinColumn(name = "process_id")
    private Process process;

    private long delayRead;
    
}
