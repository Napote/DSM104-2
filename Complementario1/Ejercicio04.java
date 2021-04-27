import java.util.Scanner;

/*Programa que pide una serie de puntos (x,y) y luego imprime puntos hay en cada cuadrante del plano (x,y)*/
import java.awt.geom.Point2D;
class Main {
  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in); 
    int n;
    int primerQ = 0, segundoQ = 0, tercerQ = 0, cuartoQ = 0;
    int yPositiva= 0, yNegativa= 0, xPositiva= 0, xNegativa=0;
    int puntosOrigen=0;

    System.out.print("Ingrese el numero de puntos: ");
    n = teclado.nextInt(); 
    
    for (int i=0;i<n;i++){
      System.out.printf("coordenada %d (x,y) : ",i);
      double x = teclado.nextDouble();
      double y = teclado.nextDouble(); 

      if (x > 0 && y > 0) 
        primerQ++;  
      else if (x < 0 && y > 0) 
        segundoQ++;  
      else if (x < 0 && y < 0) 
        tercerQ++;  
      else if (x > 0 && y < 0) 
        cuartoQ++;
      else if (x == 0 && y > 0) 
        yPositiva++;  
      else if (x == 0 && y < 0) 
        yNegativa++; 
      else if (y == 0 && x < 0) 
        xNegativa++;  
      else if (y == 0 && x > 0) 
        xPositiva++;
      else
        puntosOrigen++;
    } 

    System.out.println("RESULTADOS");
    System.out.println("Puntos en primer cuadrante: " + primerQ);
    System.out.println("Puntos en el segundo cuadrante: " + segundoQ);
    System.out.println("Puntos en el tercer cuadrante: " + tercerQ);
    System.out.println("Puntos en el cuarto cuadrante: " + cuartoQ);
    System.out.println("Puntos en el axis y positivo: " + yPositiva);
    System.out.println("Puntos en el axis y negativo: " + yNegativa);
    System.out.println("Puntos en el axis x positivo: " + xPositiva);
    System.out.println("Puntos en el axis x negativo: " + xNegativa);
    System.out.println("Puntos en el origen: " + puntosOrigen);


  }
}