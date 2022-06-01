/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listas.ruleta.game;

import listas.ruleta.helpers.useRead;
import listas.ruleta.helpers.useValidate;
public class Menu {
   int status = 1;

    public void show() {
        System.out.println("Empecemos esplicando un poco las reglas\n");
        System.out.println(
                "1. Las apuestas se hacen en muneros del 1 al 36, en grupos, en colores (negro y rojo), pares he impares\n");
        System.out.println(
                "2. Caja juagador debera hacer su apuesta seguido del otro\n");
        System.out.println(
                "3. Luego de terminarce las apuestas, la ruleta empesara a girar\n");

        showInfoPay();
    }

    private static void showInfoPay() {
        System.out.println(
                "|-- Los pagos son de la siguiente manera:\n");
        System.out.println(
                "|-- Un (1) credito por numero, gana cinco (5)\n");
        System.out.println(
                "|-- Dos (2) creditos por numero en grupos, gana cinco (5)\n");
        System.out.println(
                "|-- Un (1) credito por color, gana dos (2)\n");
        System.out.println(
                "|-- Un (1) credito por par o impar, gana tres (3)\n");
    }

    public int startOptions() {
        String option = "H";

        do {
            do {
                option = useRead.readString("Iniciar Juego ? (s/n)\n");
            } while (!useValidate.validateEntryOption(option));

            if ("n".equals(option)) {

                System.out.println(
                        "Hasta luego\n");
                this.status = 0;

            } else {
                System.out.println(
                        "Se procedera a cargar los datos de la partida\n");
                return 1;
            }
        } while (this.status == 1);

        return this.status;

    }

    public void modPlay() {
        System.out.println(
                "|-- Porfavor seleccione la modalidad a jugar:\n");
        System.out.println(
                "(1) Numeros:\n");
        System.out.println(
                "(2) Colores\n");
        System.out.println(
                "(3) Pares\n");
        System.out.println(
                "(4) Impares\n");
    }

    public boolean validatemodPlay(String option) {
        if ("1".equals(option) || "2".equals(option) || "3".equals(option) || "4".equals(option)) {
            return true;
        }

        System.out.println(
                "Porfavor ingrese una opcion valida\n");

        return false;

    }

    public void modPlayColors() {
        System.out.println(
                "|-- Porfavor seleccione el color (r/n)\n");
    }

    public boolean validatemodPlayColors(String option) {
        if ("n".equals(option) || "r".equals(option)) {
            return true;
        }

        System.out.println(
                "Porfavor ingrese una opcion valida\n");

        return false;

    }

    public void modNums() {
        System.out.println(
                "|-- Escriba el numero de su eleccion (1-36), si desea ingresar mas de un numero porfavor separelos con una coma (,)\n");
    }

    public boolean validatemodNums(String[] option) {
        boolean[] status_pass = new boolean[option.length];

        for (int i = 0; i < option.length; i++) {
            int val_option = Integer.valueOf(option[i]);
            status_pass[i] = val_option > 0 && val_option < 37;
        }

        for (int i = 0; i < status_pass.length; i++) {
            if (!status_pass[i]) {
                System.out.println(
                        "Porfavor ingrese una opcion valida\n");
                return false;
            }
        }

        return true;

    } 
}
