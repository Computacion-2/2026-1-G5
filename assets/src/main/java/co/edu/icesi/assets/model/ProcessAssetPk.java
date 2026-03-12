package co.edu.icesi.assets.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ProcessAssetPk {
    
    @Column(name = "asset_id", insertable = false, updatable = false)
    private Integer assetId;
    @Column(name = "process_id", insertable = false, updatable = false)
    private Integer processId;
}
