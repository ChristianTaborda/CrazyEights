/*****************************************
 * Christian Taborda                     *
 * 1632081-3743                          *
 * ***************************************
 */

package ochosLocos;

import java.util.Random;
import java.util.Scanner;

public class Visual {
	
	//Atributos
	
	private Juego partida;
	private int N;
	private int opcion;
	Scanner entrada;
	Scanner elegir;

	//Métodos
	
	public Visual(){
		partida = new Juego();
		entrada = new Scanner(System.in);
		elegir = new Scanner(System.in);
	}
	
	//Imprime el estado actual del juego.
	
	public void dibujar(){
		System.out.printf("\n------------------------------------------------------------------------------------------------\n");
		System.out.printf("\n\t\t\t\t** OCHOS LOCOS **\n");
		System.out.printf("\n\t\t\t\tJugador 3 -- Mano: %d\n", partida.getJugador(3).getTamaño_mano());
		System.out.printf("\nJugador 2 -- Mano: %d\t\t", partida.getJugador(2).getTamaño_mano());
		System.out.printf("Baraja: %d ", partida.getBaraja().getTamaño_baraja());
		System.out.printf("Centro: %s\t\t", partida.getCarta_actual());
		System.out.printf("Jugador 4 -- Mano: %d\n", partida.getJugador(4).getTamaño_mano());
		System.out.printf("\n\t\t\t\t\tTu Mano:\n");
		System.out.printf(partida.getJugador(1).mostrar_mano());
		System.out.printf("\n");
	}
	
	//Retorna el valor de una carta.
	
	public String valorar_carta(String carta){
		String valor;
		valor = carta.substring(0, carta.length()-1);
		return valor;
	}
	
	//Permite elegir el palo a cambiar.
	
	public void ocho(){
		System.out.printf("\nElige un palo:\n1. Corazones.\n2. Diamantes.\n3. Treboles.\n4. Picas.\n\n");
		opcion = elegir.nextInt();
		partida.cambiar_palo(opcion);
	}
	
	//Verifica si el jugador tiene cartas válidas para jugar o debe robar, si las tiene permite jugar una carta.
	
	public void jugar(int jugador){
		
		//Jugador humano:
		
		if(jugador == 1){
			if(partida.getJugador(1).verificar_mano(partida.getValor(), partida.getPalo())){
				String carta;
				do{
					System.out.printf("\nElige una carta: \n");
					N = entrada.nextInt();
					carta = partida.getJugador(1).ver_carta(N-1);
					if(partida.validar_carta(partida.getJugador(1).ver_carta(N-1))){
						partida.lanzar_carta(partida.getJugador(1).jugar_carta(N-1));
						if(valorar_carta(carta).equals("8")){
							ocho();
							System.out.printf("\nElegiste %c como palo.\n", partida.getPalo());
						}
						dibujar();
					}
					else{
						System.out.printf("\nNo puedes jugar esa carta.\n");
						dibujar();
					}
				}while(!partida.validar_carta(carta));
				carta = null;
			}
			else{
				if(partida.getValor().equals("A")){
					System.out.printf("\nNo tienes ningún AS en tu mano, robas tres cartas\n");
					for(int x=0; x<3; x++){
						partida.getJugador(1).obtener_carta(partida.getBaraja().sacar_carta(partida.getCentro()));
					}
					dibujar();
				}
				else{
					System.out.printf("\nNo tienes ninguna carta válida, robas una carta\n");
					partida.getJugador(1).obtener_carta(partida.getBaraja().sacar_carta(partida.getCentro()));
					dibujar();
				}
			}
		}
		
		//Jugador Máquina:
		
		else{
			if(partida.getJugador(jugador).verificar_mano(partida.getValor(), partida.getPalo())){
				String carta;
				System.out.printf("\nTurno del jugador %d\n", jugador);
				carta = partida.getJugador(jugador).ver_carta(partida.elegir_carta(jugador));
				partida.lanzar_carta(partida.getJugador(jugador).jugar_carta(partida.elegir_carta(jugador)));
				if(valorar_carta(carta).equals("8")){
					Random aleatorio = new Random();
					int A = aleatorio.nextInt(4) + 1;
					partida.cambiar_palo(A);
					System.out.printf("\nEl jugador %d eligió %c como palo.\n", jugador, partida.getPalo());
				}
				dibujar();
			}
			else{
				if(partida.getValor().equals("A")){
					System.out.printf("\nEl jugador %d no tuvo ningún AS en su mano, robó tres cartas\n", jugador);
					for(int x=0; x<3; x++){
						partida.getJugador(jugador).obtener_carta(partida.getBaraja().sacar_carta(partida.getCentro()));
					}
					dibujar();
				}
				else{
					System.out.printf("\nEl jugador %d no tuvo ninguna carta válida, robó una carta\n", jugador);
					partida.getJugador(jugador).obtener_carta(partida.getBaraja().sacar_carta(partida.getCentro()));
					dibujar();
				}
			}
		}
	}
	
	//Inicializa el juego y lo ejecuta hasta su final.
	
	public void comienzo(){
		dibujar();
		System.out.printf("\nRepartiendo cartas...\n");
		partida.iniciar_juego();
		dibujar();
		do{
			if(!(partida.getBaraja().getTamaño_baraja() < 3)){
				jugar(1);
			}
			else{
				System.out.printf("\nTerminó el juego!\nNo se pueden robar más cartas\n");
				break;
			}	
			if(!(partida.getBaraja().getTamaño_baraja() < 3)){
				jugar(2);
			}
			else{
				System.out.printf("\nTerminó el juego!\nNo se pueden robar más cartas\n");
				break;
			}
			if(!(partida.getBaraja().getTamaño_baraja() < 3)){
				jugar(3);
			}
			else{
				System.out.printf("\nTerminó el juego!\nNo se pueden robar más cartas\n");
				break;
			}
			if(!(partida.getBaraja().getTamaño_baraja() < 3)){
				jugar(4);
			}
			else{
				System.out.printf("\nTerminó el juego!\nNo se pueden robar más cartas\n");
				break;
			}
		}while(!partida.parada());
		if(!(partida.getBaraja().getTamaño_baraja() < 3)){
			if(partida.determinar_ganador() == 1){
				System.out.printf("\nTerminó el juego!\nGanaste!\n");
			}
			else{
				System.out.printf("\nTerminó el juego!\nGanó el jugador %d\n", partida.determinar_ganador());
			}
		}		
	}		
}
