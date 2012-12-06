/* 

 * By Magdalen Berns 2012.

 */

import java.io.*;

public class LocusPlotter{
	
	/*
	* instance variable
	*/
	private  final double Re = 3.4;
	private final double T = 2.5;
	private final double Rm = 1.2;
	private final double Le = Math.pow(10,-4);
	private final double Cm = 1.1 * Math.pow(10,-4);
	private final double Lm = 4.7 * Math.pow(10,-3);
	private double a;

	
	private String file;
	/*
	* zero-arg class constructor to initialize instance variables
	*/
	public LocusPlotter(){
		/*
		* preach to the converted
		*/
		file=null;	
		a=0.0;		
	}
	/* 
	*  argument set number to multiply by fundimental period.
	*  has been hard coded to 1 in main for this task.
	*/
	public void setOutPut(double n) throws IOException {

		/*
		* 
		* local variables 
		*
		*/
		double step = 0.1;
		double period = Math.PI*2;
		double t = 0;
		double omegaMax=314000; 
		double omega=0;
		

		/*instantiate file output object */ 	
		PrintWriter fout = new PrintWriter(new FileWriter(file));
		/* 
		* Calculate the value of the function, with y the reactive and x the resistive 
		*   
		*/	
		
		while (omega <= omegaMax){	
			
			if(omega>125){
				setA(Cm,omega, Lm);	
				double x =  resitive(Re, T, Rm, omega);	
				double y= reactive(T, Rm, Le, omega);
				fout.println(x + " " + y);
			}
			omega =omega + (period * step);				
		}
		//print statement and close output file
		System.out.println("\nxmgrace " + file + "\n");
		fout.close();  
   	
	}
	public double resitive(double Re, double T, double Rm, double omega){

		return Re + (( T * T  * Rm) /(( Rm * Rm ) + a * a));
	}
	public double reactive(double T, double Rm, double Le, double omega){
	
		return omega * Le + ((T * T * a) / ((Rm * Rm) +(a * a) ));
	}
	public void setA(double Cm, double omega, double Lm){
		a = (1/(omega* Cm)-omega * Lm);
	}
	public double getA(){
		return a; 
	}
	/*
	Main Methodßås
	*/
    public void setFileName(String aFile){
		this.file=aFile;
	}
	/*All seeing main method */
	public static void main(String[] argv) throws IOException {
		
		/* 
		*make sure that a filename was read into commandline 
		*/
		if(argv.length!=0){
		
			LocusPlotter hp = new LocusPlotter();	

			//the input string of first agument becomes name of output file 									
			hp.setFileName(argv[0]);
			hp.setOutPut(1);
		}

		/* 
		* if not file name was typed in data won't get printed.
		*/
		else return;
	}
	
}
		

	
