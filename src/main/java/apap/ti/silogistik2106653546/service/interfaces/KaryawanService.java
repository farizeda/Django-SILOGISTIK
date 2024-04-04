package apap.ti.silogistik2106653546.service.interfaces;

import java.util.List;

import apap.ti.silogistik2106653546.model.Karyawan;

public interface KaryawanService {
    void addKaryawan(Karyawan karyawan);


    List<Karyawan> getlAllKaryawan();
}
