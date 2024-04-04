package apap.ti.silogistik2106653546.service.implementations;

import apap.ti.silogistik2106653546.model.PermintaanPengiriman;
import apap.ti.silogistik2106653546.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106653546.service.interfaces.PermintaanPengirimanService;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;


    @Override
    public void addPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman, Integer totalBarang) {
         // Get the current time
         LocalDateTime currentTime = LocalDateTime.now();

         // Define the formatter
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
 
         // Format the time to a string
         String formattedTime = currentTime.format(formatter);

        // permintaanPengiriman.setIdPermintaanPengiriman(generateIdPermintaanPengiriman());
        permintaanPengiriman.setNoPengiriman(generateNomorPengiriman(totalBarang, permintaanPengiriman.getJenisLayanan(), formattedTime));
        permintaanPengiriman.setWaktuPermintaan(currentTime);
        permintaanPengirimanDb.save(permintaanPengiriman);
    }

    @Override
    public BigInteger generateIdPermintaanPengiriman() {
        BigInteger latestId = permintaanPengirimanDb.findHighestIdPermintaanPengiriman().orElse(BigInteger.ZERO);
        return latestId.add(BigInteger.ONE);
    }

    @Override 
    public String generateNomorPengiriman(Integer kuantitasPesanan, Integer jenisLayanan, String formattedTime){
        String noPengiriman = "REQ";

        String quantity = String.format("%02d", kuantitasPesanan > 99 ? kuantitasPesanan % 100 : kuantitasPesanan);

        String[] types = {"SAM", "KIL", "REG", "HEM"};
        String type = types[jenisLayanan - 1];
      
        noPengiriman = noPengiriman + quantity + type + formattedTime;

        return noPengiriman;
    }


    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
       return permintaanPengirimanDb.findAll();
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(long id) {
        for (PermintaanPengiriman permintaanPengiriman: getAllPermintaanPengiriman()) {
            if (permintaanPengiriman.getIdPermintaanPengiriman() == id) {
                return permintaanPengiriman;
            }
        }
        return null;
    }

    @Override
    public void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengirimanDb.delete(permintaanPengiriman);
    }

    @Override
    public String convertWaktuPermintaan(PermintaanPengiriman permintaanPengiriman) {
        LocalDateTime waktuPermintaan = permintaanPengiriman.getWaktuPermintaan();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        return waktuPermintaan.format(formatter);
    }

    @Override
    public List<PermintaanPengiriman> getPermintaanPengirimanBetweenDates(LocalDate startDate, LocalDate endDate) {
        return permintaanPengirimanDb.findByTanggalPengirimanBetween(startDate, endDate);
    }
}
