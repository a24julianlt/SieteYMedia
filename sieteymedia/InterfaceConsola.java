package sieteymedia;

import recursos.Carta;

import java.util.Scanner;

public class InterfaceConsola {
    SieteYMedia SYM;

    // Scanner
    Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        new InterfaceConsola();
    }

    public InterfaceConsola() {
        SYM = new SieteYMedia();
        presentarJuego();
        jugar();
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
        //=========Turno jugador=========\\

        // obligamos a que como mínimo se tenga 1 carta
        System.out.println("Como mínimo recibes una carta, luego puedes decidir si seguir o plantarte");
        SYM.turnoJugador();

        double valor = SYM.valorCartas(SYM.cartasJugador);
        while (valor < 7.5) {

            // mostramos cartas y su valor, si se pasa se sale del bucle
            System.out.println("Éstas son tus cartas jugador:");
            mostrarCartas(SYM.cartasJugador);

            // mostrar valor
            System.out.println("\n\tValor de cartas: " + valor);


            // Pedimos carta
            // suponemos que el usuario teclea bien !!!
            System.out.println("\n¿Pides [C]arta o te [P]lantas?");
            char opc = sc.next().trim().toUpperCase().charAt(0);

            if (opc == 'C') SYM.turnoJugador();
            else break;

            // actualizamos el valor para poder salir del bucle
            valor = SYM.valorCartas(SYM.cartasJugador);
        }


        //=========Turno banca=========\\

        // lo primero es consultar el valor que alcanzó el jugador en su turno
        double valorCartasJugador = SYM.valorCartas(SYM.cartasJugador);
        if (valorCartasJugador > 7.5) {
            // Mostramos las cartas si se pasa
            System.out.println("Éstas son tus cartas jugador:");
            mostrarCartas(SYM.cartasJugador);
            System.out.println("\n\tValor de cartas: " + valor);

            System.out.println("Jugador, te has pasado en tu jugada anterior, gana la banca");
            return;
        }

        System.out.println("\n\nTurno de banca ...");
        double valorCartasBanca = SYM.valorCartas(SYM.cartasBanca);
        // juega hasta empatar o superar
        while (SYM.valorCartas(SYM.cartasBanca) < valorCartasJugador) {
            SYM.turnoBanca();
            valorCartasBanca = SYM.valorCartas(SYM.cartasBanca);
        }

        // Mostrar cartas banca
        System.out.println("Éstas son mis cartas:");
        mostrarCartas(SYM.cartasBanca);
        System.out.println("\nValor de  mis cartas(banca): " + valorCartasBanca);


        // Comprobar quién gana
        if (valorCartasBanca > 7.5) {
            System.out.println("me pasé, ganas tú,jugador");
        } else {
            System.out.println("Gana la banca");
        }


        // Despedida
        System.out.println("Adios");
    }


    void mostrarCartas(Carta[] cartas) {
        int i = 0;
        while (cartas[i] != null) {
            System.out.print("\t" + cartas[i]);
            i++;
        }
    }

}