package com.arthurbf.uol_backend.services;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Service
public class GroupService {

    public List<String> getJusticeLeagueCodenames(){
        List<String> codenames = new ArrayList<>();
        try {
            URL url = new URI("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml").toURL();
            InputStream inputStream = url.openStream();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("codinome");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String codename = node.getTextContent();
                    codenames.add(codename);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codenames;
    }


    public List<String> getAvengerCodenames(){
        List<String> codenames = new ArrayList<>();
        try {
            URL url = new URI("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json").toURL();
            InputStream inputStream = url.openStream();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(inputStream));
            JSONArray jsonArray = (JSONArray) jsonObject.get("vingadores");
            for (Object obj : jsonArray) {
                JSONObject avenger = (JSONObject) obj;
                String codename = (String) avenger.get("codinome");
                codenames.add(codename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codenames;
    }
}
