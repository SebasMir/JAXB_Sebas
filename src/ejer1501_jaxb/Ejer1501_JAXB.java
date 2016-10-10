/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer1501_jaxb;

import com.csvreader.CsvReader;
import generated.Root;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Usuario
 */
public class Ejer1501_JAXB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // TODO code application logic here
        cargarCSV();
        System.out.println("\033[31mFichero resu.xml generado.");
    }

    public static void cargarCSV()  {

        try {
            File file = new File("resu.xml");

            CsvReader personas_csv = new CsvReader("src/Schema/personas.csv");
            personas_csv.readHeaders();
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            GregorianCalendar c = new GregorianCalendar();
            XMLGregorianCalendar date2;

            JAXBMetodos cargador = new JAXBMetodos();
            Root pers = new Root();

            while (personas_csv.readRecord()) {
                Root.Row persona = new Root.Row();

                String nom = personas_csv.get("Nombre");
                
                persona.setNombre(nom);

                String apellido = personas_csv.get("Apellidos");
                persona.setApellidos(apellido);

                String num = personas_csv.get("Num");
                int valor = Integer.parseInt(num);
                persona.setNum(valor);

                String fechanaz = personas_csv.get("FechaNacimiento");
                Date fecha = formatter.parse(fechanaz); //Fri Jun 07 00:00:00 MYT 2013
//                String tiempo = formatter.format(fecha); //07/06/2013

                c.setTime(fecha);
                date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
                persona.setFechaNacimiento(date2);

                String ciudad = personas_csv.get("Ciudad");
                persona.setCiudad(ciudad);

                pers.getRow().add(persona);

                cargador.objectToXml(pers, file);

            }

            personas_csv.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(Ejer1501_JAXB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Ejer1501_JAXB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
