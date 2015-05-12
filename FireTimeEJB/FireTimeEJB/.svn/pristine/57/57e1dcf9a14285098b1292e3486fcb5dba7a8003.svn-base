/**
 * 
 */
package fr.firesoft.fireTime.genetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.firesoft.fireTime.genetic.model.Agent;
import fr.firesoft.fireTime.genetic.model.Piquet;
import fr.firesoft.fireTime.genetic.model.SolutionDepart;
import fr.firesoft.fireTime.genetic.model.SolutionDepartFPT;


/**
 * @author xavier
 *
 */
public class SolverSolutionDepart {

	private List<Agent> lstAgent;
	private List<String> besoin;
	
	private List<SolutionDepart> population;
	private SolutionDepart bestSolution;
	
	private List<SolutionDepart> roulette;

	private static Random randomizer = new Random();

	
	public static Integer MAX_GENERATION=20;
	public static Integer MAX_POPULATION=20;
	public static Float TAUX_CROISEMENT = new Float(0.7);
	
	
	public SolverSolutionDepart() {
		population = new ArrayList<SolutionDepart>();
	}
	
	public static void main(String[] args) {
		List<String> besoin = new ArrayList<String>();
		besoin.add("CAFPT");
		besoin.add("CONDFPT");
		besoin.add("EQINC");
		besoin.add("EQINC");
		besoin.add("EQINC");
		besoin.add("EQINC");

		List<Agent> lstAgent = new ArrayList<Agent>();
		lstAgent.add(new Agent("01",new String[]{"CAFPT", "CAVSAV","EQINC", "CONDFPT"}));
		lstAgent.add(new Agent("02",new String[]{"EQINC", "CONDFPT"}));
		lstAgent.add(new Agent("03",new String[]{"EQINC"}));
		lstAgent.add(new Agent("04",new String[]{"EQINC"}));
		lstAgent.add(new Agent("05",new String[]{"EQINC"}));
		lstAgent.add(new Agent("06",new String[]{"EQINC"}));
		lstAgent.add(new Agent("07",new String[]{"EQVSAV"}));

		SolverSolutionDepart solver = new SolverSolutionDepart();
		try {
			solver.calcule(besoin, lstAgent);
		}
		catch (NotEnoughEffectifException e) {
			System.err.println("Not enough effectif");
		}
		System.out.println("------------ Solution -------------\n" + solver.getBestSolution());
	}
	
	
	public void calcule(List<String> besoin,  List<Agent> lstAgent) throws NotEnoughEffectifException{
		if  (besoin.size() > lstAgent.size())
			throw new NotEnoughEffectifException();
		this.besoin = besoin;
		this.lstAgent = lstAgent;
		// Rajout des besoins supplémentaires
		for (int i = besoin.size(); i<lstAgent.size(); i++) {
			besoin.add("@");
		}
		// Calcul de la ponderation des besoins
		Map<String,Float>ponderation = this.calculImportance();
		Float bestAdaptation = new Float(0);
		for (String aBesoin: besoin) {
			bestAdaptation += ponderation.get(aBesoin);
		}
		for (int i=0; i< SolverSolutionDepart.MAX_POPULATION; i++)
			population.add(new SolutionDepartFPT(this, ponderation));
		for (int i=0; i<MAX_GENERATION; i++) {
			this.evaluation();
			System.out.println("------- Nouvelle Generation " + i + " ------");
			for (SolutionDepart solut: population) {
				System.out.println("Population " + solut);
			}
			if (bestSolution.getAdaptation() == bestAdaptation )
				break;
			List<SolutionDepart> newGeneration = new ArrayList<SolutionDepart>();
			for (int j=0; j<population.size()/2; j++) {
				SolutionDepart[] parents = this.selectionParent();
				SolutionDepart[] enfants = this.croisement(parents[0], parents[1]);
				this.mutation(enfants[0]);
				this.mutation(enfants[1]);
				newGeneration.add(enfants[0]);
				newGeneration.add(enfants[1]);
			}
			population = newGeneration;
		}
	}	
	
	private Map<String, Float> calculImportance() {
		Map<String, Float> mapImportance = new HashMap<String, Float>();
		for (String strBesoin : besoin) {
			int nbAgentCompetent = 0;
			for (Agent agent: lstAgent)
				if (agent.getLstCompetence().contains(strBesoin))
					nbAgentCompetent++;
			Float importance;
			if (nbAgentCompetent == 0)
				importance=new Float(0);
			else
				importance = new Float( lstAgent.size()/nbAgentCompetent);
			mapImportance.put(strBesoin, importance);
		}
		return mapImportance;
	}
	
	
	private void mutation(SolutionDepart enfant) {
		float calcTauxMutation = randomizer.nextFloat();
		float tauxMax = 1;
		tauxMax = (float)0.3; //tauxMax / population.size();
		System.out.println("Taux calc " + calcTauxMutation + " Taux Max " +  tauxMax);
		if ( calcTauxMutation <= tauxMax) {
			System.out.println("Solution avant mutation : " + enfant);
			List<Piquet> lstPiquet = new ArrayList<Piquet>(enfant.getGenes().keySet());
			// Selection des chromosomes à muter
			int firstGene = randomizer.nextInt(enfant.getGenes().size());
			Piquet firstPiquet = lstPiquet.get(firstGene);
			int secondGene = randomizer.nextInt(enfant.getGenes().size());
			System.out.println("index premier gene " + firstGene + " index 2eme gene " +secondGene); 
			// Mutation des chromosomes sélectionnés
			Piquet secondPiquet = lstPiquet.get(secondGene);
			Agent agent = enfant.getGenes().get(firstPiquet);
			enfant.getGenes().put(firstPiquet, enfant.getGenes().get(secondPiquet));
			enfant.getGenes().put(secondPiquet, agent);	
			System.out.println("Solution aprés mutation : " + enfant);
		}
		
	}
	
	private SolutionDepart[] croisement(SolutionDepart pere, SolutionDepart mere) {
		SolutionDepart[] enfants = new SolutionDepartFPT[2];
		float calcTauxCroisement = randomizer.nextFloat();
		if (calcTauxCroisement <= SolverSolutionDepart.TAUX_CROISEMENT) {
			System.out.println("--------- Croisement à faire : ----------");
			System.out.println("pere : " + pere );
			System.out.println("mere : " + mere);
			SolutionDepart enfant1 = new SolutionDepartFPT( );
			SolutionDepart enfant2 = new SolutionDepartFPT( );
			int coupure = randomizer.nextInt(mere.getGenes().values().size());
			System.out.println("\tCoupure = " + coupure);
			int coupureEnCours = 0;
			for ( Piquet piquet : pere.getGenes().keySet()) {
				if (coupureEnCours < coupure) {
					if ( ! enfant1.getGenes().values().contains(pere.getGenes().get(piquet))) {
						enfant1.getGenes().put(piquet, pere.getGenes().get(piquet));
						System.out.println("\tAdding Matricule " + pere.getGenes().get(piquet).getMatricule() 
								+ " for " + piquet.getIdentifiant() 
								+ " in enfant1");
					}
					else {
						//enfant1.getGenes().put(piquet, mere.getGenes().get(piquet));
						System.out.println("\tMatricule " + pere.getGenes().get(piquet).getMatricule() 
								+ " present for " + piquet.getIdentifiant() 
								+ " in enfant1 " + enfant1
								+ " from pere");
						//Agent agent = pere.getGenes().get(piquet);
						//enfant1.setViable(false);						
					}
					if ( ! enfant2.getGenes().values().contains(mere.getGenes().get(piquet))) {
						enfant2.getGenes().put(piquet, mere.getGenes().get(piquet));
						System.out.println("\tAdding Matricule " + mere.getGenes().get(piquet).getMatricule() 
								+ " for " + piquet.getIdentifiant() 
								+ " in enfant2");
					}
					else {
						//enfant2.getGenes().put(piquet, pere.getGenes().get(piquet));
						System.out.println("\tMatricule " + mere.getGenes().get(piquet).getMatricule()  
								+ " present for " + piquet.getIdentifiant()
								+ " in enfant2 from mere");
						//Agent agent = mere.getGenes().get(piquet);
						//enfant2.setViable(false);						
					}
				}
				else {
					if ( ! enfant1.getGenes().values().contains(mere.getGenes().get(piquet))) {
						enfant1.getGenes().put(piquet, mere.getGenes().get(piquet));
						System.out.println("\tAdding Matricule " + mere.getGenes().get(piquet).getMatricule() 
								+ " for " + piquet.getIdentifiant() 
								+ " in enfant1");
					}
					else {						
						//enfant1.getGenes().put(piquet, pere.getGenes().get(piquet));
						System.out.println("\tMatricule " + mere.getGenes().get(piquet).getMatricule() 
								+ " present for " + piquet.getIdentifiant()
								+ " in enfant1 from mere");
						//Agent agent = mere.getGenes().get(piquet);
						//enfant1.setViable(false);
					}
					if ( ! enfant2.getGenes().values().contains(pere.getGenes().get(piquet))) {
						enfant2.getGenes().put(piquet, pere.getGenes().get(piquet));
						System.out.println("\tAdding Matricule " + pere.getGenes().get(piquet).getMatricule() 
								+ " for " + piquet.getIdentifiant() 
								+ " in enfant2");
					}
					else {
						/* 
						 * L'agent venant du pere est  deja present dans l'enfant
						 *  
						 */
						//enfant2.getGenes().put(piquet, mere.getGenes().get(piquet));
						//Agent agent = pere.getGenes().get(piquet);
						//enfant2.setViable(false);						
						System.out.println("\tMatricule " + pere.getGenes().get(piquet).getMatricule()  
								+ " present for " + piquet.getIdentifiant()
								+ " in enfant2  from pere");
					}
				}
				coupureEnCours ++;
			}
			// Insertion des agents absents
			for (Agent agent: lstAgent) {
				if (! enfant1.getGenes().values().contains(agent)) {
					for (Piquet piquet : pere.getGenes().keySet()) {
						if (enfant1.getGenes().get(piquet) == null) {
							enfant1.getGenes().put(piquet, agent);
							break;
						}
					}
				}
				if (! enfant2.getGenes().values().contains(agent)) {
					for (Piquet piquet : pere.getGenes().keySet()) {
						if (enfant2.getGenes().get(piquet) == null) {
							enfant2.getGenes().put(piquet, agent);
							break;
						}
					}
				}
			}
			// Tous les piquets du pere doivent être dans les enfants
/*			for (Map.Entry<Piquet, Agent> entry : pere.getGenes().entrySet()) {
				if ( enfant1.getGenes().get(entry.getKey()) == null ) {
					enfant1.getGenes().put(entry.getKey(), null);
				}
				if ( enfant2.getGenes().get(entry.getKey()) == null )
					enfant2.getGenes().put(entry.getKey(), null);
			}
*/			enfants[0] = enfant1;
			enfants[1] = enfant2;
			System.out.println("enfant croisé 1 : " + enfants[0]);
			System.out.println("enfant croisé 2 : " + enfants[1]);
		}
		else {
			System.out.println("Pas de croisement");
			enfants[0] = pere;
			enfants[1] = mere;
		}
		return enfants;
	}
	
	private void evaluation() {
		Float total = new Float(0);
		for (SolutionDepart solut : population) {
			solut.evaluate();
			total = total + solut.getAdaptation();
			if (bestSolution == null)
				bestSolution = solut;
			else {
				if (bestSolution.getAdaptation() < solut.getAdaptation() )
					bestSolution = solut;
			}
		}
		this.calculRoulette(total);
	}

	private void calculRoulette(Float total) {	
		roulette = new ArrayList<SolutionDepart>();
		for (int i=0; i < total; i++) {
			for (SolutionDepart aSolution : population) {				
				for (int quota = 0; quota< aSolution.getAdaptation(); quota ++)
					roulette.add(aSolution);
			}
		}
		System.out.println("calculeRoulette::Total = " + total);
	}
	
	private SolutionDepart[] selectionParent() {
		SolutionDepart[] parent = new SolutionDepartFPT[2];
		parent[0] = (SolutionDepartFPT) roulette.get(randomizer.nextInt(roulette.size()));
		int i = 0;
		while (i < 5) {
			parent[1] = (SolutionDepartFPT) roulette.get(randomizer.nextInt(roulette.size()));
			if (! parent[1].equals(parent[0]))
				i=5;
			i++;
		} 
		return parent;
	}
	
    public static int Calcule_PPCM (int Nb1, int Nb2) {
    	int Produit, Reste, PPCM;
    		
    	Produit = Nb1*Nb2;
    	Reste   = Nb1%Nb2;
    	while(Reste != 0){
    	    Nb1 = Nb2;
    	    Nb2 = Reste;
    	    Reste = Nb1%Nb2;
            }
    	PPCM = Produit/Nb2;
    	//		System.out.println("PGCD = " + Nb2 + " PPCM = " + PPCM);
    	return PPCM;		
    } // fin Calcule_PPCM
    
	public List<Agent> getLstAgent() {
		return lstAgent;
	}
	
	public List<String> getBesoin() {
		return this.besoin;
	}

	public List<SolutionDepart> getPopulation() {
		return population;
	}
	
	public void setPopulation(List<SolutionDepart> population) {
		this.population = population;
	}
	
	public SolutionDepart getBestSolution() {
		return this.bestSolution;
	}
	
}
