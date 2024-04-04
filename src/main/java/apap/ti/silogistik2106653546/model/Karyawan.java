package apap.ti.silogistik2106653546.model;

import java.math.BigInteger;
import java.util.Date;
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
@Table(name = "karyawan")
public class Karyawan {
    @Id
    private BigInteger idKaryawan;
    
    @NotNull
    @Column(name = "nama_karyawan", nullable = false, length = 255)
    private String namaKaryawan;
    
    @NotNull
    @Column(name = "jenis_kelamin", nullable = false, length = 255)
    private Integer jenisKelamin;
    
    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tanggalLahir;
    
    @OneToMany(mappedBy = "karyawan")
    private List<PermintaanPengiriman> listPermintaanPengiriman;
} 