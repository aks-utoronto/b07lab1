// import File;
// import java.io.start;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;


public class Polynomial {
	
	double [] coefficients; // change size
	int [] powers;


	public Polynomial(double []array_parameter, int []power_array) {
		coefficients = new double[array_parameter.length];
		for(int i = 0; i < array_parameter.length; i++) {
			coefficients[i] = array_parameter[i];
		}

		powers = new int[power_array.length];
		for(int i = 0; i < power_array.length; i++) {
			powers[i] = power_array[i];
		}
	}

	public Polynomial(File input_file) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader(input_file));
		String str = input.readLine();
		int count = 0;
		String [] count_arr = str.split("");
		
		String [] str_arr = str.split("[\\+]");
		double []coefficients_f = new double[count_arr.length];
		int []powers_f = new int[count_arr.length];
		int c_counter = 0;
		int p_counter = 0;

		for(int i = 0; i < str_arr.length; i++) {
			
			String [] str_arr2 = str_arr[i].split("\\-");

			for(int j = 0; j < str_arr2.length; j++) {
				if(str_arr2[j].indexOf("x") == -1) {
					if(j > 0) {
						coefficients_f[c_counter] = Double.parseDouble("-" + str_arr2[j]);
						c_counter++;
						powers_f[p_counter] = 0;
						p_counter++;
					} else {
						coefficients_f[c_counter] = Double.parseDouble(str_arr2[j]);
						c_counter++;
						powers_f[p_counter] = 0;
						p_counter++;
					}
				} else {
					String [] str_arr3 = str_arr2[j].split("");
					if(j > 0) {
						coefficients_f[c_counter] = Double.parseDouble("-" + str_arr3[0]);
						c_counter++;
						powers_f[(p_counter)] = Integer.parseInt(str_arr3[2]);
						p_counter++;
					} else {
						coefficients_f[c_counter] = Double.parseDouble(str_arr3[0]);
						c_counter++;
						powers_f[(p_counter)] = Integer.parseInt(str_arr3[2]);
						p_counter++;
					}
				}
			}
		}


		coefficients = new double[p_counter];
		powers = new int[p_counter];

		for(int i  = 0; i < p_counter; i++) {
			coefficients[i] = coefficients_f[i];
			powers[i] = powers_f[i];
		}

	}


	public Polynomial add(Polynomial poly) {

		int max_length = powers.length + poly.powers.length;
		
		int []p1_powers = new int[max_length]; // list of powers for new polynomial
		double []p1_coeff = new double[max_length];

		int coeff_index = 0;
		for(int i = 0; i < powers.length; i++) {
			p1_powers[i] = powers[i];
			p1_coeff[i] = coefficients[i];
			coeff_index = i;
		}

		coeff_index += 1;
		int counter = 0;
		boolean is_repeat = false;
		for(int i = 0; i < poly.powers.length; i++) {
			is_repeat = false;
			for(int j = 0; j < powers.length; j++) {
				if(powers[j] == poly.powers[i]) {
					if((p1_coeff[j] + poly.coefficients[i]) != 0.0) {
						p1_coeff[j] += poly.coefficients[i];
					}

					is_repeat = true;
				}
			}
			//Make a new entry
			if(!is_repeat) {
				if((poly.coefficients[i]) != 0.0) {
					p1_powers[coeff_index+counter] = poly.powers[i];
					p1_coeff[coeff_index+counter] = poly.coefficients[i];
					counter++;
				}
			}
			
		}

		int []f_powers = new int[coeff_index + counter];
		double []f_coeff = new double[coeff_index + counter];
		//Trim arrays
		for(int i = 0; i < coeff_index + counter; i++) {
			f_powers[i] = p1_powers[i];
			f_coeff[i] = p1_coeff[i];
		}

		Polynomial p1 = new Polynomial(f_coeff, f_powers);

		return p1;
		
	}


	public Polynomial multiply(Polynomial poly) {

		Polynomial [] poly_arr = new Polynomial[poly.powers.length * powers.length];

		int counter = 0;
		for(int i = 0; i < powers.length; i++) {
			for(int j = 0; j < poly.powers.length; j++) {
				double [] p1_coeff = {coefficients[i] * poly.coefficients[j]};
				int [] p1_powers = {powers[i] + poly.powers[j]};

				poly_arr[counter] = new Polynomial(p1_coeff, p1_powers);
				counter++;
		

			}
		}

		int index = 0;
		Polynomial new_poly = poly_arr[index];
		index++;
		while(poly_arr[index].coefficients[0] == 0.0) {
			new_poly = poly_arr[index];
			index++;
		}

		//Polynomial new_poly = poly_arr[0];
		for(int i = index; i < poly_arr.length; i++) {
			if(poly_arr[i].coefficients[0] != 0.0)
				new_poly = new_poly.add(poly_arr[i]);
		}

		return new_poly;

	}

	public double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < coefficients.length; i++) {
			result += (coefficients[i])*Math.pow(x, powers[i]);
		}
    
		return result;
	}


	public boolean hasRoot(double x) {
		if(evaluate(x) == 0) {
			return true;
		}
		return false;
	}



	public void saveToFile(String path) throws Exception {
		FileWriter writer = new FileWriter(path);  
		BufferedWriter buffer = new BufferedWriter(writer);
		String output = "";
		for(int i = 0; i < coefficients.length; i++) {
			int coeff_int = (int)coefficients[i];
			String coeff_output = "";
			if(coeff_int > 0 && i != 0) {
				coeff_output += "+" + Integer.toString(coeff_int);
			} else {
				coeff_output += Integer.toString(coeff_int);
			}

			if(powers[i] != 0) {
				output += coeff_output + "x" + Integer.toString(powers[i]);
			} else {
				output += coeff_output;
			}
			
		}  
		buffer.write(output);  
		buffer.close();  
	}



}