/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.importer.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import player.importer.fileReader;
import player.importer.validation.inputValidator;

/**
 *
 * @author Orthus
 */
public class JsonReader {

    public void Reader(String Results_path) throws TransformerConfigurationException, TransformerException {
        try{
            String file = fileReader.readFile(Results_path);
            JSONObject obj = new JSONObject(file);
            JSONArray arr = obj.getJSONArray("Records");
            String playerdata = arr.getJSONObject(0).getString("Text");
            inputValidator valid = new inputValidator();
            valid.Tester(playerdata);
            System.out.println("Input Validated");
            
        }
        catch (IOException | JSONException ex) {
            Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public JsonReader(){
            
}}
