package br.com.uolhost.cadastrodejogadores.services;

import br.com.uolhost.cadastrodejogadores.dto.JsonDTO;
import br.com.uolhost.cadastrodejogadores.dto.XmlDTO;
import br.com.uolhost.cadastrodejogadores.exception.ResourceHttpIsNotAvailableException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@AllArgsConstructor
public class JogadorService {

    private final ObjectMapper mapper;
    private final XmlMapper xmlMapper = new XmlMapper();

    @Value("${url.json}")
    private String urlJson;
    @Value("${url.xml}")
    private String urlXml;

    public JsonDTO requestVingadores(){
        try{
            return this.mapper.readValue(this.requestByUrl(urlJson), JsonDTO.class);
        }catch (IOException | ResourceHttpIsNotAvailableException e){
            e.printStackTrace();
            return null;
        }
    }

    public XmlDTO requestLiga() {
        try {
            return this.xmlMapper.readValue(requestByUrl(urlXml), XmlDTO.class);
        } catch (IOException | ResourceHttpIsNotAvailableException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String requestByUrl(String url) throws ResourceHttpIsNotAvailableException {
        try {
            final StringBuilder result = new StringBuilder();
            final URL obj = new URL(url);
            final HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(RequestMethod.GET.name());
            con.setRequestProperty("User-Agent", "");
            final int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                final InputStream in = new BufferedInputStream(con.getInputStream());
                final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line + "\n");
                }
            }
            return result.toString();
        }catch (IOException e) {
            e.printStackTrace();
            throw new ResourceHttpIsNotAvailableException();
        }
    }
}
