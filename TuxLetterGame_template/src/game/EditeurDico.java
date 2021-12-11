/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author User
 */
public class EditeurDico {
    Document documentDOM;
    
    void lireDOM(String fichier) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        documentDOM = db.parse("src/xml/" + fichier);
    }
    
    void ecrireDOM(String fichier) throws TransformerConfigurationException, TransformerException{
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        DOMSource domSource = new DOMSource(documentDOM);
        StreamResult streamResult = new StreamResult(new File("src/xml/" + fichier));
        transformer.transform(domSource, streamResult);
    }
    
    void ajouterMot(String mot, int niveau){
        Element root = documentDOM.getDocumentElement();
        Element motNode = documentDOM.createElement("mot");
        Text motText = documentDOM.createTextNode(mot);
        motNode.appendChild(motText);
        motNode.setTextContent(mot);
        motNode.setAttribute("niveau", String.format("%d", niveau));
        root.appendChild(motNode);
        
        
    }
}
