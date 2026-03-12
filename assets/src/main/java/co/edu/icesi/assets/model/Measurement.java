package co.edu.icesi.assets.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(schema = "asset_manager", name = "measurement")
@Data
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "measure_seq")
    @SequenceGenerator(name = "measure_seq", initialValue = 1)
    private Integer id;

    private Timestamp time;

    private double value;

    @ManyToOne
    @JoinColumn(name = "asset")
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "execution")
    @JsonIgnore
    private Execution execution;

}