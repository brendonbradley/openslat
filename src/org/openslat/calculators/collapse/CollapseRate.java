package org.openslat.calculators.collapse;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.RombergIntegrator;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;
import org.apache.commons.math3.distribution.LogNormalDistribution;
import org.openslat.control.SlatInputStore;
import org.openslat.numerical.MagnitudeAdaptiveQuadratureIntegrator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Collapse hazard relationship or Collapse-Rate. Represents the annual
 * frequency of collapse.
 * 
 * CollapseRate can be constructed with an IMR relationship and an PCIM relationship and
 * an integration method.
 * 
 * @author Alan Williams
 */
public class CollapseRate {

	private SlatInputStore sis;
	//private UnivariateIntegrator integrator = new RombergIntegrator();
	private UnivariateIntegrator integrator = new MagnitudeAdaptiveQuadratureIntegrator();
	
	@JsonIgnore
	private LogNormalDistribution distribution;

	/**
	 * Returns the rate of exceedance for a specified probability of collapse.
	 * 
	 * @param edp
	 *            probability of collapse
	 * @return rate of exceedance
	 */
	public double evaluate() {

		distribution = this.getSis().getStructure().getCollapse().getPcim().calcDistribution();
		
		final CollapseRate collapseRate = this;
		return integrator.integrate(100000000, new UnivariateFunction() {
			public double value(double t) {
				return collapseRate.getDistribution()
						.cumulativeProbability(t)
						* Math.abs(collapseRate.getSis().getStructure().getIm().retrieveImr().derivative(t));
			}
		}, 1e-7, sis.getIm().getMaxIMValue()); //TODO: make robust
	}

	
	/**
	 * Returns the rate of exceedance for a specified probability of collapse.
	 * 
	 * @param edp
	 *            probability of collapse
	 * @return rate of exceedance
	 */
	public double evaluate_with_variable_transform_to_t() {

		final CollapseRate collapseRate = this;
		return integrator.integrate(10000, new UnivariateFunction() {
			public double value(double t) {
				return collapseRate.getSis().getStructure().getCollapse().getPcim().calcDistribution()
						.cumulativeProbability(1 / t - 1)
						* Math.abs(collapseRate.getSis().getStructure().getIm().retrieveImr().derivative(1 / t - 1))
						* (-1 / Math.pow(t, 2));
			}
		}, 0, 1);
	}


	public UnivariateIntegrator getIntegrator() {
		return integrator;
	}

	public void setIntegrator(UnivariateIntegrator integrator) {
		this.integrator = integrator;
	}


	public SlatInputStore getSis() {
		return sis;
	}


	public void setSis(SlatInputStore sis) {
		this.sis = sis;
	}


	public LogNormalDistribution getDistribution() {
		return distribution;
	}


	public void setDistribution(LogNormalDistribution distribution) {
		this.distribution = distribution;
	}

}
