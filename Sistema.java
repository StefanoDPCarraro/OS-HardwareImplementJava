// PUCRS - Escola Politécnica - Sistemas Operacionais
// Prof. Fernando Dotti
// Código fornecido como parte da solução do projeto de Sistemas Operacionais
//
// Estrutura deste código:
//    Todo código está dentro da classe *Sistema*
//    Dentro de Sistema, encontra-se acima a definição de HW:
//           Memory,  Word, 
//           CPU tem Opcodes (codigos de operacoes suportadas na cpu),
//               e Interrupcoes possíveis, define o que executa para cada instrucao
//           VM -  a máquina virtual é uma instanciação de CPU e Memória
//    Depois as definições de SW:
//           no momento são esqueletos (so estrutura) para
//					InterruptHandling    e
//					SysCallHandling 
//    A seguir temos utilitários para usar o sistema
//           carga, início de execução e dump de memória
//    Por último os programas existentes, que podem ser copiados em memória.
//           Isto representa programas armazenados.
//    Veja o main.  Ele instancia o Sistema com os elementos mencionados acima.
//           em seguida solicita a execução de algum programa com  loadAndExec

import java.util.*;

import Hardware.HW;
import Programas.Programs;
import Software.PCB;
import Software.SO;
import java.util.Scanner;

public class Sistema {



	// -------------------------------------------------------------------------------------------------------
	// ------------------- S I S T E M A
	// --------------------------------------------------------------------

	public HW hw;
	public SO so;
	public Programs progs;

	public Sistema(int tamMem, int tamFrame) {
		hw = new HW(tamMem, tamFrame);   // memoria do HW tem tamMem palavras e tamPag para frames
		so = new SO(hw, tamFrame);
		hw.cpu.setUtilities(so.utils); // permite cpu fazer dump de memoria ao avancar
		progs = new Programs();
	}

	public void run() {


		so.utils.loadAndExec(progs.retrieveProgram("fatorialV2"));

		// so.utils.loadAndExec(progs.retrieveProgram("fatorial"));
		// fibonacci10,
		// fibonacci10v2,
		// progMinimo,
		// fatorialWRITE, // saida
		// fibonacciREAD, // entrada
		// PB
		// PC, // bubble sort
	}
	public static void main(String args[]) {
		Sistema s = new Sistema(1024, 16);
		s.run();
	}
	public void handleShowOptions(int option){
		switch (option) {
			case 0:
				// TODO: Implement and test
				handleNew();
				break;

			case 1:
				// TODO: Handle RM

			case 2:
				// TODO: Test
				handlePs();
				break;
		
			case 3:
				// TODO: Finish
				handleDump();
				break;

			case 4:
				// TODO: Implement
				handleDumpM();
				break;

			case 5:
				//TODO: Implement
				exec();
				break;
			
			case 6:
				traceOn();
				break;

			case 7:
				traceOff();
				break;

			default:
				break;
		}
	}
	public int optionsConsole(){
		Scanner in = new Scanner(System.in);
		System.out.println("Opcoes:");
		System.out.println("[0] new");
		System.out.println("[1] rm");
		System.out.println("[2] ps");
		System.out.println("[3] dump");
		System.out.println("[4] dumpM");
		System.out.println("[5] exec");
		System.out.println("[6] traceOn");
		System.out.println("[7] traceOff");
		System.out.println("[8] exit");
		System.out.println("");
		int opt = in.nextInt();
		in.close();
		return opt;
	}

	public int handleNew(){
		Scanner in = new Scanner(System.in);
		System.out.println("Selecione o programa:");
		System.out.println("[0] fatorial");
		System.out.println("[1] fatorialV2");
		System.out.println("[2] progMinimo");
		System.out.println("[3] fibonacci10");
		System.out.println("[4] fibonacci10v2");
		System.out.println("[5] fibonacciREAD");
		System.out.println("[6] PB");
		System.out.println("[7] PC");
		int opt = in.nextInt();
		in.close();
		return opt;
	}

	public void handlePs(){
		so.gp.printAllProcesses();
		return;
	}

	public void handleDump(){
		Scanner in = new Scanner(System.in);
		int id = in.nextInt();
		PCB dumped = so.gp.processes.get(id);
		System.out.println(dumped);
		int count = 0;
		for(int pags: so.gp.processes.get(id).tabelaPags){
			for(int i = 0; i < so.gm.tamPag; i++){
				int index = (pags * so.gm.tamPag) + i;
				System.out.println(count + " - " + so.gm.memoria.pos[index]);
				count++;
			}
			// TODO: Print pags
		}
		in.close();
		return;
	}

	public void handleDumpM(){
		Scanner in = new Scanner(System.in);
		int inicio = in.nextInt();
		int fim = in.nextInt();
		in.close();
		return;
	}

	public void exec(){
		Scanner in = new Scanner(System.in);
		int id = in.nextInt();
		in.close();
		return;
	}

	public void traceOn(){
		hw.cpu.debug = true;
		return;
	}

	public void traceOff(){
		hw.cpu.debug = false;
		return;
	}
}