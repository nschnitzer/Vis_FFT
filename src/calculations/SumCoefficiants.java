//*********************************************************
// Nathan Schnitzer
// SumCoefficients.java (I know its mis spelt but I already committed it to Java and I'm too lazy to change it)
// 1/5/18
// Gets sum of the Fournier Coeffients

package calculations;

public class SumCoefficiants
{
	/**
	 * A method that sums the Fourier Coefficients based on sample size.
	 * @param C A double[] containing the coefficients
	 * @param NN A int containing the sample size
	 * @return A double containing the sum
	 */
	public double sum(double[] C, double NN)
	{
		double CSum = 0;
		// sum the coefficients
		for (int i = 0; i < C.length; i++)
		{
			CSum += C[i];
		}

		// the sum of all the real parts divided by NS, the number of samples
		CSum = CSum / NN;
		
		return CSum;
	}

}