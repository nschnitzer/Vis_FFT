//**************************************************
// Nathan Schnitzer
// CalcImag.java
// 1/3/18
// Calculates the imaginary (sin) fourier coefficients

package calculations;

public class CalcImag
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
		
		double[] interpolateRads = new double[NN];
		double[] interpolateSignal = new double[NN];

		// produce the radians for the signal sample size
		for (int i = 0; i < interpolateRads.length; i++)
		{
			interpolateRads[i] = (((360 / NN) * i) * (Math.PI / 180));
		}

		// interpolate the signal based on the sample size
		InterpolatePoly poly = new InterpolatePoly(radiansSignal, signal,
				interpolateRads, 3);
		interpolateSignal = poly.getInterpolateSignal();

		// used to sum the coefficients
		double IMCSum = 0;
		double[] IMC = new double[NN];
		
		// base case
		IMC[0] = (signal[0] * Math.sin(-radiansSignal[0]));
		
		// output the coefficient values
		System.out.println("Harmonic# " + k);
		System.out.println("IMC(n=" + 0 + ") " + IMC[0]);
		
		// the rest of the cases
		for (int i = 1; i < IMC.length; i++)
		{
			// count backwards through the table by k (the harmonic size)
			//((i * k) % (NN))
			
			// for when the modular value is 0
			// prevents array out of bounds
			// IMC[array.length - 0] produces exception
			// use the first sin value instead
			if ((((i * k) % (NN))) == 0)
			{
				IMC[i] = signal[i] * Math.sin(radiansSignal[0]);
			} 
			// for every other case
			else
			{
				IMC[i] = interpolateSignal[i]
						* Math.sin(interpolateRads[IMC.length
								- (((i * k) % (NN)))]);
			}
			System.out.println("IMC(n=" + i + "): " + IMC[i]);
		}

		return IMC;
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

		double[] IMC = new double[signal.length];

		// used to the sum the coefficients
		double IMCSum = 0;
		
		// base case
		IMC[0] = (signal[0] * Math.sin(-radiansSignal[0]));
		
		// output coefficients
		System.out.println("Harmonic# " + k);
		System.out.println("IMC(n=" + 0 + ") " + IMC[0]);
		
		
		for (int i = 1; i < IMC.length; i++)
		{
			// count backwards through the table by k (the harmonic size)
			//((i * k) % (NN))
			
			// for when the modular value is 0
			// prevents array out of bounds
			// IMC[array.length - 0] produces exception
			// use the first sin value instead
			if ((((i * k) % (radiansSignal.length))) == 0)
			{
				IMC[i] = signal[i] * Math.sin(radiansSignal[0]);
			} 
			// Every other case
			else
			{
				IMC[i] = (signal[i] * Math.sin(radiansSignal[IMC.length
						- (((i * k) % (radiansSignal.length)))]));
			}

			System.out.println("IMC(n=" + i + "): " + IMC[i]);
		}

		return IMC;
	}
}