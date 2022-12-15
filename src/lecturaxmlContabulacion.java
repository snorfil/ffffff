import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class lecturaxmlContabulacion {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("C:\\Users\\snorf\\Desktop\\Nueva carpeta\\mastermind-master-desarrollo-implementacion-java\\randomAccesFile\\src\\departamentos.xml"));

            // Creamos el fichero BufferedWriter
            BufferedWriter bw = new BufferedWriter(new FileWriter("departamentos.txt"));

            // Obtenemos los elementos departamento
            NodeList listaDepartamentos = document.getElementsByTagName("departamento");

            for (int i = 0; i < listaDepartamentos.getLength(); i++) {
                Element departamento = (Element) listaDepartamentos.item(i);

                // Obtenemos los elementos dept_no, nombre y loc
                Element deptNo = (Element) departamento.getElementsByTagName("dept_no").item(0);
                Element nombre = (Element) departamento.getElementsByTagName("nombre").item(0);
                Element loc = (Element) departamento.getElementsByTagName("loc").item(0);

                // Obtenemos el contenido de los elementos
                String deptNoText = deptNo.getTextContent();
                String nombreText = nombre.getTextContent();
                String locText = loc.getTextContent();

                // Escribimos el contenido en el fichero de texto
                bw.write(deptNoText + "\t" + nombreText + "\t" + locText);
                bw.newLine();
            }

            bw.close();

            System.out.println("Fichero TXT creado correctamente");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
