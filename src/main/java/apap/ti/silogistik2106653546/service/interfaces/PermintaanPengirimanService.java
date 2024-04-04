package apap.ti.silogistik2106653546.service.interfaces;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import apap.ti.silogistik2106653546.model.PermintaanPengiriman;

public interface PermintaanPengirimanService {
    void addPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman, Integer totalBarang);

    BigInteger generateIdPermintaanPengiriman();

    List<PermintaanPengiriman> getAllPermintaanPengiriman();

    PermintaanPengiriman getPermintaanPengirimanById(long id);

    String generateNomorPengiriman(Integer kuantitasPesanan, Integer jenisLayanan, String formattedTime);

    void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);

    String convertWaktuPermintaan(PermintaanPengiriman permintaanPengiriman);
    
    List<PermintaanPengiriman> getPermintaanPengirimanBetweenDates(LocalDate startDate, LocalDate endDate);

}
