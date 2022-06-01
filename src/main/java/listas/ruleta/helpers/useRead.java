/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listas.ruleta.helpers;

import java.util.Scanner;

public class useRead {
    public static int reeadInt(String message) {
        Scanner data = new Scanner(System.in);
        System.out.print(message);
        return (data.nextInt());
    }

    public static String readString(String message) {
        Scanner data = new Scanner(System.in);
        System.out.print(message);
        String men = data.nextLine();
        return (men);
    }

    public static Boolean readBoolean(String message) {
        Scanner data = new Scanner(System.in);
        System.out.print(message);
        return (data.nextBoolean());
    }

    public static char readChar(String message) {
        char character;
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        String data = input.next();
        character = data.charAt(0);
        return (character);
    } 
}
