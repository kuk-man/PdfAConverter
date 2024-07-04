package pdfa3;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pdfbox.Convert;

@SpringBootApplication
@RestController
@Service
public class Pdfa3 {

	public static void main(String[] args) {
		SpringApplication.run(Pdfa3.class, args);
	}

	@PostMapping("/pdfa3")
    public ResponseEntity<Map<String,Object>> handleData(@RequestBody Pdfa3Message message) {
		try {
			String pdfa3 = new Convert().getSignedDocument(message.getData(), message.getFile(), message.getFileName());

			Map<String, Object> response = new HashMap<>();
			response.put("pdf", pdfa3);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}
    }
}