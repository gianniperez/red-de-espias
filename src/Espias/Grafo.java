package Espias;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grafo {
	// Representamos el grafo por su matriz de adyacencia
	private boolean[][] A;
	
	private HashMap<Tupla<Integer, Integer>, Integer> aristas;
	
	// La cantidad de vertices esta predeterminada desde el constructor
	public Grafo(int vertices)
	{
		A = new boolean[vertices][vertices];
		aristas = new HashMap<Tupla<Integer, Integer>, Integer>();
	}
	
	// Agregado de aristas
	public void agregarArista(int i, int j, int peso) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);
		agregarPeso(i, j, peso);
		
		A[i][j] = true;
		A[j][i] = true;
	}
	
	private void agregarPeso(int i, int j, int peso) {
		Tupla<Integer,Integer> arista = new Tupla<Integer,Integer>(i,j);
		aristas.put(arista, peso);
	}
	
	// Eliminacion de aristas
	public void eliminarArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		A[i][j] = false;
		A[j][i] = false;
	}

	// Informa si existe la arista especificada
	public boolean existeArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return A[i][j];
	}

	// Cantidad de vertices
	public int tamano()
	{
		return A.length;
	}
	
	// Vecinos de un vertice
	public Set<Integer> vecinos(int i)
	{
		verificarVertice(i);
		
		Set<Integer> ret = new HashSet<Integer>();
		for(int j = 0; j < this.tamano(); ++j) if( i != j )
		{
			if( this.existeArista(i,j) )
				ret.add(j);
		}
		
		return ret;		
	}
	
	// Verifica que sea un vertice valido
	private void verificarVertice(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		
		if( i >= A.length )
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j)
	{
		if( i == j )
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}
	
	public int dameVertice() {
		if (estaVacio()) {
			throw new RuntimeException("El grafo no tiene ning�n v�rtice");
		}
		Random random = new Random();
		int vertice = random.nextInt(this.tamano()-1);
		return vertice;
	}

	public boolean estaVacio() {
		return tamano() == 0;
	}
}
