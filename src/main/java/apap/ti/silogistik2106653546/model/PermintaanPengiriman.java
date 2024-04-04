package apap.ti.silogistik2106653546.model;

import java.math.BigInteger;
import java.security.Timestamp;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@Table(name = "permintaan_pengiriman")
@SQLDelete(sql = "UPDATE permintaan_pengiriman SET is_cancelled = true WHERE id_permintaan_pengiriman=?")
// @Where(clause = "is_cancelled=false")
public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPermintaanPengiriman;
    
    @NotNull
    @Column(name = "no_pengiriman", nullable = false, length = 255)
    private String noPengiriman; // Varchar(12)
    
    @NotNull
    @Column(name = "is_cancelled", nullable = false)
    private Boolean isCancelled = Boolean.FALSE;
    
    @NotNull
    @Column(name = "nama_penerima", nullable = false, length = 255)
    private String namaPenerima;
    
    @NotNull
    @Column(name = "alamat_penerima", nullable = false, length = 255)
    private String alamatPenerima;
    
    @NotNull
    @Column(name = "tanggal_pengiriman", nullable = false)
    private LocalDate tanggalPengiriman; // dat

    @NotNull
    @Column(name = "biaya_pengiriman", nullable = false)
    private Integer biayaPengiriman;
    
    @NotNull
    @Column(name = "jenis_layanan", nullable = false)
    private Integer jenisLayanan;
    
    @NotNull
    @Column(name = "waktu_permintaan", nullable = false)
    private LocalDateTime waktuPermintaan; // DateTime
    
    @ManyToOne
    @JoinColumn(name = "id_karyawan", referencedColumnName = "idKaryawan")
    private Karyawan karyawan;

    @OneToMany(mappedBy = "permintaanPengiriman")
    private List<PermintaanPengirimanBarang> permintaanPengirimanBarangs;
    
}
