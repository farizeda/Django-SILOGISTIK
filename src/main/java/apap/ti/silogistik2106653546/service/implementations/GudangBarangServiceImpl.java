package apap.ti.silogistik2106653546.service.implementations;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106653546.model.Barang;
import apap.ti.silogistik2106653546.model.Gudang;
import apap.ti.silogistik2106653546.model.GudangBarang;
import apap.ti.silogistik2106653546.repository.GudangBarangDb;
import apap.ti.silogistik2106653546.service.interfaces.GudangBarangService;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    private GudangBarangDb gudangBarangDb;

    @Override
    public List<GudangBarang> getAllGudangBarang(){
        return gudangBarangDb.findAll();
    }

    @Override
    public Integer getStock(Barang barang) {
        Integer stock = gudangBarangDb.findTotalStockByBarang(barang);

        if(stock == null){
            return 0;
        }
        return stock.intValue();
    }

    @Override
    public List<GudangBarang> getAllGudangBarangByBarang(Barang selectedBarang) {
        return gudangBarangDb.findByBarang(selectedBarang);
    }

    @Override
    public GudangBarang getGudangBarangByBarangAndGudang(Barang barang, Gudang gudang) {
        for (GudangBarang gudangBarang: getAllGudangBarang()) {
            if (gudangBarang.getBarang().getSku().equals(barang.getSku()) && gudangBarang.getGudang().equals(gudang)) {
                return gudangBarang;
            }
        }
        return null;
    }

    @Override
    public void addGudangBarang(GudangBarang gudangBarang) {
        gudangBarang.setId(generateIdGudangBarang());
        gudangBarangDb.save(gudangBarang);
    }

    @Override
    public void updateGudangBarang(GudangBarang newGudangBarang) {
       GudangBarang gudangBarang = getGudangBarangByBarangAndGudang(newGudangBarang.getBarang(), newGudangBarang.getGudang());
        
       if(gudangBarang != null){
        gudangBarang.setBarang(newGudangBarang.getBarang());
        gudangBarang.setStok(newGudangBarang.getStok());
        gudangBarangDb.save(gudangBarang);
       }
       else{
         addGudangBarang(newGudangBarang);
       }
    }


    @Override
    public BigInteger generateIdGudangBarang() {
        BigInteger latestId = gudangBarangDb.findHighestIdGudangBarang().orElse(BigInteger.ZERO);
        return latestId.add(BigInteger.ONE);
    }

    @Override
    public void deleteGudangBarang(GudangBarang gudangBarang) {
       gudangBarangDb.delete(gudangBarang);
    }
}
