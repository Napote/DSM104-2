import java.util.*;
public class Ejercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double n1 = 0, n2 = 0;
		
		Scanner teclado =  new Scanner(System.in);
		
		System.out.println("Ingrese el valor del numero 1: ");
		n1 = teclado.nextDouble();
		System.out.println("Ingrese el valor del numero 2: ");
		n2 = teclado.nextDouble();
		
		if(n1%n2==0){
			System.out.println("Los numeros son divisibles entre sí.");
		}
		else{
			System.out.println("Los numeros no son divisibles entre sí.");
		}

	}

}
