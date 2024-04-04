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
@Table(name = "barang")
public class Barang {
    @Id
    @Column(name = "sku", nullable = false, unique = true)
    private String sku;
    
    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private Integer tipeBarang;
    
    @NotNull
    @Column(name = "merk_barang", nullable = false, length = 255)
    private String merkBarang;
    
    @NotNull
    @Column(name = "harga_barang", nullable = false)
    private BigInteger hargaBarang;
    
    @OneToMany(mappedBy = "barang")
    private List<GudangBarang> gudangBarangs;

    @OneToMany(mappedBy = "barang")
    private List<PermintaanPengirimanBarang> permintaanPengirimanBarangs;

}
