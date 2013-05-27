package org.openslat.options;

import org.openslat.model.fragilityfunctions.EpistemicCorrArrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Session Bean implementation class CalculationOptions
 */
@JsonSerialize
public class CalculationOptions {

	private boolean collapse;
	private boolean collLossType;
	private boolean downTime;

	@JsonIgnore
	private EpistemicUncertOptions epistemicUncertOptions;
	@JsonIgnore
	private CorrelationOptions correlationOptions;

	// is this where this goes?
	// private EDPIMCorrelations edpIMCorrelations;
	
	@JsonIgnore
	private EpistemicCorrArrays epistemicCorrArrays;
	@JsonIgnore
	private EpistemicLogicTreeValues epistemicLogicTreeValues;

	public boolean isCollapse() {
		return collapse;
	}

	public void setCollapse(boolean collapse) {
		this.collapse = collapse;
	}

	public boolean isCollLossType() {
		return collLossType;
	}

	public void setCollLossType(boolean collLossType) {
		this.collLossType = collLossType;
	}

	public boolean isDownTime() {
		return downTime;
	}

	public void setDownTime(boolean downTime) {
		this.downTime = downTime;
	}

	public EpistemicUncertOptions getEpistemicUncertOptions() {
		return epistemicUncertOptions;
	}

	public void setEpistemicUncertOptions(
			EpistemicUncertOptions epistemicUncertOptions) {
		this.epistemicUncertOptions = epistemicUncertOptions;
	}

	public CorrelationOptions getCorrelationOptions() {
		return correlationOptions;
	}

	public void setCorrelationOptions(CorrelationOptions correlationOptions) {
		this.correlationOptions = correlationOptions;
	}

	// public EDPIMCorrelations getEdpIMCorrelations() {
	// return edpIMCorrelations;
	// }
	//
	// public void setEdpIMCorrelations(EDPIMCorrelations edpIMCorrelations) {
	// this.edpIMCorrelations = edpIMCorrelations;
	// }

	public EpistemicCorrArrays getEpistemicCorrArrays() {
		return epistemicCorrArrays;
	}

	public void setEpistemicCorrArrays(EpistemicCorrArrays epistemicCorrArrays) {
		this.epistemicCorrArrays = epistemicCorrArrays;
	}

	public EpistemicLogicTreeValues getEpistemicLogicTreeValues() {
		return epistemicLogicTreeValues;
	}

	public void setEpistemicLogicTreeValues(
			EpistemicLogicTreeValues epistemicLogicTreeValues) {
		this.epistemicLogicTreeValues = epistemicLogicTreeValues;
	}
}
