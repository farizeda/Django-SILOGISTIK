package apap.ti.silogistik2106653546.dto.request;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import apap.ti.silogistik2106653546.model.Karyawan;
import apap.ti.silogistik2106653546.model.PermintaanPengirimanBarang;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {
    
    private long idPermintaanPengiriman;

    private String noPengiriman; // Varchar(12)
    
    private Boolean isCancelled = Boolean.FALSE;

    @NotBlank
    private String namaPenerima;
    
    @NotBlank
    private String alamatPenerima;
    
    @NotBlank
    private String tanggalPengirimanStr; // dat

    @NotNull
    @Min(value = 1, message = "Biaya Layanan harus lebih besar dari 0")
    private Integer biayaPengiriman;
    
    @NotNull
    private Integer jenisLayanan;
    
    @NotNull
    private Karyawan karyawan;
    
    @NotEmpty(message = "Permintaan Pengiriman Barang cannot be empty")
    private List<PermintaanPengirimanBarang> permintaanPengirimanBarangs;
    
}
