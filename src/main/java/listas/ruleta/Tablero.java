/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listas.ruleta;
import listas.ruleta.helpers.useFile;
import java.io.*;

public class Tablero {
    int total;
    ListaEnlazadaTablero DatosTablero = new ListaEnlazadaTablero();

    Tablero() {
        this.total = 0;
    }

    /**
     * InnerTablero
     */
    public class InnerTablero {
        String num;
        String color;

        InnerTablero(String num, String color) {
            this.num = num;
            this.color = color;
        }
    }

    public void addPos(String num, String color) {
        InnerTablero newPosTablero = new InnerTablero(num, color);
        this.DatosTablero.add(newPosTablero);
    }

    public void initTablero() {
        String projectPath = (new File(".")).getAbsolutePath();
        String[] arrayLines = useFile.read(projectPath + "/files/Ruleta.txt").split("@neo@");

        this.total = arrayLines.length;

        for (String string : arrayLines) {
            String[] arrayDatosLines = string.split(",");
            this.addPos(arrayDatosLines[0], arrayDatosLines[1]);
        }
    }
}
