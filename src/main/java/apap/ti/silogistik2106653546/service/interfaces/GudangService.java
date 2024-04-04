package apap.ti.silogistik2106653546.service.interfaces;

import java.math.BigInteger;
import java.util.List;

import apap.ti.silogistik2106653546.model.Gudang;

public interface GudangService {
    
    //method untuk menyimpan gudang baru
    void addGudang(Gudang gudang);

    //method untuk mendapatkan barang yang telah tersimpan
    List<Gudang> getAllGudang();

    //method untuk mendapatkan barang berdasarkan id barang
     Gudang getGudangById(BigInteger id);

     //method untuk membuat IdGudang
     BigInteger generateIdGudang();

     Gudang updateGudang(Gudang gudang);

}
