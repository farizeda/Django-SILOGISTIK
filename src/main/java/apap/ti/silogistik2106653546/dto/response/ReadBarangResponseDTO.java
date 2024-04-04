package apap.ti.silogistik2106653546.dto.response;

import java.math.BigInteger;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106653546.model.GudangBarang;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private String sku;
    private String merkBarang;
    private String tipeBarang;
    private BigInteger hargaBarang;
    private List<GudangBarang> gudangBarangs;
    private int totalStok; 
}
