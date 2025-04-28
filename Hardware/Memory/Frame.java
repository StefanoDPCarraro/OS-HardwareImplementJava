package Hardware.Memory;

public class Frame{
	public int indice;
	public int tamFrame;
	public int inicio;
	public int fim;

	public Frame(int tamFrame, int indice){
			this.indice = indice;
			this.tamFrame = tamFrame;
			inicio = indice * tamFrame;
			fim = (indice + 1) * tamFrame - 1;
		}
}