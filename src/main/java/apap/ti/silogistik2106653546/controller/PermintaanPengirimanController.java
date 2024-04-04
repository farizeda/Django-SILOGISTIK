package apap.ti.silogistik2106653546.controller;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import apap.ti.silogistik2106653546.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106653546.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106653546.model.PermintaanPengiriman;
import apap.ti.silogistik2106653546.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106653546.service.interfaces.BarangService;
import apap.ti.silogistik2106653546.service.interfaces.KaryawanService;
import apap.ti.silogistik2106653546.service.interfaces.PermintaanPengirimanBarangService;
import apap.ti.silogistik2106653546.service.interfaces.PermintaanPengirimanService;
import jakarta.validation.Valid;


@Controller
public class PermintaanPengirimanController {
    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private PermintaanPengirimanBarangService permintaanPengirimanBarangService;

    @Autowired
    private PermintaanPengirimanMapper permintaanPengirimanMapper;

    // DAFTAR PERMINTAAN PENGIRIMAN
    @GetMapping("permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model) {
        
        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman(); 
        // List<ReadPermintaanPengirimanResponseDTO> listPermintaanPengirimanDisplay = new ArrayList<>();
        
        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        model.addAttribute("permintaanPengirimanService", permintaanPengirimanService);
        return "permintaan-pengiriman/viewall-permintaan-pengiriman"; 
    }


     // DETAIL PERMINTAAN PENGIRIMAN
     @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}")
     public String listPermintaanPengiriman(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model) {
         
        PermintaanPengiriman permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        var waktuPermintaan = permintaanPengiriman.getWaktuPermintaan();
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(waktuPermintaan, now);
        var durationDisplay = Math.abs(duration.toHours());

        model.addAttribute("durationDisplay", durationDisplay);
        
        model.addAttribute("permintaanPengiriman", permintaanPengiriman);
        model.addAttribute("permintaanPengirimanService", permintaanPengirimanService);
        var permintaanPengirimanDTO = permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanDTO(permintaanPengiriman);
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
         return "permintaan-pengiriman/view-permintaan-pengiriman"; 
     }


     // Cancel Permintaan Pengiriman
     @GetMapping("/permintaan-pengiriman/{idPermintaanPengiriman}/cancel")
     public String cancelPermintaanPengiriman(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model){
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        permintaanPengirimanService.cancelPermintaanPengiriman(permintaanPengiriman);
        
        var noPengiriman = permintaanPengiriman.getNoPengiriman();

        model.addAttribute("noPengiriman", noPengiriman);
        return "permintaan-pengiriman/success-delete-permintaan-pengiriman";
     }

     // RESTOCK BARANG PADA GUDANG GET
        @GetMapping("permintaan-pengiriman/tambah")
        public String addPermintaanPengiriman(Model model){
        
        var allKaryawan = karyawanService.getlAllKaryawan();
        var allBarang = barangService.getAllBarang();
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();

        model.addAttribute("allBarang", allBarang);
        model.addAttribute("allKaryawan", allKaryawan);
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        return "permintaan-pengiriman/form-create-permintaan-pengiriman.html";
    } 

    @PostMapping(value = "permintaan-pengiriman/tambah", params = {"addRow"})
    public String addPermintaanPengirimanBarang(@ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO, Model model) {
   
        // Check if gudangBarangs is null or empty, and initialize if necessary
        if (permintaanPengirimanDTO.getPermintaanPengirimanBarangs() == null || permintaanPengirimanDTO.getPermintaanPengirimanBarangs().isEmpty()) {
            permintaanPengirimanDTO.setPermintaanPengirimanBarangs(new ArrayList<>());
        }
   
        // Add a new GudangBarang object to the list
        permintaanPengirimanDTO.getPermintaanPengirimanBarangs().add(new PermintaanPengirimanBarang());
   
        // Fetch all Barangs & Karywana
        var allKaryawan = karyawanService.getlAllKaryawan();
        var allBarang = barangService.getAllBarang();
   
        // Thymeleaf render
        model.addAttribute("allBarang", allBarang);
        model.addAttribute("allKaryawan", allKaryawan);
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
   
        // Return back to the HTML page
        return "permintaan-pengiriman/form-create-permintaan-pengiriman.html"; // assuming this is the name of your thymeleaf html file
   }

   @PostMapping(value = "permintaan-pengiriman/tambah")
    public String postPermintaanPengiriman(@Valid @ModelAttribute  CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO,  
                           BindingResult bindingResult,  
                           Model model) {

    // First, check if there are any binding errors from the form validation.
    if (bindingResult.hasErrors()) {
        List<String> errors = bindingResult.getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        FieldError fieldError = (FieldError) error;
                        return fieldError.getField() + ": " + error.getDefaultMessage();
                    }
                    return error.getDefaultMessage();
                })
                .collect(Collectors.toList());

        model.addAttribute("errors", errors);
        return "error/error-viewall";
    }

    
    var newPermintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanPengirimanDTO);

    DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate tempTime = LocalDate.parse(permintaanPengirimanDTO.getTanggalPengirimanStr(), formatter3);
    newPermintaanPengiriman.setTanggalPengiriman(tempTime);


    var listPermintaanPengirimanBarang = permintaanPengirimanDTO.getPermintaanPengirimanBarangs();

    Integer totalBarang = 0;

    for (PermintaanPengirimanBarang permintaanPengirimanBarang : listPermintaanPengirimanBarang) {
        totalBarang += permintaanPengirimanBarang.getKuantitasPesanan();
    }

    permintaanPengirimanService.addPermintaanPengiriman(newPermintaanPengiriman, totalBarang);

    for (PermintaanPengirimanBarang permintaanPengirimanBarang : listPermintaanPengirimanBarang) {
        permintaanPengirimanBarang.setPermintaanPengiriman(newPermintaanPengiriman);
        permintaanPengirimanBarangService.add(permintaanPengirimanBarang);
    }
    
    var noPengiriman = newPermintaanPengiriman.getNoPengiriman();

    model.addAttribute("noPengiriman", noPengiriman);

    return "permintaan-pengiriman/success-create-permintaan-pengiriman";
   
    }

    // BONUS
    @GetMapping("filter-permintaan-pengiriman")
    public String bonus(@RequestParam(value = "start-date", required = false) LocalDate startDate, 
                        @RequestParam(value = "end-date", required = false) LocalDate endDate,
                        @RequestParam(value = "sku", required = false) String SKU, Model model)
    {

        // Populate the dropdown
        var allBarang = barangService.getAllBarang();
        model.addAttribute("allBarang", allBarang);

        var filteredPermintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanBetweenDates(startDate, endDate);

        List<PermintaanPengiriman> listPengiriman = new ArrayList<>();

        for (PermintaanPengiriman permintaanPengiriman : filteredPermintaanPengiriman) {
            var listPermintaanPengirimanBarang = permintaanPengiriman.getPermintaanPengirimanBarangs();
            for (PermintaanPengirimanBarang permintaanPengirimanBarang : listPermintaanPengirimanBarang) {
                if(permintaanPengirimanBarang.getBarang().getSku().equals(SKU)){
                     listPengiriman.add(permintaanPengiriman);
                    
                }
            }
        }

        model.addAttribute("listPengiriman", listPengiriman);
        model.addAttribute("permintaanPengirimanService", permintaanPengirimanService);
        return "permintaan-pengiriman/bonus";
    }


}
