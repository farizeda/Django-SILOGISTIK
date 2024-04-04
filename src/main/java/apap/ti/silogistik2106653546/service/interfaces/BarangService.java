package apap.ti.silogistik2106653546.service.interfaces;

import java.util.List;

import apap.ti.silogistik2106653546.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106653546.model.Barang;

public interface BarangService {

    //method untuk menyimpan barang baru
    void addBarang(Barang barang);

    //method untuk mendapatkan barang yang telah tersimpan
    List<Barang> getAllBarang();
    
    Barang getBarangBySku(String sku);
    
    //method untuk mengenerate sku
    String generateSKU(Barang barang);

    String determinePrefix(int type);

    long fetchLastNumberForType(String prefix);

    Barang updateBarang(Barang barang);

    boolean isMerkExist(String merk);
    

}
