package com.appsnacks.snacks_inventary.controllers;

import com.appsnacks.snacks_inventary.models.Snack;
import com.appsnacks.snacks_inventary.service.iSnack;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Snack_Controller {

    @Autowired
    private iSnack snackService;
    
   
    
    @GetMapping({"/", "/snacks"})
    private String getAllsnacks(Model model) {
        model.addAttribute("snacks", snackService.getAllSnack());
        return "snacks";
    }
     @GetMapping("/snacks/view/{id}")
    private String snackView(@PathVariable int id, Model model){
        Snack s = snackService.getSnackById(id);
        String ruta = s.getPathImg();
        model.addAttribute("ruta", ruta);
        model.addAttribute("snack", s );
        return "/snackView";
    }

    @GetMapping({"/snacks/new"})
    private String showChargeForm(Model model) {

        Snack snack = new Snack();
        model.addAttribute("snack", snack);

        //retorna un archivo html 
        return "create_snack";
    }

    @PostMapping({"/charge-snacks"})
    private String chargeSnack(@ModelAttribute("snack") Snack snack, @RequestParam("file") MultipartFile img) {
        try {
          
            if (img.isEmpty()) {
                // Manejar el caso en que no se seleccionó ningún archivo                
                return "redirect:/problem?error=noFile";
                   
            }
            if(!(img.getContentType().endsWith("jpg") || img.getContentType().endsWith("jpeg") || img.getContentType().endsWith("png"))) {
                return "redirect:/problem?error=invalidFileType";
            }  
            // Limitar el tamaño del archivo (en bytes)
            long maxSize = 1024 * 1024 * 5; // 5 MB
             if(img.getSize() > maxSize) {                  
                return "redirect:/problem?error=fileSizeExceeded";
            }
            
            // Generar nombre único y ruta relativa
            String filename = UUID.randomUUID() + "-" + img.getOriginalFilename();
            String relativePath =  filename;

            // Obtener la ruta absoluta donde se guardarán las imágenes
            String dirUpload = "src//main//resources//static/images";
            Path fullPath = Paths.get(dirUpload, filename);

            // Guardar el archivo en el sistema de archivos del servidor
            Files.write(fullPath, img.getBytes());
            
            // Guardar la ruta relativa en el producto
            snack.setPathImg(relativePath);
        } catch (IOException ex) {
            ex.printStackTrace();
            return "redirect:/problem?error=internalError";
        }
        snackService.chargeSnack(snack);
        return "redirect:/snacks";
    }
    
   
    
    @GetMapping("snacks/edit/{id}")
    private String showEditForm(@PathVariable int id, Model model) {
        Snack s = snackService.getSnackById(id);
        model.addAttribute("snack", s);

        return "edit";
    }

    @PostMapping("/snackedit")
    private String updateSnack(@ModelAttribute("snack") Snack snack, @RequestParam("file") MultipartFile img) {
        Snack snackFound = snackService.getSnackById(snack.getId());
        snackFound.setId(snack.getId());
        snackFound.setProduct(snack.getProduct());
        snackFound.setPrice(snack.getPrice());
        snackFound.setExpirationDate(snack.getExpirationDate());
        
        if(snack.getPathImg() == null){
        
        String r = snackFound.getPathImg();
        String dirImgs = "src//main//resources//static/images";
        
        Path rp = Paths.get(dirImgs, r);
        try {
            Files.deleteIfExists(rp);
        } catch (IOException ex) {
            Logger.getLogger(Snack_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  
        // Generar nombre único y ruta relativa
            String filename = UUID.randomUUID() + "-" + img.getOriginalFilename();
            String relativePath =  filename;

            // Obtener la ruta absoluta donde se guardarán las imágenes
            String dirUpload = "src//main//resources//static/images";
            Path fullPath = Paths.get(dirUpload, filename);

        try {
            // Guardar el archivo en el sistema de archivos del servidor
            Files.write(fullPath, img.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Snack_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            // Guardar la ruta relativa en la db;
            snackFound.setPathImg(relativePath);
        }
        snackService.updateSnack(snackFound);

        return "redirect:/snacks";
    }
    

    @GetMapping({"/snacks/{id}"})
    private String deleteSnack(@PathVariable int id) {
        Snack s = snackService.getSnackById(id);
        String r = s.getPathImg();
        String dirImgs = "src//main//resources//static/images";
        
        Path rp = Paths.get(dirImgs, r);
        try {
            Files.deleteIfExists(rp);
        } catch (IOException ex) {
            Logger.getLogger(Snack_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        snackService.deleteSnack(id);
        return "redirect:/snacks";
    }
    
    

    @GetMapping("/problem")
    public String showErrors() {
      
        return "errorView";
    }
}
