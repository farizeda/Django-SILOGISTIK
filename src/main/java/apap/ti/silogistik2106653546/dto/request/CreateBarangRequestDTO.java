package apap.ti.silogistik2106653546.dto.request;

import java.math.BigInteger;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    
    @NotBlank(message = "Merk barang tidak boleh kosong")
    private String merkBarang;

    @NotBlank(message = "Tipe barang tidak boleh kosong")
    private String tipeBarang;

    @NotNull
    @Min(value = 1, message = "Harga barang harus lebih besar dari 0")
    private BigInteger hargaBarang;
}
