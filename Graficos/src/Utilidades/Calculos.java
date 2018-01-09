package Utilidades;

import java.util.List;

import model.Configuracoes;

public class Calculos {

	public Double correlacaoDePearson(List<Double> a, List<Double> b, Configuracoes config) {
		Double valor1 = 0.0;
		Double valor2 = 0.0;
		Double valor3 = 0.0;
		Double valor4 = 0.0;
		Double valor5 = 0.0;
		Double coe1 = 0.0;
		Double coe2 = 0.0;
		int contador = a.size();
		
		for(int i = 0; i< a.size();i++) {
			Double nota1 = a.get(i);
			Double nota2 = b.get(i);
			valor1 += nota1*nota2;
		}
		
		for (Double valor: a) 
			valor2 += valor;
		
		for (Double valor: b) 
			valor3 += valor;
		
		for(int i = 0; i< a.size();i++) {
			valor4 += Math.pow(a.get(i),2);
			valor5 += Math.pow(b.get(i),2);
		}
		coe1 = (contador * valor1) - (valor2 * valor3);
		coe2 =Math.sqrt(((contador*valor4)-Math.pow(valor2, 2))*((contador*valor5)- Math.pow(valor3, 2))); 
		
		Double coeficiente = coe1 / coe2;
		System.out.println("Pearson: "+coeficiente);
		return(coeficiente>=config.getLimiar() || coeficiente<=-config.getLimiar())?1.0:0.0;
	}
	
/*
 * 
 * 
 * 
 * 
 * 
 * public function calcula(){            
            //Calcula a soma dos produtos entre as notas
            for ($i = 0 ; $i < sizeof($this->list[0]); $i++){
                            $nota1 = $this->list[0][$i];
                            $nota2 = $this->list[1][$i];

                            @$valor1 += $nota1*$nota2;
                    };
            //Soma as notas de cada usuário entre si	
            $valor2 = array_sum($this->list[0]);
            $valor3 = array_sum($this->list[1]);

            //Calcula a soma dos quadrados das notas
            for ($p = 0; $p < sizeof($this->list[0]); $p++){
                            @$valor4 += pow($this->list[0][$p],2);
                            @$valor5 += pow($this->list[1][$p],2);
                    };

            //Calculo do COEFICIENTE DE PEARSON
            $n = count($this->list[0]);

            $coe1 = ($n * $valor1 - $valor2 * $valor3);
            $coe2 = sqrt(($n * $valor4 - pow($valor2,2))*($n * $valor5 - pow($valor3,2)));

            @$coeficiente = $coe1 / $coe2;

            //Retorna o resultado do cálculo
            return $coeficiente;
        }
 */
}
