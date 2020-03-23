package com.codereddie.lojinha.services.validation.br;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ValidateBRData {

	public static boolean conferirCPF(String cpf) {
		try {
			boolean validez = true;

			long d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11;
			String cpfFinal = "";
			long cpfTeste = 0;
			// separar
			if (cpf.length() == 14) {
				String cpf2 = cpf.substring(0, 3) + (cpf.substring(4, 7)) + (cpf.substring(8, 11))
						+ (cpf.substring(12, 14));
				cpf = cpf2;
				cpfTeste = Long.parseLong(cpf2);
			} else if (cpf.length() == 11) {
				cpfTeste = Long.parseLong(cpf);
			} else {
				validez = false;
			}

			if (validez == true) {
				d9 = (cpfTeste % 1000) / 100;
				d8 = (cpfTeste % 10000) / 1000;
				d7 = (cpfTeste % 100000) / 10000;
				d6 = (cpfTeste % 1000000) / 100000;
				d5 = (cpfTeste % 10000000) / 1000000;
				d4 = (cpfTeste % 100000000) / 10000000;
				d3 = (cpfTeste % 1000000000) / 100000000;
				d2 = (cpfTeste / 1000000000) % 10;
				d1 = (cpfTeste / 1000000000) / 10;
				// calcular
				long primeiroDigitoV = 11 - (((d9 * 2) + (d8 * 3) + (d7 * 4) + (d6 * 5) + (d5 * 6) + (d4 * 7) + (d3 * 8)
						+ (d2 * 9) + (d1 * 10)) % 11);

				if (primeiroDigitoV > 9) {
					primeiroDigitoV = 0;
					long segundoDigitoV = 11 - (((d1 * 11) + (d2 * 10) + (d3 * 9) + (d4 * 8) + (d5 * 7) + (d6 * 6)
							+ (d7 * 5) + (d8 * 4) + (d9 * 3) + (primeiroDigitoV * 2)) % 11);

					cpfFinal = Long.toString(d1) + Long.toString(d2) + Long.toString(d3) + Long.toString(d4)
							+ Long.toString(d5) + Long.toString(d6) + Long.toString(d7) + Long.toString(d8)
							+ Long.toString(d9) + Long.toString(primeiroDigitoV) + Long.toString(segundoDigitoV);

					if (cpfFinal.trim().equalsIgnoreCase(cpf)) {
						validez = true;
					} else {
						validez = false;
					}

				} else {
					long segundoDigitoV = 11 - (((d1 * 11) + (d2 * 10) + (d3 * 9) + (d4 * 8) + (d5 * 7) + (d6 * 6)
							+ (d7 * 5) + (d8 * 4) + (d9 * 3) + (primeiroDigitoV * 2)) % 11);

					cpfFinal = Long.toString(d1) + Long.toString(d2) + Long.toString(d3) + Long.toString(d4)
							+ Long.toString(d5) + Long.toString(d6) + Long.toString(d7) + Long.toString(d8)
							+ Long.toString(d9) + Long.toString(primeiroDigitoV) + Long.toString(segundoDigitoV);

					if (cpfFinal.trim().equalsIgnoreCase(cpf)) {
						validez = true;
					} else {
						validez = false;
					}
				}
			} else {
				validez = false;
			}

			return validez;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean conferirCNPJ(String cnpj) {
		try {
			char separar[] = cnpj.toCharArray();
			ArrayList<Integer> CNPJ = new ArrayList<Integer>();
			for (int i = 0; i < separar.length; i++) {
				if (separar[i] != '.' && separar[i] != '-' && separar[i] != '/') {
					if (separar[i] == '0') {
						CNPJ.add(0);
					} else if (separar[i] == '1') {
						CNPJ.add(1);
					} else if (separar[i] == '2') {
						CNPJ.add(2);
					} else if (separar[i] == '3') {
						CNPJ.add(3);
					} else if (separar[i] == '4') {
						CNPJ.add(4);
					} else if (separar[i] == '5') {
						CNPJ.add(5);
					} else if (separar[i] == '6') {
						CNPJ.add(6);
					} else if (separar[i] == '7') {
						CNPJ.add(7);
					} else if (separar[i] == '8') {
						CNPJ.add(8);
					} else if (separar[i] == '9') {
						CNPJ.add(9);
					}
				}
			}
			int d11 = ((CNPJ.get(0) * 5) + (CNPJ.get(1) * 4) + (CNPJ.get(2) * 3) + (CNPJ.get(3) * 2) + (CNPJ.get(4) * 9)
					+ (CNPJ.get(5) * 8) + (CNPJ.get(6) * 7) + (CNPJ.get(7) * 6) + (CNPJ.get(8) * 5) + (CNPJ.get(9) * 4)
					+ (CNPJ.get(10) * 3) + (CNPJ.get(11) * 2)) % 11;
			if (d11 < 2) {
				d11 = 0;
			} else {
				d11 = 11 - d11;
			}

			int d12 = ((CNPJ.get(0) * 6) + (CNPJ.get(1) * 5) + (CNPJ.get(2) * 4) + (CNPJ.get(3) * 3) + (CNPJ.get(4) * 2)
					+ (CNPJ.get(5) * 9) + (CNPJ.get(6) * 8) + (CNPJ.get(7) * 7) + (CNPJ.get(8) * 6) + (CNPJ.get(9) * 5)
					+ (CNPJ.get(10) * 4) + (CNPJ.get(11) * 3) + (d11 * 2)) % 11;
			if (d12 < 2) {
				d12 = 0;
			} else {
				d12 = 11 - d12;
			}

			if (CNPJ.get(12) == d11 && CNPJ.get(13) == d12) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

	}

	public static boolean ConferirIE(String IE, Date dataIE) {
		try {
		ArrayList<Integer> IEv = new ArrayList<Integer>();
		int digito11;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataIE);
		
		if(cal.YEAR <= 2003) {
		if(IE.length()==11) {
			char iteste[] = IE.toCharArray();
			
			for (int i = 0; i < 11; i++) {
				
				IEv.add(Integer.parseInt(Character.toString(iteste[i])));
			}
		} else if(IE.length()== 14) {
			char iteste[] = IE.toCharArray();
			for (int i = 0; i < 14; i++) {
				if(iteste[i]!='.' && iteste[i]!='-') {
					IEv.add(Integer.parseInt(Character.toString(iteste[i])));
				}
			}
		} else {
		  return false;
		}
		
		int soma1 = ((IEv.get(0)*9) + (IEv.get(1)*8) + (IEv.get(4)*7)+
				(IEv.get(5)*6) + (IEv.get(6)*5) + (IEv.get(7)*4)+
				(IEv.get(8)*3) + (IEv.get(9)*2)) %11;
		if(soma1>=2) {
			digito11 = 11-soma1;
		} else {
			digito11=0;
		}
		if(IEv.get(10)==digito11) {
			return true;
		} else {
			return false;
		}
		
		} else {
			if(IE.length()==9) {
				char iteste[] = IE.toCharArray();
				for (int i = 0; i < 9; i++) {
					IEv.add(Integer.parseInt(Character.toString(iteste[i])));
				}
			} else if(IE.length()==10) {
				char iteste[] = IE.toCharArray();
				for (int i = 0; i < 9; i++) {
					if(iteste[i]!='-') {
					IEv.add(Integer.parseInt(Character.toString(iteste[i])));
				}
				}
			}
			
			int soma1 = ((IEv.get(0)*9) + (IEv.get(1)*8) + (IEv.get(2)*7)
				+ (IEv.get(3)*6) + (IEv.get(4)*5) + (IEv.get(5)*4) +
				(IEv.get(6)*3) + (IEv.get(7)*2))%11;
			
			if(soma1<2) {
				digito11=0;
			} else {
				digito11= 11-soma1;
			}
					if(digito11 ==  IEv.get(8)) {
						return true;
					}else {
						return false;
					}
			
		}
		}catch(Exception e) {
			return false;
		}
	}

}