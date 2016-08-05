/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.importer.validation;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import player.importer.xml.XML_Handler;

/**
 *
 * @author Orthus
 */
public class inputValidator{

    public void Tester(String playerdata) throws TransformerConfigurationException, TransformerException {
            String Input_ID, Input_FirstName, Input_LastName, Input_DOB = "0/0/1111", Input_GamerTag;
            List<String> playerinfo = Arrays.asList(playerdata.split(","));
            //
            Input_ID = playerinfo.get(0);
            Input_FirstName = playerinfo.get(1);
            Input_LastName = playerinfo.get(2);
            if(playerinfo.size() == 5){
               Input_GamerTag=playerinfo.get(4);
            }
            else{
                Input_GamerTag = "null";
            }
            System.out.println(Input_FirstName + " " + Input_LastName);
            //
            // validate player DOB
            SimpleDateFormat test = new SimpleDateFormat("MM/dd/yyyy");
            try {
                test.parse(playerinfo.get(3));
                Input_DOB = playerinfo.get(3);
            }
            catch(Exception e){
                            System.out.println("Player's DOB is invalid!!!!");

            }
            XML_Handler Handler = new XML_Handler();
            Boolean exists = Handler.Duplicate_checker(Input_ID);
            if (exists == false){

                    System.out.println("Adding New Player");
                    Handler.XML_Writer(Input_ID, Input_FirstName, Input_LastName, Input_DOB, Input_GamerTag);

            }
            
    }
    
}
