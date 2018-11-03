

public class Poda {
	public static int sedesS(int[] c0, int[] c1, int f){
		int coste = 0;
		int anterior  = 0;
		if (c0[0] < c1[0]){
			coste = c0[0];
			anterior=0;
		} else {
			coste = c1[0];
			anterior=1;
		}
		for (int i = 1; i < c0.length;i++){
			if ((anterior == 0)){
				if (c0[i] < c1[i]){
					coste = coste + c0[i];
					anterior = 0;
				} else {
					coste = coste + c1[i] + f;
					anterior = 1;
				}
			} else if (anterior == 1) {
				if (c0[i] < c1[i]){
					coste = coste + c0[i] + f;
					anterior = 0;
				} else {
					coste = coste + c1[i];
					anterior = 1;
				}
			}
		}
		return coste;
	}
	
	public static int sedesCo(int[] c0, int[] c1, int f){
		int coste = 0;
		int anterior  = 0;
		if (c0[0] < c1[0]){
			coste = c0[0];
			anterior = 0;
		} else {
			coste = c1[0];
			anterior = 1;
		}
		for (int i = 1; i < c0.length;i++){
			if ((anterior == 0)){
				if (c0[i] < (c1[i] + f)){
					coste = coste + c0[i];
					anterior = 0;
				} else{
					coste = coste + c1[i] + f;
					anterior = 1;
				}
			} else if (anterior == 1) {
				if ((c0[i]+ f) < c1[i]){
					coste = coste + c0[i] + f;
					anterior = 0;
				} else{
					coste = coste + c1[i];
					anterior = 1;
				}
			}
		}
		return coste;
	}
		public static int sedes (int[] c0, int[] c1, int f) {
			int i = 0;
			int aux;
			int cambio = 0;
			int sol = 2147483647;
			int solAux = 0;
			int[][] cAux = new int[2][c0.length];
			for (int k = 0; k < c0.length; k++){
				cAux[0][k] = c0[k];
			}
			for (int k = 0; k < c0.length; k++){
				cAux[1][k] = c1[k];
			}
			aux = sedesAux(i,sol,solAux , cAux ,f, cambio);
			return aux;
		}
		private static int sedesAux(int i,int sol, int solAux, int[][] cAux, int f,int anterior){
			
			for (int k = 0; k < 2; k++){
				if (i==0){
					if (i == (cAux[k].length)-1){
						solAux=cAux[k][i];
						if(sol>solAux) {
							sol=solAux;
						}
						return sol;
					}
					else{
					anterior =k;
					
					solAux += cAux[k][i];
					i++;
					sol=sedesAux(i, sol, solAux, cAux, f, anterior);
					i--;
					solAux -= cAux[k][i];
					
					}
				}
				else{
					
						if (i ==  (cAux[k].length)){
							if(sol>solAux){
								sol=solAux;
							}
							return sol;
						}
						else{
							if(anterior==k){
								solAux+=cAux[k][i];
								i++;
								sol=sedesAux(i, sol, solAux, cAux, f, k);
								i--;
								solAux-=cAux[k][i];
								
							}
							else{
								//anterior=k;
								solAux+=cAux[k][i]+f;
								i++;
								sol=sedesAux(i, sol, solAux, cAux, f, k);
								i--;
								solAux-=cAux[k][i]+f;
							}
						}
						
				
				}
				
			}
			return sol;
		}
		public static int sedesC(int[] c0, int[] c1){
			int coste = 0;
			for (int i = 0; i < c0.length;i++){
				if (c0[i] < c1[i]){
					coste += c0[i];
				
				} else {
					coste += c1[i];
				
				}
			}
			
			return coste;
		}
		public static int sedesP (int[] c0, int[] c1, int f) {
			int i = 0;
			int cambio = 0;
			int sol =2147483647;
			int solAux = 0;
			int[][] cAux = new int[2][c0.length];
			int cota=sedesC(c0,c1);
			for (int k = 0; k < c0.length; k++){
				cAux[0][k] = c0[k];
			}
			for (int k = 0; k < c0.length; k++){
				cAux[1][k] = c1[k];
			}	
			int aux = sedesAuxP(i,sol,solAux , cAux ,f, cambio,cota);
			return aux;
		}
		private static int sedesAuxP(int i,int sol, int solAux, int[][] cAux, int f,int anterior,int cota){
			for (int k = 0; k < 2; k++){
				if (i==0){
					if (i == (cAux[k].length)-1){
						solAux=cAux[k][i];
						if(sol>solAux) {
							sol=solAux;
						}
						return sol;
					}
					else{
					anterior =k;
					int aux=cota;
					solAux += cAux[k][i];
					i++;
					int c0[]=new int[cAux[k].length];
					int c1[]=new int[cAux[k].length];
					int l=0;
					for(int c=i;c<cAux[k].length;c++,l++) {
						c0[l]=cAux[0][c];
					}
					l=0;
					for(int c=i;c<cAux[k].length;c++,l++) {
						c1[l]=cAux[1][c];
					}
					cota =solAux+sedesC(c0, c1);
					if(sol>=cota) {																						
						sol=sedesAuxP(i, sol, solAux, cAux, f, anterior,cota);
					}
					cota=aux;
					i--;
					solAux -= cAux[k][i];
					}
				}
				else{
					if (i ==  (cAux[k].length)){
						if(sol>solAux){
							sol=solAux;
						}
						return sol;
					}
					else{
						if(anterior==k){
							int aux=cota;		
							solAux+=cAux[k][i];
							i++;
							int c0[]=new int[cAux[k].length];
							int c1[]=new int[cAux[k].length];
							int l=0;
							for(int c=i;c<cAux[k].length;c++,l++) {
								c0[l]=cAux[0][c];
							}
							l=0;
							for(int c=i;c<cAux[k].length;c++,l++) {
								c1[l]=cAux[1][c];
							}		
							cota =solAux+sedesC(c0, c1);
							if(sol>=cota) {		
								sol=sedesAuxP(i, sol, solAux, cAux, f, k,cota);	
							}
							cota=aux;
							i--;
							solAux-=cAux[k][i];	
							}
							else{
								int aux=cota;	
								solAux+=cAux[k][i]+f;
								i++;
								int c0[]=new int[cAux[k].length];
								int c1[]=new int[cAux[k].length];
								int l=0;
								for(int c=i;c<cAux[k].length;c++,l++) {
									c0[l]=cAux[0][c];
								}
								l=0;
								for(int c=i;c<cAux[k].length;c++,l++) {
									c1[l]=cAux[1][c];
								}	
								cota =solAux+sedesC(c0, c1);
								if(sol>=cota) {	
									sol=sedesAuxP(i, sol, solAux, cAux, f, k,cota);
								}
								cota=aux;
								i--;
								solAux-=cAux[k][i]+f;
							}
						}					
					}			
			}
			return sol;
		}
}
