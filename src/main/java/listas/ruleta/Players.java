/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listas.ruleta;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import listas.ruleta.helpers.useFile;

public class Players {
   int total;
    ListaEnlazadaPlayers DatosPlayers = new ListaEnlazadaPlayers();

    Players() {
        this.total = 4;
    }

    /**
     * InnerPlayers
     */
    public class InnerPlayers {
        String nombre;
        String apellido;
        int saldo = 0;
        int status;
        boolean winner = false;
        int montoGanado = 0;
        int saldoComprometido = 0;
        int[] posJugada = new int[36];

        InnerPlayers(String nombre, String apellido, int saldo, int status) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.saldo = saldo;
            this.status = status;
        }

        /*
         * setPosJugada()
         * 
         * Este metodo
         * sirve para
         * almacenar los
         * numeros de
         * las jugadas, siendo del 1-36
         * jugadas normales,(40)
         * para rojos,(50)
         * para negros,(60)
         * para pares
         * y (70) para impares
         */

        public void setPosJugada(int[] jugada) {
            this.posJugada = jugada;
        }

        public void subtractSaldo(int val) {
            this.saldo = this.saldo - val;
        }

        public boolean validateJugada() {
            int saldo_comprometido = 0;

            int numposJugada = 0;

            for (int i = 0; i < this.posJugada.length; i++) {
                if (this.posJugada[i] > 0) {
                    numposJugada++;
                }
            }

            if (numposJugada == 1) {
                if (this.posJugada[0] > 0) {

                    /*
                     * Se valida el tipo de monto a comprometer, se hacen diversos if en caso de
                     * variaciones futuras
                     */
                    if (this.posJugada[0] < 36) {
                        saldo_comprometido = 1;
                    }

                    if (this.posJugada[0] == 40) {
                        saldo_comprometido = 1;
                    }

                    if (this.posJugada[0] == 50) {
                        saldo_comprometido = 1;
                    }

                    if (this.posJugada[0] == 60) {
                        saldo_comprometido = 1;
                    }

                    if (this.posJugada[0] == 70) {
                        saldo_comprometido = 1;
                    }

                } else {
                    System.out.println("La jugada no pudo ser procesada, posiblemente credito insuficiente 1\n");
                    return false;
                }
            } else {
                for (int i = 0; i < this.posJugada.length; i++) {
                    if (this.posJugada[i] > 0) {
                        saldo_comprometido = saldo_comprometido + 2;
                    } /*
                       * else {
                       * System.out.
                       * println("La jugada no pudo ser procesada, posiblemente credito insuficiente 2\n"
                       * );
                       * return false;
                       * }
                       */
                }
            }

            if (saldo_comprometido > this.saldo) {
                System.out.println("La jugada no pudo ser procesada, posiblemente credito insuficiente 3\n");
                return false;
            } else {
                this.saldoComprometido = saldo_comprometido;
                return true;
            }

        }
    }

    public void addPlayer(String nombre, String apellido, int saldo, int status) {
        InnerPlayers datosPlayer = new InnerPlayers(nombre, apellido, saldo, status);
        this.DatosPlayers.add(datosPlayer);
    }

    public void initPlayers() {
        String projectPath = (new File(".")).getAbsolutePath();

        for (int i = 1; i < this.total + 1; i++) {
            String[] arrayDatosLines = useFile.read(projectPath + "/files/Jugador" + i + ".txt").split(",");

            this.addPlayer(arrayDatosLines[0].replaceAll("@neo@", ""), arrayDatosLines[1].replaceAll("@neo@", ""),
                    Integer.valueOf(arrayDatosLines[2].replaceAll("@neo@", "")),
                    Integer.valueOf(arrayDatosLines[3].replaceAll("@neo@", "")));
        }

    }

    public void updatePlayerFiles() {
        String projectPath = (new File(".")).getAbsolutePath();

        for (int i = 0; i < this.total; i++) {
            InnerPlayers dataPlayer = this.DatosPlayers.getNodeAt(i).data;
            String[] linePlayer = new String[1];
            linePlayer[0] = dataPlayer.nombre + "," + dataPlayer.apellido + "," + dataPlayer.saldo + ","
                    + dataPlayer.status;

            /*
             * Eliminamos el archivo
             * para crear uno limpio desde 0
             */
            if ((new File(projectPath + "/files/Jugador" + ((int) i + (int) 1) + ".txt")).delete()) {
                useFile.write(linePlayer, projectPath + "/files/Jugador" + ((int) i + (int) 1) + ".txt");
            }
        }

    }

    public void logOutFile(InnerPlayers Player) {
        String projectPath = (new File(".")).getAbsolutePath();
        String line = Player.nombre + " " + Player.apellido + "," + Player.winner + "," + Player.saldoComprometido + ","
                + Player.montoGanado + "," + Player.saldo + ","
                + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        try {
            FileWriter fw = new FileWriter(projectPath + "/files/log.out", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(line);
            out.close();
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    public void winnersOutFile(InnerPlayers Player) {
        String projectPath = (new File(".")).getAbsolutePath();
        String line = Player.nombre + " " + Player.apellido + "," + Player.winner + "," + Player.saldoComprometido + ","
                + Player.montoGanado + "," + Player.saldo + ","
                + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        try {
            FileWriter fw = new FileWriter(projectPath + "/files/Ganadores.out", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(line);
            out.close();
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    public void ValidateWinners(int numWinner) {
        Tablero Ruleta = new Tablero();
        Ruleta.initTablero();

        System.out.println("Los ganadores de esta partida son:\n");

        for (int i = 0; i < this.total; i++) {
            InnerPlayers dataPlayer = this.DatosPlayers.getNodeAt(i).data;

            for (int j = 0; j < dataPlayer.posJugada.length; j++) {

                if (dataPlayer.posJugada[j] > 0) {

                    if (dataPlayer.posJugada[j] == 40
                            && "rojo".equals(Ruleta.DatosTablero.getNodeAt(numWinner - 1).data.color)) {
                        dataPlayer.montoGanado = dataPlayer.montoGanado + 2;
                    }

                    if (dataPlayer.posJugada[j] == 50
                            && "negro".equals(Ruleta.DatosTablero.getNodeAt(numWinner - 1).data.color)) {
                        dataPlayer.montoGanado = dataPlayer.montoGanado + 2;
                    }

                    if (dataPlayer.posJugada[j] == 60 && (numWinner % 2 == 0)) {
                        dataPlayer.montoGanado = dataPlayer.montoGanado + 3;
                    }

                    if (dataPlayer.posJugada[j] == 70 && (numWinner % 2 != 0)) {
                        dataPlayer.montoGanado = dataPlayer.montoGanado + 3;
                    }

                    if (dataPlayer.posJugada[j] == numWinner) {
                        dataPlayer.montoGanado = dataPlayer.montoGanado + 5;
                    }

                }
            }

            dataPlayer.saldo = dataPlayer.saldo - dataPlayer.saldoComprometido;

            if (dataPlayer.montoGanado > 0) {
                dataPlayer.saldo = dataPlayer.saldo + dataPlayer.montoGanado;
                dataPlayer.winner = true;
                winnersOutFile(dataPlayer);
            }
            if (dataPlayer.saldoComprometido > 0) {
                logOutFile(dataPlayer);
            }

            if (dataPlayer.winner) {
                System.out.println("El jugador " + dataPlayer.nombre + " " + dataPlayer.apellido
                        + " ha ganado un monto de :" + dataPlayer.montoGanado + " creditos\n");
            }
        }

    } 
}
