package apap.ti.silogistik2106653546.service.implementations;

import apap.ti.silogistik2106653546.model.Gudang;
import apap.ti.silogistik2106653546.repository.GudangDb;
import apap.ti.silogistik2106653546.service.interfaces.GudangService;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GudangServiceImpl implements GudangService{

    @Autowired
    GudangDb gudangDb;

    @Override
    public void addGudang(Gudang gudang) {
        gudang.setIdGudang(generateIdGudang());
        gudangDb.save(gudang);
    }

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }

    @Override
    public Gudang getGudangById(BigInteger id) {
        for (Gudang gudang: getAllGudang()) {
            if (gudang.getIdGudang().equals(id)) {
                return gudang;
            }
        }
        return null;
    }

    @Override
    public BigInteger generateIdGudang() {
        BigInteger latestId = gudangDb.findHighestIdGudang().orElse(BigInteger.ZERO);
        return latestId.add(BigInteger.ONE);
    }

    @Override
    public Gudang updateGudang(Gudang updatedGudang) {
       Gudang gudang = getGudangById(updatedGudang.getIdGudang());
       if(gudang!=null){
           gudang.setGudangBarangs(updatedGudang.getGudangBarangs());
           gudangDb.save(gudang);
       }
       return gudang;
    }
    
}
