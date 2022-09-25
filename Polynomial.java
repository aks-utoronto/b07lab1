public class Polynomial {
	
	double [] coefficients; // change size

	public Polynomial() {
		coefficients = new double[1];
		coefficients[0] = 0;
	}	

	public Polynomial(double []array_parameter) {
		coefficients = array_parameter;
	}

	public Polynomial add(Polynomial poly) {
		
		int arr_length = Math.max(poly.coefficients.length, this.coefficients.length);

		double [] coeff_add = new double[arr_length];
		coeff_add[arr_length-1] = 0;

		for(int i = 0; i < this.coefficients.length; i++) {
			coeff_add[i] += this.coefficients[i];
		}


		for(int i = 0; i < poly.coefficients.length; i++) {
			coeff_add[i] += poly.coefficients[i];
		}

		Polynomial poly1 = new Polynomial(coeff_add);
		
		return poly1;
	}

	public double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < coefficients.length; i++) {
			result += (coefficients[i])*Math.pow(x, i);
		}

		return result;
	}


	public boolean hasRoot(double x) {
		if(evaluate(x) == 0) {
			return true;
		}
		return false;
	}



}