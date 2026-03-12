package co.edu.icesi.assets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.assets.model.Asset;
import co.edu.icesi.assets.model.Measurement;
import co.edu.icesi.assets.repositories.MeasureRepository;

@Service
public class MeasureService {

    @Autowired
    private AssetService assetService;
    @Autowired
    private MeasureRepository measureRepository;

    public void createMeasure(int assetId, double value) {

        Asset asset = assetService.getAssetById(assetId);
        // Create measure logic here, e.g., save to database
        // For example:
        Measurement measure = new Measurement();
        measure.setValue(value);
        measure.setAsset(asset);
        measureRepository.save(measure);
    }

    
}
