package apap.ti.silogistik2106653546.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106653546.model.Barang;

@Repository
public interface BarangDb extends JpaRepository<Barang, String> {
    List<Barang> findAll();

    Optional<Barang> findBySku(String sku);
    
    @Query("SELECT b FROM Barang b WHERE b.sku LIKE ?1% ORDER BY b.sku DESC")
    Optional<Barang> findTopBySkuStartingWithOrderBySkuDesc(String prefix);

    @Query("SELECT COUNT(b) FROM Barang b WHERE b.sku LIKE ?1%")
    Long countBySkuStartingWith(String prefix);
}
