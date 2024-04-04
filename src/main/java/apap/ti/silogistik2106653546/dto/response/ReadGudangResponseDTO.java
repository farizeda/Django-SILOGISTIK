package apap.ti.silogistik2106653546.dto.response;

import java.math.BigInteger;
import java.util.List;

import apap.ti.silogistik2106653546.model.GudangBarang;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadGudangResponseDTO {
    private BigInteger idGudang; 
    private String namaGudang;
    private String alamatGudang;
    private List<GudangBarang> gudangBarangs;
}
