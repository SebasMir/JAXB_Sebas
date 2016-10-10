/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer1501_jaxb;

//import com.sun.org.apache.xml.internal.resolver.Catalog;
import generated.Root;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Usuario
 */
public class JAXBMetodos {

    public JAXBMetodos() {
    }

    public Root xmlToObject(File f) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //Do the job, return object
            return (Root) jaxbUnmarshaller.unmarshal(f);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void objectToXml(Root cds, File rf) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Optional
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Do the job
            jaxbMarshaller.marshal(cds, rf);

            //Optional: output pretty printed
            //jaxbMarshaller.marshal(cds, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void printObject(Root cds) {
        String result = "";
        //Read every CD from list
        List<Root.Row> llista = cds.getRow();
        for (int i = 0; i < llista.size(); i++) {
            result += "\n " + "Nombre: " + llista.get(i).getNombre();
            result += "\n " + "Apellido: " + llista.get(i).getApellidos();
            result += "\n " + "Ciudad: " + llista.get(i).getCiudad();
            result += "\n " + "Numero: " + llista.get(i).getNum();
            result += "\n " + "Fecha Nacimiento: " + llista.get(i).getFechaNacimiento();
            result += "\n ";
        }
        System.out.println(result);
    }
}
