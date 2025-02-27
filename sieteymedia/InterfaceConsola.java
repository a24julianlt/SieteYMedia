package sieteymedia;

import recursos.Carta;

import java.util.Scanner;

public class InterfaceConsola {
    // creamos SieteYMedia
    SieteYMedia juego;

    // Scanner
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        InterfaceConsola consola = new InterfaceConsola();

        // Iniciamos SieteYMedia
        consola.juego = new SieteYMedia(consola);

        consola.presentarJuego();
        consola.jugar();
    }

    void presentarJuego() {
        System.out.println("- El usuario es el jugador y el ordenador la  banca.");
        System.out.println("- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.");
        System.out.println("- las figuras (10-sota, 11-caballo y 12-rey) valen medio punto y, el resto, su valor.");
        System.out.println(
                "- Hay dos turnos de juego: el turno del jugador y el turno de la banca. Se comienza por el turno del jugador.");
        System.out.println("- El jugador va pidiendo cartas a la banca de una en una.");
        System.out.println("- El jugador puede plantarse en cualquier momento.");
        System.out.print("- Si la suma de los valores de las cartas sacadas es superior ");
        System.out.println("a 7 y medio, el jugador 'se pasa de siete y medio' y  pierde.");
        System.out.println(
                "- Si el jugador no se pasa, comienza a sacar cartas la banca y ésta  está obligada a sacar cartas hasta empatar o superar al jugador.");
        System.out.println(
                "- Si la banca consigue empatar o superar la puntuación del jugador 'sin pasarse de siete y medio', gana la banca.");
        System.out.println(
                "- La banca no se puede plantar y tiene que empatar o superar la puntuación del  jugador sin pasarse.");
        System.out.println(
                "- En este proceso puede ocurrir que la banca 'se pase' y entonces pierde la banca y gana el jugador.");
        System.out.println("\nEmpecemos!!!\n");
    }

    void jugar() {
        // turno jugador
        char opc = 'C';

        // obligamos a que como mínimo se tenga 1 carta
        System.out.println("Como mínimo recibes una carta, luego puedes decidir si seguir o plantarte");
        juego.turnoJugador(opc);

        // turno banca
        System.out.println("\n\nTurno de banca ...");
        boolean resultadoBanca = juego.turnoBanca();

        // resultado
        if (resultadoBanca) {
            System.out.println("me pasé, ganas tú,jugador");
        } else {
            System.out.println("Gana la banca");
        }

        // Despedida
        System.out.println("Adios");
    }

    char pedirCartas() {
        // suponemos que el usuario teclea bien !!!
        System.out.println("\n¿Pides [C]arta o te [P]lantas?");

        // Devolvemos la decisión del jugador
        return sc.next().trim().toUpperCase().charAt(0);
    }


    void mostrarCartas(Carta[] cartas) {
        int i = 0;
        while (cartas[i] != null) {
            System.out.print("\t" + cartas[i]);
            i++;
        }
        mostrarValor(cartas);
    }

    void mostrarValor(Carta[] cartas) {
        double valor = juego.valorCartas(cartas);
        System.out.println("\n\tValor de cartas: " + valor);
    }

}
