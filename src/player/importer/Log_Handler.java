/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.importer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Orthus
 */
public class Log_Handler {
    File GamerTags = new File("GamerTags.txt");
    File ID_List = new File("ID_List.txt");
    public Log_Handler() throws IOException{
        if(!GamerTags.exists()){
            GamerTags.createNewFile();
        }
        if(!ID_List.exists()){
            ID_List.createNewFile();
        }
        FileOutputStream Gamer_Output = new FileOutputStream(GamerTags, true);
        FileOutputStream ID_Output = new FileOutputStream(ID_List, true);

    }
    
    
}
