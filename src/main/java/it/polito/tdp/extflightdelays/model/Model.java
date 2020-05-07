package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> idMap;
	
	public Model() {
		idMap = new HashMap<Integer, Airport>();
	}
	
	public void creaGrafo(int distanzaMediaMin) {
		
		grafo = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		
		dao.loadAllAirports(idMap);
		
		Graphs.addAllVertices(grafo, idMap.values());
		
		List<Tratta> tratte = dao.getTratte(distanzaMediaMin);
		
		// Aggiungo gli archi al grafo, il quale, essendo non orientato, se ha gia' un arco a1-a2, NON inserira' l'arco a2-a1
		for(Tratta t : tratte) {
			Graphs.addEdge(grafo, idMap.get(t.getA1()), idMap.get(t.getA2()), t.getDistanzaMedia());
		}
		List<Airport> remove = new ArrayList<>();
		for(Airport a : grafo.vertexSet()) {
			if(!Graphs.vertexHasPredecessors(grafo, a) && !Graphs.vertexHasSuccessors(grafo, a)) {
				remove.add(a);
			}
		}
		grafo.removeAllVertices(remove);
	}

	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public String grafoToString() {
		String s = "";
		
		/* METODO SBAGLIATO perchè sto usando un grafo non orientato, di conseguenza ongi aeroporto avra' come successori tutti gli aeroporti ad esso collegato
		 * TRADOTTO: ho una tratta a1-a2 ed anche la sua copia a2-a1.
		for(Airport a1 : grafo.vertexSet()) {
			List<Airport> temp = Graphs.successorListOf(grafo, a1);
			for(Airport a2 : temp) {
				s += "Aeroporto1: "+a1.getId()+" aeroporto2: "+a2.getId()+" distanza media: "+grafo.getEdgeWeight(grafo.getEdge(a1, a2))+"\n";
			}
		}
		*/
		for(DefaultWeightedEdge e : grafo.edgeSet()) {
			s += "Aeroporto1: "+grafo.getEdgeSource(e).getId()+" aeroporto2: "+grafo.getEdgeTarget(e).getId()+" distanza media: "+grafo.getEdgeWeight(e)+"\n";
		}
		
		return s;
	}
}
