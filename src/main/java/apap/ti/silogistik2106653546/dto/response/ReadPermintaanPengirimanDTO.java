package apap.ti.silogistik2106653546.dto.response;


import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106653546.model.GudangBarang;
import apap.ti.silogistik2106653546.model.Karyawan;
import apap.ti.silogistik2106653546.model.PermintaanPengirimanBarang;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadPermintaanPengirimanDTO {
    private long idPermintaanPengiriman;
    
    private String noPengiriman; // Varchar(12)

    private Boolean isCancelled;
    
    private String namaPenerima;
    
    private String alamatPenerima;
    
    private String tanggalPengiriman; // date

    private Integer biayaPengiriman;

    private Integer jenisLayanan;

    private LocalDateTime waktuPermintaan; // DateTime
  
    private Karyawan karyawan;

    private List<PermintaanPengirimanBarang> permintaanPengirimanBarangs;
}
