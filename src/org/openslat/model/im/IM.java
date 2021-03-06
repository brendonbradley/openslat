package org.openslat.model.im;

import java.util.ArrayList;

import org.openslat.control.SlatInputStore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Session Bean implementation class IM
 */
@JsonSerialize
public class IM {

	private String name;
	@JsonIgnore
	private SlatInputStore sis;
	private ArrayList<IMR> iMR = new ArrayList<IMR>();
	private double minIMValue;
	private double maxIMValue;

	/**
	 * Randomly returns an IM-R relationship according to the epistemic weights.
	 * 
	 * @return
	 */
	public IMR retrieveImr() {
		// TODO : exceptions..
		if (iMR.size() == 0) {
			return null;
		} else if (true) { //iMR.size() == 1 TODO: bastardized this
			return iMR.get(0);
		} else {
			double rn = sis.getCalculationOptions()
					.getEpistemicLogicTreeValues().getRandIMR();
			ArrayList<Double> epistemicWeights = new ArrayList<Double>();
			double total = 0;
			for (IMR each : iMR) {
				total = total + each.getEpistemicWeight();
				epistemicWeights.add(total);
			}
			// TODO: check total adds to 1
			for (double each : epistemicWeights) {
				if (rn <= each) {
					return iMR.get(epistemicWeights.indexOf(each));
				}
			}
		}
		return null;
	}

	public ArrayList<IMR> getiMR() {
		return iMR;
	}

	public void setiMR(ArrayList<IMR> iMR) {
		this.iMR = iMR;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setMaxIMValue(double maxValue){
		this.maxIMValue = maxValue;
	}
	
	public double getMaxIMValue() {
		return maxIMValue;
	}

	public double getMinIMValue() {
		return minIMValue;
	}

	public void setMinIMValue(double minIMValue) {
		this.minIMValue = minIMValue;
	}

	public SlatInputStore getSis() {
		return sis;
	}

	public void setSis(SlatInputStore sis) {
		this.sis = sis;
	}

}
