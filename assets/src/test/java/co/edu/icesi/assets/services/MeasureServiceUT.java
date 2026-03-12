package co.edu.icesi.assets.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.icesi.assets.model.Asset;
import co.edu.icesi.assets.model.Measurement;
import co.edu.icesi.assets.repositories.MeasureRepository;

@ExtendWith(MockitoExtension.class)
public class MeasureServiceUT {


    @Mock
    private MeasureRepository measureRepository;
    
    @Mock
    private AssetService assetService;
    @InjectMocks
    private MeasureService measureService;

    private Asset asset;
    

    @BeforeEach
    public void init(){
        asset = new Asset();
        asset.setId(1);
        asset.setName("Asset 1");
        asset.setDescription("Description of Asset 1");
        asset.setAssetState("A");
        asset.setType(1);
        asset.setWorkSpace(1);
        when(assetService.getAssetById(1)).thenReturn(asset);

    }

    @Test
    public void testAddMeasure(){
        measureService.createMeasure(1, 2.5);

        verify(measureRepository).save(any(Measurement.class));
        verify(assetService).getAssetById(1);
    }
}
