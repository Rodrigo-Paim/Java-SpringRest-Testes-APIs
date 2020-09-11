package org.serratec.correios.controller;

 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.serratec.correios.dominio.Cep;
import org.serratec.correios.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 

@RestController
@RequestMapping("/cep")
public class CepController {
    
    @Autowired
    private CepRepository repository;
        
    /* Operação: GET (obter)
     * Parâmetros de pesquisa vem pela URL como uma QueryString
     * Exemplo: http://localhost:8080/cep?cidade=Teresopolis&uf=RJ
     */
    @GetMapping("")
    public List<Cep> getTodos(@RequestParam Map<String,String> parametros) {                
            
        List<Cep> todos = (List<Cep>)repository.findAll(); 
        List<Cep> filtrada = new ArrayList<Cep>();
        
        for (Cep cep : todos) {
                    
            if (!parametros.getOrDefault("uf", cep.getUf()).equals(cep.getUf()))
                continue;
            
            if (!parametros.getOrDefault("cidade", cep.getCidade()).equals(cep.getCidade()))
                continue;
            
            if (!parametros.getOrDefault("numero", cep.getNumero()).equals(cep.getNumero()))
                continue;
            
            if (!parametros.getOrDefault("bairro", cep.getBairro()).equals(cep.getBairro()))
                continue;
            
            filtrada.add(cep);
        }
        
        return filtrada;
        
        /* Para quem tiver curiosidade sobre uso de generics :D
         * 
         return todos
            .stream()
            .filter(o -> 
                parametros.getOrDefault("uf", o.getUf()).equals(o.getUf()) &&
                parametros.getOrDefault("cidade", o.getCidade()).equals(o.getCidade()) &&
                parametros.getOrDefault("numero", o.getNumero()).equals(o.getNumero())
                parametros.getOrDefault("bairro", o.getBairro()).equals(o.getBairro())
            ).collect(Collectors.toList());
         */        
    }
    
    /* Operação: GET (obter)
     * Parâmetro de pesquisa vem pela URL como uma variável de PATH
     * Exemplo: http://localhost:8080/cep/25111000
     */
    @GetMapping("")   
    public ResponseEntity<?> getTodos(@RequestParam Map<String,String> parametros, @RequestHeader(value = "Segredo") String segredo) {               
   
        if (!"123456789".equals(segredo))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Segredo errado amigo");
       
        List<Cep> todos = (List<Cep>)repository.findAll();
        List<Cep> filtrada = new ArrayList<>();
       
        for (Cep cep : todos) {
                   
            if (!parametros.getOrDefault("uf", cep.getUf()).equals(cep.getUf()))
                continue;
           
            if (!parametros.getOrDefault("cidade", cep.getCidade()).equals(cep.getCidade()))
                continue;
           
            if (!parametros.getOrDefault("numero", cep.getNumero()).equals(cep.getNumero()))
                continue;
           
            if (!parametros.getOrDefault("bairro", cep.getBairro()).equals(cep.getBairro()))
                continue;
           
            filtrada.add(cep);
        }
        return ResponseEntity.ok().body(filtrada);
		
    }
       }
   
       
        /* Para quem tiver curiosidade sobre uso de generics :D
         *
         return todos
            .stream()
            .filter(o ->
                parametros.getOrDefault("uf", o.getUf()).equals(o.getUf()) &&
                parametros.getOrDefault("cidade", o.getCidade()).equals(o.getCidade()) &&
                parametros.getOrDefault("numero", o.getNumero()).equals(o.getNumero())
                parametros.getOrDefault("bairro", o.getBairro()).equals(o.getBairro())
            ).collect(Collectors.toList());
         */       
    