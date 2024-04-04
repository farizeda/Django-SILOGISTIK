package apap.ti.silogistik2106653546.dto.request;

import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106653546.model.GudangBarang;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequestDTO {
    private String namaKaryawan;
    private Integer jenisKelamin;
    private Date tanggalLahir;
}
