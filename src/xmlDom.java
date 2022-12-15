import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;
public class xmlDom {
    public static void main(String[] args) {
        try {

            Scanner scanner = new Scanner(System.in);

            // Creamos el documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Creamos el elemento raíz
            Element departamentos = document.createElement("departamentos");
            document.appendChild(departamentos);

            int codigo = 0;
            while (codigo != -1) {
                System.out.println("Introduce el código del departamento:");
                codigo = Integer.parseInt(scanner.nextLine());

                if (codigo != -1) {
                    System.out.println("Introduce el nombre del departamento:");
                    String nombre = scanner.nextLine();

                    System.out.println("Introduce la localidad del departamento:");
                    String loc = scanner.nextLine();

                    // Creamos el elemento departamento
                    Element departamento = document.createElement("departamento");
                    departamentos.appendChild(departamento);

                    // Creamos los elementos dept_no, nombre y loc
                    Element deptNo = document.createElement("dept_no");
                    departamento.appendChild(deptNo);

                    Element nombreElement = document.createElement("nombre");
                    departamento.appendChild(nombreElement);

                    Element locElement = document.createElement("loc");
                    departamento.appendChild(locElement);

                    // Creamos los nodos de texto
                    Text deptNoText = document.createTextNode(String.valueOf(codigo));
                    deptNo.appendChild(deptNoText);

                    Text nombreText = document.createTextNode(nombre);
                    nombreElement.appendChild(nombreText);

                    Text locText = document.createTextNode(loc);
                    locElement.appendChild(locText);
                }
            }

            // Creamos un transformador
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Creamos el origen DOMSource
            DOMSource source = new DOMSource(document);

            // Creamos el resultado StreamResult
            StreamResult result = new StreamResult(new File("departamentos.xml"));

            // Transformamos el documento
            transformer.transform(source, result);

            System.out.println("Fichero XML creado correctamente");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
