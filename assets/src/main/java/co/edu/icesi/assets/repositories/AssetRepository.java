package co.edu.icesi.assets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.icesi.assets.model.Asset;

public interface AssetRepository extends JpaRepository<Asset, Integer> {
    
}
