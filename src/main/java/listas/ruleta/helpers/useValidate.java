/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listas.ruleta.helpers;

public class useValidate {
    public static boolean validateEntryOption(String option) {
        if ("s".equals(option) || "n".equals(option)) {
            return true;
        }

        System.out.println(
                "Porfavor ingrese una opcion valida\n");

        return false;
    }
}
