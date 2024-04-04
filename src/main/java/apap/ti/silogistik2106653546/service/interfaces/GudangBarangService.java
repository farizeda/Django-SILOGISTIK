package apap.ti.silogistik2106653546.service.interfaces;

import java.math.BigInteger;
import java.util.List;

import apap.ti.silogistik2106653546.model.Barang;
import apap.ti.silogistik2106653546.model.Gudang;
import apap.ti.silogistik2106653546.model.GudangBarang;

public interface GudangBarangService {
    public Integer getStock(Barang barang);

    void addGudangBarang(GudangBarang gudangBarang);

    BigInteger generateIdGudangBarang();

    void deleteGudangBarang(GudangBarang gudangBarang);
    
    List<GudangBarang> getAllGudangBarangByBarang(Barang selectedBarang);

    GudangBarang getGudangBarangByBarangAndGudang(Barang barang, Gudang gudang) ;

    void updateGudangBarang(GudangBarang newGudangBarang);

    List<GudangBarang> getAllGudangBarang();
}
