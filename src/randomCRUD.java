import java.io.IOException;
import java.io.RandomAccessFile;
public class randomCRUD {


        public static void main(String[] args) {
            // Comprobamos los argumentos recibidos
            String[] argumentos = new String[4];
            argumentos[0] = "0";
            argumentos[1] = "montoya";
            argumentos[2] = "0";
            argumentos[3] = "1000";

            if (argumentos.length == 1) {
                // Si solo se recibe un argumento, se ejecuta el metodo de consulta
                consultaEmpleado(Integer.parseInt(argumentos[0]));
            } else if (argumentos.length == 4) {
                // Si recibe cuatro argumentos, se ejecuta el metodo de inserción
                insercionEmpleado(Integer.parseInt(argumentos[0]), argumentos[1], Integer.parseInt(argumentos[2]), Integer.parseInt(argumentos[3]));
            } else if (argumentos.length == 3) {
                // Si recibe tres argumentos, se ejecuta el metodo de modificación
                modificacionEmpleado(Integer.parseInt(argumentos[0]), Integer.parseInt(argumentos[1]), Integer.parseInt(argumentos[2]));
            } else if (argumentos.length == 2) {
                // Si recibe dos argumentos, se ejecuta el metodo de borrado
                borradoEmpleado(Integer.parseInt(argumentos[0]), Integer.parseInt(argumentos[1]));
            }
        }

        // Metodo para consultar los txtos de un empleado
        public static void consultaEmpleado(int id) {
            try {
                // Abrimos el fichero
                RandomAccessFile fichero = new RandomAccessFile("empleados.txt", "r");
                // Posicionamos el puntero del fichero al principio
                fichero.seek(0);
                // Creamos una variable de tipo booleana para identificar si el empleado existe o no
                boolean empleadoEncontrado = false;
                // Bucle que lee los registros del fichero
                while (fichero.getFilePointer() < fichero.length()) {
                    // Leemos el identificador del empleado
                    int identificador = fichero.readInt();
                    // Si el identificador leido es igual al identificador recibido
                    if (identificador == id) {
                        // Cambiamos el valor de la variable
                        empleadoEncontrado = true;
                        // Leemos el apellido
                        String apellido = fichero.readUTF();
                        // Leemos el departamento
                        int departamento = fichero.readInt();
                        // Leemos el salario
                        int salario = fichero.readInt();
                        // Visualizamos los txtos del empleado
                        System.out.println("Identificador: " + identificador);
                        System.out.println("Apellido: " + apellido);
                        System.out.println("Departamento: " + departamento);
                        System.out.println("Salario: " + salario);
                        break;
                    } else {
                        // Si el identificador no es el mismo, nos saltamos la lectura del apellido
                        fichero.readUTF();
                        // Leemos el departamento
                        fichero.readInt();
                        // Leemos el salario
                        fichero.readInt();
                    }
                }
                // Si el empleado no se ha encontrado
                if (!empleadoEncontrado) {
                    // Visualizamos un mensaje
                    System.out.println("El empleado no existe.");
                }
                // Cerramos el fichero
                fichero.close();
            } catch (IOException e) {
                System.out.println("Error al leer el fichero.");
            }
        }

        // Metodo para insertar un nuevo empleado
        public static void insercionEmpleado(int id, String apellido, int departamento, int salario) {
            try {
                // Abrimos el fichero
                RandomAccessFile fichero = new RandomAccessFile("empleados.txt", "rw");
                // Posicionamos el puntero del fichero al principio
                fichero.seek(0);
                // Creamos una variable de tipo booleana para identificar si el empleado existe o no
                boolean empleadoEncontrado = false;
                // Bucle que lee los registros del fichero
                while (fichero.getFilePointer() < fichero.length()) {
                    // Leemos el identificador del empleado
                    int identificador = fichero.readInt();
                    // Si el identificador leido es igual al identificador recibido
                    if (identificador == id) {
                        // Cambiamos el valor de la variable
                        empleadoEncontrado = true;
                        // Visualizamos un mensaje
                        System.out.println("El empleado ya existe.");
                        break;
                    } else {
                        // Si el identificador no es el mismo, nos saltamos la lectura del apellido
                        fichero.readUTF();
                        // Leemos el departamento
                        fichero.readInt();
                        // Leemos el salario
                        fichero.readInt();
                    }
                }
                // Si el empleado no se ha encontrado
                if (!empleadoEncontrado) {
                    // Nos posicionamos al final del fichero
                    fichero.seek(fichero.length());
                    // Escribimos el identificador
                    fichero.writeInt(id);
                    // Escribimos el apellido
                    fichero.writeUTF(apellido);
                    // Escribimos el departamento
                    fichero.writeInt(departamento);
                    // Escribimos el salario
                    fichero.writeInt(salario);
                    // Visualizamos un mensaje
                    System.out.println("Empleado insertado correctamente.");
                }
                // Cerramos el fichero
                fichero.close();
            } catch (IOException e) {
                System.out.println("Error al leer el fichero.");
            }
        }

        // Metodo para modificar el salario de un empleado
        public static void modificacionEmpleado(int id, int salarioAntiguo, int salarioNuevo) {
            try {
                // Abrimos el fichero
                RandomAccessFile fichero = new RandomAccessFile("empleados.txt", "rw");
                // Posicionamos el puntero del fichero al principio
                fichero.seek(0);
                // Creamos una variable de tipo booleana para identificar si el empleado existe o no
                boolean empleadoEncontrado = false;
                // Bucle que lee los registros del fichero
                while (fichero.getFilePointer() < fichero.length()) {
                    // Leemos el identificador del empleado
                    int identificador = fichero.readInt();
                    // Si el identificador leido es igual al identificador recibido
                    if (identificador == id) {
                        // Cambiamos el valor de la variable
                        empleadoEncontrado = true;
                        // Leemos el apellido
                        String apellido = fichero.readUTF();
                        // Leemos el salario
                        int salario = fichero.readInt();
                        // Comprobamos si el salario leido es el mismo que el salario antiguo
                        if (salario == salarioAntiguo) {
                            // Nos posicionamos al principio del registro
                            fichero.seek(fichero.getFilePointer() - 4);
                            // Escribimos el salario nuevo
                            fichero.writeInt(salarioNuevo);
                            // Visualizamos un mensaje
                            System.out.println("Apellido: " + apellido);
                            System.out.println("Salario antiguo: " + salarioAntiguo);
                            System.out.println("Salario nuevo: " + salarioNuevo);
                        } else {
                            // Si el salario no es el mismo, visualizamos un mensaje
                            System.out.println("El salario antiguo no es correcto.");
                        }
                        break;
                    } else {
                        // Si el identificador no es el mismo, nos saltamos la lectura del apellido
                        fichero.readUTF();
                        // Leemos el salario
                        fichero.readInt();
                    }
                }
                // Si el empleado no se ha encontrado
                if (!empleadoEncontrado) {
                    // Visualizamos un mensaje
                    System.out.println("El empleado no existe.");
                }
                // Cerramos el fichero
                fichero.close();
            } catch (IOException e) {
                System.out.println("Error al leer el fichero.");
            }
        }

        // Metodo para borrar un empleado
        public static void borradoEmpleado(int id, int salario) {
            try {
                // Abrimos el fichero
                RandomAccessFile fichero = new RandomAccessFile("empleados.txt", "rw");
                // Posicionamos el puntero del fichero al principio
                fichero.seek(0);
                // Creamos una variable de tipo booleana para identificar si el empleado existe o no
                boolean empleadoEncontrado = false;
                // Bucle que lee los registros del fichero
                while (fichero.getFilePointer() < fichero.length()) {
                    // Leemos el identificador del empleado
                    int identificador = fichero.readInt();
                    // Si el identificador leido es igual al identificador recibido
                    if (identificador == id) {
                        // Cambiamos el valor de la variable
                        empleadoEncontrado = true;
                        // Leemos el salario
                        int salarioAntiguo = fichero.readInt();
                        // Comprobamos si el salario leido es el mismo que el salario recibido
                        if (salarioAntiguo == salario) {
                            // Nos posicionamos al principio del registro
                            fichero.seek(fichero.getFilePointer() - 12);
                            // Escribimos el identificador
                            fichero.writeInt(-1);
                            // Escribimos el apellido
                            fichero.writeUTF(Integer.toString(id));
                            // Escribimos el departamento
                            fichero.writeInt(0);
                            // Escribimos el salario
                            fichero.writeInt(0);
                            // Visualizamos un mensaje
                            System.out.println("Empleado borrado correctamente.");
                        } else {
                            // Si el salario no es el mismo, visualizamos un mensaje
                            System.out.println("El salario no es correcto.");
                        }
                        break;
                    } else {
                        // Si el identificador no es el mismo, nos saltamos la lectura del apellido
                        fichero.readUTF();
                        // Leemos el salario
                        fichero.readInt();
                    }
                }
                // Si el empleado no se ha encontrado
                if (!empleadoEncontrado) {
                    // Visualizamos un mensaje
                    System.out.println("El empleado no existe.");
                }
                // Cerramos el fichero
                fichero.close();
            } catch (IOException e) {
                System.out.println("Error al leer el fichero.");
            }
        }

}
