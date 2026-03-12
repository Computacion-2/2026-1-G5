package co.edu.icesi.assets.model;


import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(schema = "asset_manager", name = "execution")
@Data
public class Execution {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "execution_seq")
    @SequenceGenerator(name = "execution_seq", initialValue = 1)
    private Integer id;

    private Timestamp startDate;
    private Timestamp endDate;

    private String status;
    private String operUsername;

    @OneToMany(mappedBy = "execution")
    private List<Measurement> measurements;
    @ManyToOne
    @JoinColumn(name = "process")
    @JsonIgnore
    private Process process;
}
