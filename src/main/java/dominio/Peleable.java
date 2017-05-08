package dominio;

public interface Peleable {
	public int serAtacado(int daño);
	public int getSalud();
	public void despuesDeTurno();
	public int atacar(Peleable atacado);
	public int otorgarExp();
	public int getAtaque();
	public void setAtaque(int ataque);
	public boolean estaVivo();
	public String getNombre();
	public boolean esAfectadoPorHechicero();
	public boolean esAfectadoPorGuerrero();
}
