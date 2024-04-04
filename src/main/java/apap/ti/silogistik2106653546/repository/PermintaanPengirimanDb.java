package apap.ti.silogistik2106653546.repository;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106653546.model.PermintaanPengiriman;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, String>{
    
    @Query("SELECT MAX(g.idPermintaanPengiriman) FROM PermintaanPengiriman g")
    Optional<BigInteger> findHighestIdPermintaanPengiriman();

    List<PermintaanPengiriman> findByTanggalPengirimanBetween(LocalDate startDate, LocalDate endDate);
}
