package player.importer;
import java.io.IOException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import player.importer.json.JsonReader;

/**
@author Orthus
*/
public class PlayerImporter {
    public static void main(String[] args) throws TransformerConfigurationException, TransformerException, IOException {
            Configuration_Handler config = new Configuration_Handler();
            Log_Handler Logs = new Log_Handler();
            
            String Results_path = config.Results_path;
            String Players_path = config.Players_path;
            System.out.println(Results_path + " and " + Players_path);
            JsonReader Json_Reader = new JsonReader();
            Json_Reader.Reader(Results_path);
    }
}