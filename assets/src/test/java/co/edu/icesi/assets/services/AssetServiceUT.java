package co.edu.icesi.assets.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.assets.model.Asset;
import co.edu.icesi.assets.repositories.AssetRepository;

@SpringBootTest
public class AssetServiceUT {
    @Mock
    private AssetRepository assetRepository;
    @InjectMocks
    private AssetService assetService;
    
    @Test
    public void createAsset_validAsset_returnsSavedAsset() {
        // Arrange
        Asset asset = new Asset();
        asset.setName("Test Asset");
        asset.setType(1);

        when(assetRepository.save(any(Asset.class))).thenReturn(asset);

        // Act
        Asset result = assetService.createAsset(asset);

        // Assert
        assertNotNull(result);
        assertEquals("Test Asset", result.getName());
        assertEquals(1, result.getType());
    }
    
}
