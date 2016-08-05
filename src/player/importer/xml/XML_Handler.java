/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.importer.xml;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import player.importer.Configuration_Handler;
import player.importer.PlayerImporter;

/**
 *
 * @author Orthus
 */
public class XML_Handler {
       
     Configuration_Handler config = new Configuration_Handler();
     String Players_path = config.Players_path;
     
    public boolean Duplicate_checker(String ID) throws TransformerException{
         boolean exists;
         try {
             System.out.println("Testing for Duplicate player");
             DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             Document doc = dBuilder.parse(Players_path);
             Date date = new Date();
             Element root = doc.getDocumentElement();
             NodeList playerlist = root.getElementsByTagName("player");
             if (playerlist.getLength() > 0){
                 int i = 0;
                 System.out.println("Starting Test");
                 while (i < playerlist.getLength()){
                     String id = playerlist.item(i).getAttributes().getNamedItem("userid").getNodeValue();
                     if (id.equals(ID)){
                         System.out.println("Player already in system");
                         String old_points = playerlist.item(i).getChildNodes().item(11).getTextContent();
                         int points = Integer.parseInt(old_points);
                         int new_points = points + 1;
                         System.out.println(new_points);
                         playerlist.item(i).getChildNodes().item(11).setTextContent(Integer.toString(new_points));
                         DOMSource source = new DOMSource(doc);
                         TransformerFactory transformerFactory = TransformerFactory.newInstance();
                         Transformer transformer = transformerFactory.newTransformer();
                         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                         StreamResult result = new StreamResult(Players_path);
                         transformer.transform(source, result);
                         exists = true;
                         return exists;
                     }
                     else {
                         i++;
                     }
                 }
                 exists = false;
                 System.out.println("This is a new Player");
                 return exists;
             }
         } catch (ParserConfigurationException | SAXException | IOException ex) {
             Logger.getLogger(XML_Handler.class.getName()).log(Level.SEVERE, null, ex);
         }
         exists = false;
         return exists;
    }
    
    
    public void XML_Writer(String ID, String First, String Last, String DOB, String GamerTag) throws TransformerConfigurationException{
         try {
            System.out.println("Writing new player" + ID);
             DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             Document doc = dBuilder.parse(Players_path);
             Date date = new Date();
             SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
             String formattedDate = sdf.format(date);
             Element root = doc.getDocumentElement();
             NodeList playerlist = root.getElementsByTagName("player");
             Element newUser = doc.createElement("player");
             newUser.setAttribute("userid", ID);
             Element firstname = doc.createElement("firstname");
             firstname.appendChild(doc.createTextNode(First));
             newUser.appendChild(firstname);
             Element lastname = doc.createElement("lastname");
             lastname.appendChild(doc.createTextNode(Last));
             newUser.appendChild(lastname);
             Element birthdate = doc.createElement("birthdate");
             birthdate.appendChild(doc.createTextNode(DOB));
             newUser.appendChild(birthdate);
             Element creationdate = doc.createElement("creationdate");
             creationdate.appendChild(doc.createTextNode(formattedDate));
             newUser.appendChild(creationdate);
             Element lastmodifieddate = doc.createElement("lastmodifieddate");
             lastmodifieddate.appendChild(doc.createTextNode(formattedDate));
             newUser.appendChild(lastmodifieddate);
             Element points = doc.createElement("Points");
             points.appendChild(doc.createTextNode("1"));
             newUser.appendChild(points);
             Element gamertag = doc.createElement("GamerTag");
             gamertag.appendChild(doc.createTextNode(GamerTag));
             newUser.appendChild(gamertag);
             root.appendChild(newUser);
             DOMSource source = new DOMSource(doc);
             TransformerFactory transformerFactory = TransformerFactory.newInstance();
             Transformer transformer = transformerFactory.newTransformer();
             transformer.setOutputProperty(OutputKeys.INDENT, "yes");
             StreamResult result = new StreamResult(Players_path);
             transformer.transform(source, result);
             System.out.println("Added to Player Database, Player given point");
         } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
             Logger.getLogger(XML_Handler.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    
    }
    
}
