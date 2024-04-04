package apap.ti.silogistik2106653546.model;

import java.math.BigInteger;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang")
public class Gudang {
    @Id
    private BigInteger idGudang;
    
    @NotNull
    @Column(name = "nama_gudang", nullable = false, length = 255)
    private String namaGudang;
    
    @NotNull
    @Column(name = "alamat_gudang", nullable = false, length = 255)
    private String alamatGudang;
    
    @OneToMany(mappedBy = "gudang")
    private List<GudangBarang> gudangBarangs;
}

