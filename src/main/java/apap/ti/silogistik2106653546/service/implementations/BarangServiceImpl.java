package apap.ti.silogistik2106653546.service.implementations;

import apap.ti.silogistik2106653546.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106653546.model.Barang;
import apap.ti.silogistik2106653546.repository.BarangDb;
import apap.ti.silogistik2106653546.repository.GudangBarangDb;
import apap.ti.silogistik2106653546.service.interfaces.BarangService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarangServiceImpl implements BarangService {

    @Autowired
    BarangDb barangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void addBarang(Barang barang){
        String generatedSKU = generateSKU(barang);
        barang.setSku(generatedSKU);
        barangDb.save(barang);
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }

    @Override
    public String generateSKU(Barang barang) {
        String prefix = determinePrefix(barang.getTipeBarang());
        long lastNumber = fetchLastNumberForType(prefix);
        long nextNumber = lastNumber + 1;
        return prefix + String.format("%03d", nextNumber);
    }

    @Override
    public String determinePrefix(int type) {
        switch(type) {
            case 1: return "ELEC";
            case 2: return "CLOT";
            case 3: return "FOOD";
            case 4: return "COSM";
            case 5: return "TOOL";
            default: throw new IllegalArgumentException("Invalid type");
        }
    }

    @Override
    public long fetchLastNumberForType(String prefix) {
        return barangDb.countBySkuStartingWith(prefix);
    }

    @Override
    public Barang getBarangBySku(String sku) {
        Barang barang = barangDb.findBySku(sku).orElse(null);
        if (barang != null) {
            System.out.println("Barang found with SKU: " + sku);
        } else {
            System.out.println("No Barang found with SKU: " + sku);
        }
        return barang;
    }

    @Override
    public Barang updateBarang(Barang barangFromDto) {
        Barang barang = getBarangBySku(barangFromDto.getSku());
        if(barang != null){
            barang.setMerkBarang(barangFromDto.getMerkBarang());
            barang.setHargaBarang(barangFromDto.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;
    }

    @Override
    public boolean isMerkExist(String merk) {
        var allBarang = getAllBarang();
        for (Barang barang : allBarang) {
            if(barang.getMerkBarang().equalsIgnoreCase(merk)){
                return true;
            }
        }
        return false;
    }
    
}
