package apap.ti.silogistik2106653546.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import apap.ti.silogistik2106653546.dto.GudangMapper;
import apap.ti.silogistik2106653546.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106653546.dto.request.FormGudangBarangDTO;
import apap.ti.silogistik2106653546.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106653546.model.Barang;
import apap.ti.silogistik2106653546.model.Gudang;
import apap.ti.silogistik2106653546.model.GudangBarang;
import apap.ti.silogistik2106653546.service.interfaces.BarangService;
import apap.ti.silogistik2106653546.service.interfaces.GudangBarangService;
import apap.ti.silogistik2106653546.service.interfaces.GudangService;
import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.validation.Valid;

@Controller
public class GudangController {
    @Autowired
    private GudangMapper gudangMapper;
    @Autowired
    private GudangService gudangService;
    @Autowired
    private GudangBarangService gudangBarangService;
    @Autowired
    private BarangService barangService;

     // DAFTAR GUDANG GET
    @GetMapping("gudang")
    public String listGudang(Model model) {
         
         List<Gudang> listGudang = gudangService.getAllGudang();
         
         model.addAttribute("listGudang", listGudang);
     
         return "gudang/viewall-gudang";
     }


    // DETAIL GUDANG GET
    @GetMapping("gudang/{idGudang}")
    public String detailGudang(@PathVariable("idGudang") BigInteger idGudang, Model model){
    
        var gudang = gudangService.getGudangById(idGudang);

        var gudangDTO = gudangMapper.gudangToReadGudangResponseDTO(gudang);

        model.addAttribute("gudangDTO", gudangDTO);

        return "gudang/view-gudang.html";
    }


// CARI BARANG PADA GUDANG
@GetMapping("gudang/cari-barang")
public String cariBarang(@RequestParam(value = "sku", required = false) String sku, Model model) {
    
    // Populate dropdown with all available Barang
    List<Barang> allBarang = barangService.getAllBarang();
    model.addAttribute("allBarang", allBarang);

    // Check if SKU is provided and is not empty
    if(sku != null && !sku.isEmpty()) {
        Barang selectedBarang = barangService.getBarangBySku(sku);
        if(selectedBarang != null) {
            List<GudangBarang> listGudangBarang = gudangBarangService.getAllGudangBarangByBarang(selectedBarang);
            model.addAttribute("listGudangBarang", listGudangBarang);
        }
    }

    return "gudang/cari-gudang-barang";
}

// RESTOCK BARANG PADA GUDANG GET
@GetMapping("gudang/{idGudang}/restock-barang")
public String restockGudang(@PathVariable("idGudang") BigInteger idGudang, Model model){
   
   var gudang = gudangService.getGudangById(idGudang);

   var allBarang = barangService.getAllBarang();

   var gudangBarangDTO = new FormGudangBarangDTO();
   var existingGudangBarang = gudang.getGudangBarangs();

   if(existingGudangBarang != null){
          gudangBarangDTO.setGudangBarangs(existingGudangBarang);
   }else{
       gudangBarangDTO.setGudangBarangs(new ArrayList<>());
   }

   model.addAttribute("idGudang", gudang.getIdGudang());
   model.addAttribute("namaGudang", gudang.getNamaGudang());
   model.addAttribute("alamatGudang", gudang.getAlamatGudang());
   model.addAttribute("allBarang", allBarang);
   model.addAttribute("gudangBarangDTO", gudangBarangDTO);

   return "gudang/form-update-gudang.html";
 }   

 @PostMapping(value = "gudang/{idGudang}/restock-barang", params = {"addRow"})
 public String addRowGudangBarang(@PathVariable BigInteger idGudang, @ModelAttribute FormGudangBarangDTO gudangBarangDTO, Model model) {

     // Check if gudangBarangs is null or empty, and initialize if necessary
     if (gudangBarangDTO.getGudangBarangs() == null || gudangBarangDTO.getGudangBarangs().isEmpty()) {
        gudangBarangDTO.setGudangBarangs(new ArrayList<>());
     }

     // Add a new GudangBarang object to the list
     gudangBarangDTO.getGudangBarangs().add(new GudangBarang());

     // Fetch all Barangs
     var allBarang = barangService.getAllBarang();
     var gudang = gudangService.getGudangById(idGudang);

     // Thymeleaf render
     model.addAttribute("idGudang", gudang.getIdGudang());
     model.addAttribute("namaGudang", gudang.getNamaGudang());
     model.addAttribute("alamatGudang", gudang.getAlamatGudang());
     model.addAttribute("allBarang", allBarang);
     model.addAttribute("gudangBarangDTO", gudangBarangDTO);

     // Return back to the HTML page
     return "gudang/form-update-gudang"; // assuming this is the name of your thymeleaf html file
}


@PostMapping("gudang/{idGudang}/restock-barang")
public String updateGudang(@PathVariable BigInteger idGudang,  @Valid @ModelAttribute FormGudangBarangDTO formGudangBarangDTO,  
                           BindingResult bindingResult,  
                           Model model) {

    // First, check if there are any binding errors from the form validation.
    if(bindingResult.hasErrors()) {
        // Return to the form page to display errors.
        return "error/400"; 
    }
    
    // Get the current Gudang from the database.
    Gudang currentGudang = gudangService.getGudangById(idGudang);
    if(currentGudang == null) {
        // Handle error: Gudang with the given id was not found.
        return "error/400"; // Replace with your error page.
    }

    if(formGudangBarangDTO.getGudangBarangs() != null){
        for (GudangBarang gudangBarang : formGudangBarangDTO.getGudangBarangs()) {
            var currentBarang = barangService.getBarangBySku(gudangBarang.getBarang().getSku());
            gudangBarang.setGudang(currentGudang);
            gudangBarang.setBarang(currentBarang);
            gudangBarangService.updateGudangBarang(gudangBarang);
     }
 
    }

     model.addAttribute("idGudang", idGudang);
    return "gudang/success-update-gudang";
    }

}
