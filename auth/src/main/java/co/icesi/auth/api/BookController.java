package co.icesi.auth.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    List<Map<String, ?>> list = new ArrayList<>();
    
    @PostMapping
    public Map save(@RequestBody Map<String, ?> body){
        list.add(body);
        return body;
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam String tag){
        List<Map<String, ?>> listTmp = new ArrayList<>();

        for (Map<String,?> elem : list) {
            if(elem.containsKey("tags")){
                if(elem.get("tags").toString().contains(tag)){
                    listTmp.add(elem);
                }
            }
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(205)).body(listTmp);
        
    
    }
    

}
