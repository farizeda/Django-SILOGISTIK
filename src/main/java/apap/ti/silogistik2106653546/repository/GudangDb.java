package apap.ti.silogistik2106653546.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106653546.model.Gudang;

@Repository
public interface GudangDb extends JpaRepository<Gudang, BigInteger>{
    @Query("SELECT MAX(g.idGudang) FROM Gudang g")
    Optional<BigInteger> findHighestIdGudang();
}
