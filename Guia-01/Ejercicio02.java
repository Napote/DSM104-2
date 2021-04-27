import java.util.*;

public class Ejercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int mayores = 0, menores = 0;
		
		Scanner teclado = new Scanner(System.in);
		
		for(int i = 0;i<10;i++){
			double nota = teclado.nextDouble();
			
			if(nota >= 7){
				mayores += 1;
			}
			else{
				menores += 1;
			}
		}
		
		System.out.println("Cantidad de alumnos con notas mayores o iguales a 7: " + mayores);
		System.out.println("Cantidad de alumnos con notas menores a 7: " + menores);
	}

}
