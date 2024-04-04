package apap.ti.silogistik2106653546.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import aj.org.objectweb.asm.Type;
import apap.ti.silogistik2106653546.dto.BarangMapper;
import apap.ti.silogistik2106653546.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106653546.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106653546.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106653546.model.Barang;
import apap.ti.silogistik2106653546.model.GudangBarang;
import apap.ti.silogistik2106653546.service.interfaces.BarangService;
import apap.ti.silogistik2106653546.service.interfaces.GudangBarangService;
import jakarta.validation.Valid;

@Controller
public class BarangController {
    @Autowired
    private GudangBarangService gudangBarangService;
    @Autowired
    private BarangMapper barangMapper;
    @Autowired
    private BarangService barangService;

    // DAFTAR BARANG
    @GetMapping("barang")
    public String listBarang(Model model, @RequestParam(value = "search", required = false) String search) {
        
        // Retrieve all Barang
        List<Barang> barangs = barangService.getAllBarang(); 

        // Create empty DTO
        List<ReadBarangResponseDTO> listBarangDisplay = new ArrayList<>();
        
       
        for (Barang barang : barangs) {

            // Retrieve each stok barang in gudang Service
            int totalStock = gudangBarangService.getStock(barang);

            // Creates a DTO that maps barang and stock
            ReadBarangResponseDTO barangDTO = barangMapper.barangToReadBarangResponseDTO(barang, totalStock);

            // Adds to list of DTOs
            listBarangDisplay.add(barangDTO);
        }
        

        model.addAttribute("listBarangDisplay", listBarangDisplay);

        return "barang/viewall-barang"; 
    }

     // DETAIL BARANG
     @GetMapping("barang/{sku}")
     public String detailBarang(@PathVariable("sku") String sku, Model model){
         
        // Retrieve barang by sku and its stock
         var barang = barangService.getBarangBySku(sku);
         int totalStock = gudangBarangService.getStock(barang);
             
         // Use MapStruct to convert to DTO
         ReadBarangResponseDTO barangDTO = barangMapper.barangToReadBarangResponseDTO(barang, totalStock);
        
         model.addAttribute("barangDTO", barangDTO);
 
         return "barang/view-barang.html";
     }
 
      // UBAH BARANG GetMapping
      @GetMapping("barang/ubah/{sku}")
      public String formUpdateBarang(@PathVariable("sku") String sku, Model model) {
         
         var barang = barangService.getBarangBySku(sku);
 
         var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);
         model.addAttribute("barangDTO", barangDTO);
      
         return "barang/form-update-barang";
      }

    // TAMBAH BARANG GETMAPPING
    @GetMapping("barang/tambah")
    public String formAddBarang(Model model) {
        //membuat DTO baru sebagai isian form pengguna
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);
    
        return "barang/form-create-barang";
    }

    // TAMBAH BARANG POSTMAPPING
    @PostMapping("barang/tambah")
    public String addBarang(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model) {
        
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

        if (barangService.isMerkExist(barangDTO.getMerkBarang())){
            var errorMessage = "Maaf, merk barang sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error/error-view";
        }

        // Create Barang using barangMapper
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);

        // Memanggil Service addBarang
        barangService.addBarang(barang);
        
        model.addAttribute("sku", barang.getSku());

        return "barang/success-create-barang";
    }



       // Update Barang PostMapping
     @PostMapping("barang/ubah/{sku}")
     public String formUpdateBarang(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, @PathVariable("sku") String sku, BindingResult bindingResult,  Model model) {

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

        if (barangService.isMerkExist(barangDTO.getMerkBarang())){
            var errorMessage = "Maaf, merk barang sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error/error-view";
        }
    
        var barangFromDto = barangMapper.updateBarangRequestDTOToBarang(barangDTO);

        var barang = barangService.updateBarang(barangFromDto);
  
        model.addAttribute("sku", barang.getSku());
     
        return "barang/success-update-barang";
     }
 

}
