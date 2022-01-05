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
    public List<Game> getDataGameXML(){
        
        List<Game> datagame =  new ArrayList<>();
        GameJpaController controller = new GameJpaController();
        try{
            datagame = controller.findGameEntities();
        }
        catch (Exception e) {}
        return datagame;
    }
    @CrossOrigin
    @GetMapping(value="/game/json", produces= {
        MediaType.APPLICATION_JSON_VALUE
    }) 
    
    @ResponseBody
    public List<Game> getDataGameJson(){
        
        List<Game> datagame =  new ArrayList<>();
        GameJpaController controller = new GameJpaController();
        try{
            datagame = controller.findGameEntities();
        }
        catch (Exception e) {}
        return datagame;
    }
    
}
