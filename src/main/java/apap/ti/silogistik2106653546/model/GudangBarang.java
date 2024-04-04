package apap.ti.silogistik2106653546.model;

import java.math.BigInteger;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Table(name = "gudang_barang")
public class GudangBarang {
    @Id
    private BigInteger id;
    
    @ManyToOne
    @JoinColumn(name = "id_gudang", unique = false)
    private Gudang gudang;
    
    @ManyToOne
    @JoinColumn(name = "sku_barang", unique = false)
    private Barang barang;
    
    @NotNull
    @Min(value = 1, message = "Stok harus lebih besar dari 0")
    @Column(name = "stok", nullable = false)
    private Integer stok;
}

