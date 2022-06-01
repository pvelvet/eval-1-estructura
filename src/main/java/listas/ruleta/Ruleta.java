/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package listas.ruleta;
import listas.ruleta.game.Menu;
import listas.ruleta.game.Welcome;
import listas.ruleta.helpers.useRead;
import listas.ruleta.helpers.useValidate;

public class Ruleta {

    public static void main(String[] args) {

        int statusApp = 1;
        Menu Menu = new Menu();

        Welcome.show();
        Menu.show();

        do {
            statusApp = Menu.startOptions();

            if (statusApp == 0) {
                break;
            }

            System.out.println("Esta es la informacion del tablero\n");

            Tablero Ruleta = new Tablero();

            /*
             * Iniciamos el tablero segun
             * los datos precentes en
             * files/Ruleta.txt
             */
            Ruleta.initTablero();

            for (int i = 0; i < Ruleta.total; i++) {// Mostramos la informacion del Tablero
                ListaEnlazadaTablero.Node aux_pos = Ruleta.DatosTablero.getNodeAt(i);
                System.out.println(
                        "Posicion: " + aux_pos.data.num + " Color: " + aux_pos.data.color
                                + " \n");
            }

            System.out.println("Esta es la informacion de los jugadores\n");

            Players Juagdores = new Players();

            Juagdores.initPlayers();

            for (int i = 0; i < Juagdores.total; i++) {// Mostramos la informacion de cada jugador
                ListaEnlazadaPlayers.Node aux_player = Juagdores.DatosPlayers.getNodeAt(i);

                System.out.println("Nombre Jugador n° " + (i + 1) + ": " + aux_player.data.nombre.toString()
                        + " " + aux_player.data.apellido.toString() + ", Credito: " + aux_player.data.saldo + "\n");
            }

            System.out.println("Se procedera a procesar a los jugadores\n");

            for (int i = 0; i < Juagdores.total; i++) {// Recorremos cada jugador para iniciar su partida

                ListaEnlazadaPlayers.Node aux_player = Juagdores.DatosPlayers.getNodeAt(i);

                if (aux_player.data.status == 1 && aux_player.data.saldo > 0) { // Validamos que el jugador siga activo
                                                                                // y que su saldo sea mayor a 0
                    String option = "H";
                    do {
                        option = useRead.readString(aux_player.data.nombre.toString().toUpperCase()
                                + " " + aux_player.data.apellido.toString().toUpperCase() + " desea jugar ? (s/n)\n");
                    } while (!useValidate.validateEntryOption(option));

                    if ("n".equals(option)) {
                        aux_player.data.status = 0;
                    }

                    if (aux_player.data.status == 1) {
                        Boolean statusValidateJuagada = true;

                        do {
                            /*
                             * Se listan las
                             * opciones de jugadas
                             */
                            Menu.modPlay();

                            option = "H";
                            do {
                                option = useRead.readString("");
                            } while (!Menu.validatemodPlay(option));

                            switch (option) {
                                case "1":
                                    Menu.modNums();
                                    String[] optionmodNums = new String[36];
                                    optionmodNums[0] = "H";

                                    do {
                                        optionmodNums = useRead.readString("").split(",");
                                    } while (!Menu.validatemodNums(optionmodNums));

                                    for (int j = 0; j < optionmodNums.length; j++) {
                                        if (Integer.valueOf(optionmodNums[j]) > 0) {
                                            aux_player.data.posJugada[j] = Integer.valueOf(optionmodNums[j]);
                                        }
                                    }

                                    break;
                                case "2":
                                    Menu.modPlayColors();
                                    String optionModPlayColors = "H";

                                    do {
                                        optionModPlayColors = useRead.readString("");
                                    } while (!Menu.validatemodPlayColors(optionModPlayColors));

                                    if ("r".equals(optionModPlayColors)) {
                                        aux_player.data.posJugada[0] = 40;
                                    }

                                    aux_player.data.posJugada[0] = 50;

                                    break;
                                case "3":
                                    aux_player.data.posJugada[0] = 60;
                                    break;
                                case "4":
                                    aux_player.data.posJugada[0] = 70;
                                    break;
                                default:
                                    System.out.println("Ah ocurrido un error inesperado\n");
                                    statusApp = 0;
                                    break;
                            }

                            statusValidateJuagada = aux_player.data.validateJugada();
                        } while (!statusValidateJuagada);
                    }

                }
            }

            /*
             * Ahora procedemos a recorrer el tablero o ruleta, teniendo en cuenta que se
             * generaran numeros a leatorios del 1 al 100, para aumentar el procentaje de
             * aleatoriedad
             */

            boolean statusRuleta = false;
            do {
                for (int i = 0; i < Ruleta.DatosTablero.total; i++) {
                    int random_num = (int) (Math.random() * 100 + 1);

                    if (random_num == Integer.valueOf(Ruleta.DatosTablero.getNodeAt(i).data.num)) {
                        System.out.println("Bola callo en: " + random_num);
                        Juagdores.ValidateWinners(random_num);
                        Juagdores.updatePlayerFiles();
                        statusRuleta = true;
                        break;
                    }
                }
            } while (!statusRuleta);

        } while (statusApp == 1);

    }
}
