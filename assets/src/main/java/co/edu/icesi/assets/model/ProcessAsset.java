package co.edu.icesi.assets.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "asset_manager", name = "process_asset")
@Data
public class ProcessAsset {

    @EmbeddedId
    private ProcessAssetPk id;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
    @ManyToOne
    @JoinColumn(name = "process_id")
    private Process process;

    private long delayRead;
    
}
