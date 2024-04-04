package apap.ti.silogistik2106653546.dto.request;

import java.util.List;

import apap.ti.silogistik2106653546.model.GudangBarang;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormGudangBarangDTO {
    private List<GudangBarang> gudangBarangs;
}