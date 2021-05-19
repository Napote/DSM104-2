import java.util.*;

public class Ejercicio3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int pares = 0, impares = 0;
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.print("Ingrese el valor de n: ");
		int n = teclado.nextInt();
		
		for (int i=1; i<=n; i++){
			System.out.print("Ingrese el valor de la posicion " + i +": ");
			int num = teclado.nextInt();
			
			if(num%2==0){
				pares += 1;
				
			}
			else{
				impares += 1;
			}
		}
		
		System.out.println("La cantidad de numeros pares: " + pares);
		System.out.println("La cantidad de numeros impares: " + impares);
	}

}
