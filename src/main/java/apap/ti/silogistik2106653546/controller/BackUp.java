// package apap.ti.silogistik2106653546.controller;

// import java.math.BigInteger;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.validation.FieldError;
// import org.springframework.web.bind.annotation.*;

// import apap.ti.silogistik2106653546.dto.GudangMapper;
// import apap.ti.silogistik2106653546.dto.request.CreateGudangRequestDTO;
// import apap.ti.silogistik2106653546.dto.request.UpdateGudangRequestDTO;
// import apap.ti.silogistik2106653546.model.Barang;
// import apap.ti.silogistik2106653546.model.Gudang;
// import apap.ti.silogistik2106653546.model.GudangBarang;
// import apap.ti.silogistik2106653546.service.interfaces.BarangService;
// import apap.ti.silogistik2106653546.service.interfaces.GudangBarangService;
// import apap.ti.silogistik2106653546.service.interfaces.GudangService;
// import jakarta.validation.Valid;

// @Controller
// public class BackUp {
//     @Autowired
//     private GudangMapper gudangMapper;
//     @Autowired
//     private GudangService gudangService;
//     @Autowired
//     private GudangBarangService gudangBarangService;
//     @Autowired
//     private BarangService barangService;

//      // DAFTAR GUDANG GET
//     @GetMapping("gudang")
//     public String listGudang(Model model) {
         
//          List<Gudang> listGudang = gudangService.getAllGudang();
         
//          model.addAttribute("listGudang", listGudang);
     
//          return "gudang/viewall-gudang";
//      }


//     // DETAIL GUDANG GET
//     @GetMapping("gudang/{idGudang}")
//     public String detailGudang(@PathVariable("idGudang") BigInteger idGudang, Model model){
    
//         var gudang = gudangService.getGudangById(idGudang);

//         var gudangDTO = gudangMapper.gudangToReadGudangResponseDTO(gudang);

//         model.addAttribute("gudangDTO", gudangDTO);

//         return "gudang/view-gudang.html";
//     }


// // CARI BARANG PADA GUDANG
// @GetMapping("gudang/cari-barang")
// public String cariBarang(@RequestParam(value = "sku", required = false) String sku, Model model) {
    
//     // Populate dropdown with all available Barang
//     List<Barang> allBarang = barangService.getAllBarang();
//     model.addAttribute("allBarang", allBarang);

//     // Check if SKU is provided and is not empty
//     if(sku != null && !sku.isEmpty()) {
//         Barang selectedBarang = barangService.getBarangBySku(sku);
//         if(selectedBarang != null) {
//             List<GudangBarang> listGudangBarang = gudangBarangService.getAllGudangBarangByBarang(selectedBarang);
//             model.addAttribute("listGudangBarang", listGudangBarang);
//         }
//     }

//     return "gudang/cari-gudang-barang";
// }

// // RESTOCK BARANG PADA GUDANG GET
// @GetMapping("gudang/{idGudang}/restock-barang")
// public String restockGudang(@PathVariable("idGudang") BigInteger idGudang, Model model){
   
//    var gudang = gudangService.getGudangById(idGudang);

//    var gudangDTO = gudangMapper.gudangToUpdateGudangRequestDTO(gudang);

//    var listBarangExisting = barangService.getAllBarang(); 
   
//    model.addAttribute("listBarangExisting", listBarangExisting);
//    model.addAttribute("n", gudangDTO);

//    return "gudang/form-update-gudang.html";
//     }   

//     @PostMapping(value = "gudang/{idGudang}/restock-barang", params = {"addRow"})
//     public String addRowGudangBarang(@PathVariable BigInteger idGudang, @ModelAttribute UpdateGudangRequestDTO gudangDTO, Model model) {
   
//         // Check if gudangBarangs is null or empty, and initialize if necessary
//         if (gudangDTO.getGudangBarangs() == null || gudangDTO.getGudangBarangs().isEmpty()) {
//             gudangDTO.setGudangBarangs(new ArrayList<>());
//         }

//         // Add a new GudangBarang object to the list
//         gudangDTO.getGudangBarangs().add(new GudangBarang());

//         // Fetch all Barangs
//         var allBarang = barangService.getAllBarang();
//         System.out.println(allBarang); 
//         model.addAttribute("allBarang", allBarang);
//         model.addAttribute("gudangDTO", gudangDTO);

//         // Return back to the HTML page
//         return "gudang/form-update-gudang"; // assuming this is the name of your thymeleaf html file
// }
        
// @PostMapping("gudang/{idGudang}/restock-barang")
// public String updateGudang(@PathVariable BigInteger idGudang,  @Valid @ModelAttribute UpdateGudangRequestDTO gudangDTO,  
//                            BindingResult bindingResult,  
//                            Model model) {

//     // First, check if there are any binding errors from the form validation.
//     if(bindingResult.hasErrors()) {
//         // Return to the form page to display errors.
//         return "home"; 
//     }
    
//     // Get the current Gudang from the database.
//     Gudang currentGudang = gudangService.getGudangById(idGudang);
//     if(currentGudang == null) {
//         // Handle error: Gudang with the given id was not found.
//         return "home"; // Replace with your error page.
//     }

//     var daftarGudangBarang = gudangDTO.getGudangBarangs();
//     System.out.println("Ukurannya " + daftarGudangBarang.size());
//     for (GudangBarang gudangBarang : daftarGudangBarang) {
//         System.out.println(gudangBarang);
//         System.out.println("Gudang" + gudangBarang.getGudang());
//         System.out.println("Barang aman:" + gudangBarang.getBarang());
//         // Set the Gudang for this GudangBarang to the current Gudang.
//         gudangBarang.setGudang(currentGudang);

//         // Note: The Barang should already be set from the form binding, but if you want to ensure it's valid:
//         Barang selectedBarang = barangService.getBarangBySku(gudangBarang.getBarang().getSku());


//         if(selectedBarang == null) {
//             // Handle error: Selected Barang is not valid.
//             bindingResult.rejectValue("gudangBarangs", "Invalid Barang selected");
//             return "home"; 
//         }

//         // Set the Barang for this GudangBarang.
//         gudangBarang.setBarang(selectedBarang);
        
//         // Save the GudangBarang to the database.
//         gudangBarangService.addGudangBarang(gudangBarang);
//     }

//     // Converting gudangDTO into a newly updated Gudang
//     var updatedGudang = gudangMapper.updateGudangRequestDTOToGudang(gudangDTO);

//     // Assuming GudangService has a method to update the gudang based on the received DTO.
//     gudangService.updateGudang(updatedGudang);

//     // Redirect or return to a different view after the update is successful.
//     return "gudang/viewall-gudang";
// }

// }
