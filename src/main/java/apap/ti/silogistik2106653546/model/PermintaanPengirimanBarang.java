package apap.ti.silogistik2106653546.model;

import java.math.BigInteger;
import java.security.Timestamp;
import java.sql.Date;
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
@Table(name = "permintaan_pengiriman_barang")
public class PermintaanPengirimanBarang {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "id_permintaan_pengiriman")
    private PermintaanPengiriman permintaanPengiriman;
    
    @ManyToOne
    @JoinColumn(name = "sku_barang")
    private Barang barang;
    
    @NotNull
    @Column(name = "kuantitas_pesanan", nullable = false)
    private Integer kuantitasPesanan;
}