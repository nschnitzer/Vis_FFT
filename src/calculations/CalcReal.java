//*********************************************
// Nathan Schnnitzer
// CalcReal.java
// 1/3/18
// Calculates Imaginary (cos) Fourier Coefficients
//*********************************************

package calculations;

public class CalcReal
{
	/**
	 * An overloaded method to produce Fourier Coefficients for a signal
	 * with a sample size that is smaller than the signal size. Uses interpolation. 
	 * @param radiansSignal A double[] containing the radians for the signal.
	 * @param signal A double[] containing the signal
	 * @param NN An int containing the desired sample size
	 * @param k An int containing the harmonic
	 * @return
	 */
	public double[] calc(double[] radiansSignal, double[] signal, int NN, int k)
	{
		// interpolate the signal for the number of samples
		double[] interpolateRads = new double[NN];
		double[] interpolateSignal = new double[NN];
		double[] REC = new double[NN];

		// generate radians based on sample size
		for (int i = 0; i < interpolateRads.length; i++)
		{
			interpolateRads[i] = (((360 / NN) * i) * (Math.PI / 180));
		}

		// interpolate signal based on sample size
		InterpolatePoly poly = new InterpolatePoly(radiansSignal, signal,
				interpolateRads, 3);
		interpolateSignal = poly.getInterpolateSignal();

		double RECSum = 0;
		
		REC[0] = (signal[0] * Math.cos(radiansSignal[0]));
		System.out.println("Harmonic# " + k );
		System.out.println("REC(n=0): " + REC[0]);
		for (int i = 0; i < REC.length; i++)
		{
			// count backwards through the table by k (the harmonic size)
			//((i * k) % (NN))
			
			// for when the modular value is 0
			// prevents array out of bounds
			// REC[array.length - 0] produces exception
			// use the first sin value instead
			if ((((i * k) % (NN))) == 0)
			{
				REC[i] = signal[i] * Math.cos(radiansSignal[0]);
			} 
			// every other case
			else
			{
				REC[i] = interpolateSignal[i]
						* Math.cos(interpolateRads[REC.length
								- (((i * k) % (NN)))]);
			}
			System.out.println("REC(n=" + i + "): " + REC[i]);
		}

		return REC;
	}
	
	/**
	 * An overloaded method that produces the Fourier Coefficients for a signal
	 * sample size that is the same size as the signal. No interpolation. 
	 * @param radiansSignal A double[] containing the radians for the signal
	 * @param signal A double[] containing the signal
	 * @param k An int containing the harmonic
	 * @return
	 */
	public double[] calc(double[] radiansSignal, double[] signal, int k)
	{
		double[] REC = new double[signal.length];

		double RECSum = 0;
		
		REC[0] = (signal[0] * Math.cos(radiansSignal[0]));
		System.out.println("Harmonic# " + k );
		System.out.println("REC(n=0): " + REC[0]);
		for (int i = 1; i < REC.length; i++)
		{
			// count backwards through the table by k (the harmonic size)
			//((i * k) % (NN))
			
			// for when the modular value is 0
			// prevents array out of bounds
			// REC[array.length - 0] produces exception
			// use the first sin value instead
			if ((((i * k) % (radiansSignal.length))) == 0)
			{
				REC[i] = signal[i] * Math.cos(radiansSignal[0]);
			} 
			// Every other case
			else
			{
				REC[i] = 
						+ (signal[i] * Math.cos(radiansSignal[REC.length
								- (((i * k) % (radiansSignal.length)))]));
			}
			System.out.println("REC(n=" + i + "): " + REC[i]);
		}

		return REC;
	}
}
