package apap.ti.silogistik2106653546.service.implementations;

import apap.ti.silogistik2106653546.model.Karyawan;
import apap.ti.silogistik2106653546.repository.KaryawanDb;
import apap.ti.silogistik2106653546.service.interfaces.KaryawanService;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KaryawanServiceImpl implements KaryawanService{

    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public void addKaryawan(Karyawan karyawan) {
        BigInteger nextId = getKaryawanCount().add(BigInteger.ONE);
        karyawan.setIdKaryawan(nextId);
        karyawanDb.save(karyawan);
    }
    
    public BigInteger getKaryawanCount() {
        return BigInteger.valueOf(karyawanDb.count());
    }

    @Override
    public List<Karyawan> getlAllKaryawan() {
       return karyawanDb.findAll();
    }
}
