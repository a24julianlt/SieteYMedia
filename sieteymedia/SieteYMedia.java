package sieteymedia;

import recursos.Baraja;
import recursos.Carta;

public class SieteYMedia {
    // Creamos InterfaceConsola
    InterfaceConsola IC;

    Baraja baraja;
    Carta[] cartasJugador;
    Carta[] cartasBanca;


    public SieteYMedia(InterfaceConsola IC) {
        this.IC = IC;

        baraja = new Baraja();
        baraja.barajar();
        // se van pidiendo cartas al jugar pero matemáticamente a partir de 15 siempre
        // nos pasamos
        // hay 12 cartas de medio puntos, si sacara estas 12 luego cartas con valor 1
        // vemos que a partir de 15 cartas siempre se pasas
        cartasJugador = new Carta[15];
        cartasBanca = new Carta[15];
    }


    void turnoJugador(char opc) {
        while (valorCartas(cartasJugador) < 7.5 && opc == 'C') {
            Carta c = baraja.darCartas(1)[0];

            // insertamos c en las cartas del jugador
            insertarCartaEnArray(cartasJugador, c);

            // mostramos cartas y su valor, si se pasa se sale del bucle
            System.out.println("Éstas son tus cartas jugador:");
            IC.mostrarCartas(cartasJugador);

            if (valorCartas(cartasJugador) < 7.5) {
                // pedimos cartas
                opc = IC.pedirCartas();
            }

        }

    }

    Boolean turnoBanca() {
        // lo primero es consultar el valor que alcanzó el jugador en su turno
        double valorCartasJugador = valorCartas(cartasJugador);
        if (valorCartasJugador > 7.5) {
            System.out.println("Jugador, te has pasado en tu jugada anterior, gana la banca");
            return false; // gana banca
        }

        // juega hasta empatar o superar
        while (valorCartas(cartasBanca) < valorCartasJugador) {
            Carta c = baraja.darCartas(1)[0];
            insertarCartaEnArray(cartasBanca, c);
        }
        // mostramos cartas
        System.out.println("Éstas son mis cartas:");
        IC.mostrarCartas(cartasBanca);

        // comprobamos quien gana
        if (valorCartas(cartasBanca) > 7.5) {
            return true; // gana jugador
        } else {
            return false; // gana banca
        }
    }




    double valorCartas(Carta[] cartas) {
        double total = 0.0;
        int val;
        int i = 0;
        while (cartas[i] != null) {
            val = cartas[i].getNumero();
            total += (val > 7) ? 0.5 : val;
            i++;
        }

        return total;
    }

    void insertarCartaEnArray(Carta[] cartas, Carta c) {
        // inserta al final detectando el primer null
        int i = 0;
        while (cartas[i] != null) {
            i++;
        }
        cartas[i] = c;

    }

}
