/*****************************************
 * Christian Taborda                     *
 * 1632081-3743                          *
 * ***************************************
 */

package ochosLocos;

import java.util.Vector;

public class Jugador {
	
	//Atributo
	
	private Vector <String> mano;
	
	//Métodos
	
	public Jugador(){
		mano = new Vector <String> ();
	}
	
	//Añade una carta a la mano del jugador.
	
	public void obtener_carta(String carta){
		mano.addElement(carta);
	}
	
	//Retorna una carta de la mano del jugador.
	
	public String jugar_carta(int pos){
		String carta;
		carta = mano.elementAt(pos);
		mano.removeElementAt(pos);
		return carta;
	}
	
	//Retorna la cantidad de cartas de la mano del jugador.
	
	public int getTamaño_mano(){
		return mano.size();
	}
	
	//Retorna el valor de una carta.
	
	public String valorar_carta(String carta){
		String valor;
		valor = carta.substring(0, carta.length()-1);
		return valor;
	}
	
	//Verifica si hay almenos una carta válida para jugar en la mano del jugador.
	
	public boolean verificar_mano(String valor, char palo){
		if(valor.equals("A")){
			for(int x=0; x<mano.size(); x++){
				if(valorar_carta(mano.elementAt(x)).equals("A")){
					return true;
				}
			}
			return false;
		}
		for(int x=0; x<mano.size(); x++){
			if((valorar_carta(mano.elementAt(x)).equals("8")) || (valorar_carta(mano.elementAt(x)).equals(valor)) || (mano.elementAt(x).charAt(mano.elementAt(x).length()-1) == palo)){
				return true;
			}
		}
		return false;
	}
	
	//Muestra una carta de la mano del jugador.
	
	public String ver_carta(int pos){
		return mano.elementAt(pos);
	}
	
	//Retorna las cartas de la mano del jugador en forma de String.
	
	public String mostrar_mano(){
		String salida = "\n";
		for(int x=0; x<mano.size(); x++){
			salida += (x+1) + ".\t";
		}
		salida += "\n";
		for(int x=0; x<mano.size(); x++){
			salida += mano.elementAt(x) + "\t";
		}
		return salida;
	}
	
}
