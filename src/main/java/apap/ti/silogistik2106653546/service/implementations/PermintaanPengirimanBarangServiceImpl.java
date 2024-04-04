package apap.ti.silogistik2106653546.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106653546.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106653546.repository.PermintaanPengirimanBarangDb;
import apap.ti.silogistik2106653546.service.interfaces.PermintaanPengirimanBarangService;

@Service
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService {

    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Override
    public void add(PermintaanPengirimanBarang permintaanPengirimanBarang) {
        
        permintaanPengirimanBarangDb.save(permintaanPengirimanBarang);
    }
    
}
