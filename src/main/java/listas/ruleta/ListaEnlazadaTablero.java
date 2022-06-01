/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listas.ruleta;

public class ListaEnlazadaTablero {
    Node head;
    int total;

    ListaEnlazadaTablero() {
        this.head = null;
        this.total = 0;
    }

    public void add(Tablero.InnerTablero data) {

        Node newNode = new Node(data);
        newNode.next = null;

        if (this.head == null) {
            this.head = newNode;
        } else {
            Node last = getLastNode();
            last.next = newNode;
        }

        this.total++;
    }

    public Node getLastNode() {
        if (this.head == null) {
            return null;
        }

        Node last = this.head;
        Node control = this.head;

        while (control != null) {
            last = control;
            control = last.next;
        }

        return last;
    }

    public Node getNodeAt(int pos) {
        if (this.head == null || (this.total - 1) < pos) {
            return null;
        }

        Node control = this.head;
        int x = 0;
        while (control != null) {
            if (x == pos) {
                break;
            }

            x++;
            control = control.next;
        }

        return control;
    }

    public void append(Node node, Tablero.InnerTablero data) {

        if (this.head == null || node == null) {
            return;
        }

        Node newNode = new Node(data);

        Node prev = node;
        Node next = node.next;

        prev.next = newNode;
        newNode.next = next;

        this.total++;
    }

    public void delete(Node node) {
        if (this.head == null || node == null) {
            return;
        }

        Node control = this.head;
        Node temp = null;

        if (node == this.head) {
            temp = this.head;
            this.head = this.head.next;

            temp = null;
        } else {
            while (control.next != node) {
                control = control.next;
            }

            temp = control.next;
            control.next = temp.next;
            temp = null;
        }

        this.total--;
    }

    public class Node {
        Tablero.InnerTablero data;
        Node next;

        Node(Tablero.InnerTablero data) {
            this.data = data;
            this.next = null;
        }
    }
}
