package apap.ti.silogistik2106653546.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106653546.model.Karyawan;

@Repository
public interface KaryawanDb extends JpaRepository<Karyawan, BigInteger>{
    
}
