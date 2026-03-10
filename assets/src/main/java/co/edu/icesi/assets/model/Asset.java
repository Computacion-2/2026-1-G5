package co.edu.icesi.assets.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "asset_manager", name = "asset")
@Data
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_seq")
    @SequenceGenerator(name = "asset_seq", initialValue = 1)
    private Integer id;

    @Column(name = "asset_state")
    private String assetState;
    private String name;

    private String description;
    private Integer type;
    @Column(name = "work_space")
    private Integer workSpace;

    @ManyToOne
    @JoinColumn(name = "asset_sup")
    @JsonIgnore
    private Asset assetSup;

    @OneToMany(mappedBy = "assetSup")
    private List<Asset> childs;

    @OneToMany(mappedBy = "asset")
    private List<ProcessAsset> processes;


}