/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.util.List;

import fr.firesoft.fireTime.entity.horaire.CycleTravail;
import fr.firesoft.fireTime.entity.horaire.SequenceTravail;

/**
 * @author xbeaufils
 *
 */
public interface CycleManagerLocal {
	public List<CycleTravail> makeListForCycle(Integer idfCycle);
	public CycleTravail select(Integer idfCycle);
	public void save (CycleTravail cycle);
	public void addSequence( Integer idfCycle, Integer idfStatut);
	public void removeSequence(Integer idfCycle, Integer ordre);
	public List<SequenceTravail> makeListSequenceForCycle(Integer idfCycle);
	
}
