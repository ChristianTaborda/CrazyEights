/*****************************************
 * Christian Taborda                     *
 * 1632081-3743                          *
 * ***************************************
 */

package ochosLocos;

import java.util.Vector;
import java.util.Random;

public class Baraja {
	
	//Atributos
	
	private Vector <String> cartas;
		
	//Métodos
	
	public Baraja(){
		
		cartas = new Vector <String> ();
		
		//Corazones
		
		for(int x=2; x<11; x++){			
			cartas.addElement(x + "C");
		}
		
		cartas.addElement("JC");
		cartas.addElement("QC");
		cartas.addElement("KC");
		cartas.addElement("AC");
		
		//Diamantes
		
		for(int x=2; x<11; x++){
			cartas.addElement(x + "D");
		}
		
		cartas.addElement("JD");
		cartas.addElement("QD");
		cartas.addElement("KD");
		cartas.addElement("AD");
		
		//Treboles
		
		for(int x=2; x<11; x++){
			cartas.addElement(x + "T");
		}
		
		cartas.addElement("JT");
		cartas.addElement("QT");
		cartas.addElement("KT");
		cartas.addElement("AT");
		
		//Picas
		
		for(int x=2; x<11; x++){
			cartas.addElement(x + "P");
		}
		
		cartas.addElement("JP");
		cartas.addElement("QP");
		cartas.addElement("KP");
		cartas.addElement("AP");
		
	}
	
	//Retorna una carta aleatoria de la baraja.
	
	public String sacar_carta(Vector <String> V){
		String salida;		
		Random aleatorio = new Random();
		int A = aleatorio.nextInt(cartas.size());
		salida = cartas.elementAt(A);
		cartas.removeElementAt(A);	
		return salida;
	}
	
	//Retorna la cantidad de cartas en la baraja.
	
	public int getTamaño_baraja(){
		return cartas.size();
	}
	
	//Verifica si la baraja está vacía.
	
	public boolean vacia(){
		if(cartas.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Añade una carta a la baraja.
	
	public void ingresar_carta(String carta){
		cartas.addElement(carta);
	}
	
}
