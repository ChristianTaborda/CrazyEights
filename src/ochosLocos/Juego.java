/*****************************************
 * Christian Taborda                     *
 * 1632081-3743                          *
 * ***************************************
 */

package ochosLocos;

import java.util.Vector;

public class Juego {
	
	//Atributos
	
	private Baraja mazo;
	private Jugador j1, j2, j3, j4;
	private Vector <String> centro;
	private char palo;
	private String valor;
	
	//Métodos
	
	public Juego(){
		mazo = new Baraja();
		j1 = new Jugador();
		j2 = new Jugador();
		j3 = new Jugador();
		j4 = new Jugador();
		centro = new Vector <String> ();
		centro.addElement("");
	}
	
	//Reparte ocho cartas de la baraja a cada jugador.
	
	public void repartir(){
		do{
			j1.obtener_carta(mazo.sacar_carta(centro));
		}while(j1.getTamaño_mano() != 8);
		do{
			j2.obtener_carta(mazo.sacar_carta(centro));
		}while(j2.getTamaño_mano() != 8);
		do{
			j3.obtener_carta(mazo.sacar_carta(centro));
		}while(j3.getTamaño_mano() != 8);
		do{
			j4.obtener_carta(mazo.sacar_carta(centro));
		}while(j4.getTamaño_mano() != 8);
	}
	
	//Retorna el valor de una carta.
	
	public String valorar_carta(String carta){
		String valor;
		valor = carta.substring(0, carta.length()-1);
		return valor;
	}
	
	//Inicializa la información del juego.
	
	public void iniciar_juego(){
		repartir();
		centro.clear();
		centro.addElement(mazo.sacar_carta(centro));
		valor = valorar_carta(centro.elementAt(centro.size() - 1));
		palo = centro.elementAt(centro.size() - 1).charAt(centro.elementAt(centro.size() - 1).length()-1);
	}
	
	//Verifica si una carta es válida para jugar.
	
	public boolean validar_carta(String carta){
		if(valor.equals("A")){
			if(valorar_carta(carta).equals("A")){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if((valorar_carta(carta).equals("8")) || (valorar_carta(carta).equals(valor)) || (carta.charAt(carta.length()-1) == palo)){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	//Retorna uno de los jugadores.
	
	public Jugador getJugador(int N){
		Jugador salida = null;
		switch(N){
			case 1:
				salida = j1;
				break;
			case 2:
				salida = j2;
				break;
			case 3:
				salida = j3;
				break;
			case 4:
				salida = j4;
				break;					
		}
		return salida;
	}
	
	//Retorna la baraja.
	
	public Baraja getBaraja(){
		return mazo;
	}
	
	//Retorna la carta actual en juego.
	
	public String getCarta_actual(){
		return centro.elementAt(centro.size() - 1);
	}
	
	//Retorna el valor de la carta actual en juego.
	
	public String getValor(){
		return valor;
	}
	
	//Retorna el palo de la carta actual en juego.
	
	public char getPalo(){
		return palo;
	}
	
	//Pone una carta en juego.
	
	public void lanzar_carta(String carta){
		centro.addElement(carta);
		mazo.ingresar_carta(carta);
		centro.removeElementAt(0);
		valor = valorar_carta(carta);
		palo = carta.charAt(carta.length()-1);
	}
	
	//Indica si el juego debe continuar.
	
	public boolean parada(){
		if(j1.getTamaño_mano() == 0 || j2.getTamaño_mano() == 0 || j3.getTamaño_mano() == 0 || j4.getTamaño_mano() == 0 || mazo.getTamaño_baraja() < 3){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Cambia el palo de la carta actual en juego.
	
	public void cambiar_palo(int N){
		switch(N){
			case 1:
				palo = 'C';
				break;
			case 2:
				palo = 'D';
				break;
			case 3:
				palo = 'T';
				break;
			case 4:
				palo = 'P';
				break;
		}
	}
	
	//Elige la última carta válida de la mano de un jugador.
	
	public int elegir_carta(int jugador){
		int salida = 0;
		switch(jugador){
			case 2:
				for(int x=0; x<j2.getTamaño_mano(); x++){
					if(validar_carta(j2.ver_carta(x))){
						salida = x;
					}
				}
				break;
			case 3:
				for(int x=0; x<j3.getTamaño_mano(); x++){
					if(validar_carta(j3.ver_carta(x))){
						salida = x;
					}
				}
				break;
			case 4:
				for(int x=0; x<j4.getTamaño_mano(); x++){
					if(validar_carta(j4.ver_carta(x))){
						salida = x;
					}
				}
				break;
		}
		return salida;
	}
	
	//Indica cuál es el jugador que ganó.
	
	public int determinar_ganador(){
		int salida = 0;
		if(j1.getTamaño_mano() == 0){
			salida = 1;
		}
		if(j2.getTamaño_mano() == 0){
			salida = 2;
		}
		if(j3.getTamaño_mano() == 0){
			salida = 3;
		}
		if(j4.getTamaño_mano() == 0){
			salida = 4;
		}
		return salida;
	}
	
	//Retorna el vector del área de juego.
	
	public Vector <String> getCentro(){
		return centro;
	}
	
}
