package apap.ti.silogistik2106653546.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import apap.ti.silogistik2106653546.service.interfaces.BarangService;
import apap.ti.silogistik2106653546.service.interfaces.GudangService;
import apap.ti.silogistik2106653546.service.interfaces.KaryawanService;
import apap.ti.silogistik2106653546.service.interfaces.PermintaanPengirimanService;
import jakarta.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    GudangService gudangService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    BarangService barangService;


    @GetMapping("/")
    public String home(Model model) {

        var allGudang = gudangService.getAllGudang();
        var allKaryawan = karyawanService.getlAllKaryawan();
        var allBarang = barangService.getAllBarang();
        var allPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();


        if(allGudang != null && !allGudang.isEmpty() ){
            model.addAttribute("jumlahGudang", allGudang.size());
        }

        if(allKaryawan != null && !allKaryawan.isEmpty() ){
            model.addAttribute("jumlahKaryawan", allKaryawan.size());
        }

        if(allBarang != null && !allBarang.isEmpty() ){
            model.addAttribute("jumlahBarang", allBarang.size());
        }

        if(allPermintaanPengiriman != null && !allPermintaanPengiriman.isEmpty() ){
            model.addAttribute("jumlahPermintaanPengiriman", allPermintaanPengiriman.size());
        }

        return "home";
    }
}
