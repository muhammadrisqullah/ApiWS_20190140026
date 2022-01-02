/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service.exercise.apiws;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
 */
@Controller
public class DatabaseController {
    @GetMapping(value="/game/xml", produces ={
        MediaType.APPLICATION_XML_VALUE
        })
    
    @ResponseBody
    public List<Game> getDataMahasiswaXML(){
        
        List<Game> datamahasiswa =  new ArrayList<>();
        GameJpaController controller = new GameJpaController();
        try{
            datamahasiswa = controller.findGameEntities();
        }
        catch (Exception e) {}
        return datamahasiswa;
    }
    @CrossOrigin
    @GetMapping(value="/game/json", produces= {
        MediaType.APPLICATION_JSON_VALUE
    }) 
    
    @ResponseBody
    public List<Game> getDataMahasiswaJson(){
        
        List<Game> datamahasiswa =  new ArrayList<>();
        GameJpaController controller = new GameJpaController();
        try{
            datamahasiswa = controller.findGameEntities();
        }
        catch (Exception e) {}
        return datamahasiswa;
    }
    
}
