package co.edu.icesi.assets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.assets.model.Asset;
import co.edu.icesi.assets.repositories.AssetRepository;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public Asset createAsset(Asset asset) {
        asset.setId(null);
        if (asset.getName() == null || asset.getName().isBlank() || asset.getType() == null) {
            throw new IllegalArgumentException("Asset name and type are required");
        }
        return assetRepository.save(asset);
    }

    public Asset getAssetById(int assetId) {
        return assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("Asset not found with id: " + assetId));
    }
}
