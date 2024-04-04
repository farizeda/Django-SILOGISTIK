package apap.ti.silogistik2106653546.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106653546.model.Barang;
import apap.ti.silogistik2106653546.model.GudangBarang;

public interface GudangBarangDb extends JpaRepository<GudangBarang, BigInteger>{
    
    @Query("SELECT SUM(gb.stok) FROM GudangBarang gb WHERE gb.barang = :barang")
    Integer findTotalStockByBarang(Barang barang);

    List<GudangBarang> findByBarang(Barang selectedBarang);

    @Query("SELECT MAX(gb.id) FROM GudangBarang gb")
    Optional<BigInteger> findHighestIdGudangBarang();
    
}
