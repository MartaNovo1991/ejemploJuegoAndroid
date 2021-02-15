package com.example.juego_4;

public class Velocidad {
	public static final int DIRECCION_DERECHA = 1;
	public static final int DIRECCION_IZQUIERDA  = -1;
	public static final int DIRECCION_ARRIBA    = -1;
	public static final int DIRECCION_ABAJO  = 1;
	private float vx = 1;   // velocidad en el eje x
	private float vy = 1;   // velocidad en el eje y
	private int direccionX = DIRECCION_DERECHA;
	private int direccionY = DIRECCION_ABAJO; 
	public Velocidad() {
	   this.vx = 2;
	   this.vy = 2;
	}
	public Velocidad(float xv, float yv) {
	   this.vx = xv;
	   this.vy = yv;
	}
	public float getVx() {
	    return vx;
	}
	public void setVx(float xv) {
	    this.vx = xv;
	}
	public float getVy() {
	   return vy;
	}
	public void setVy(float yv) {
	   this.vy = yv;
	}
	public int getDireccionX() {
	   return direccionX;
	}
	public void setDireccionX(int direccionX) {
	   this.direccionX = direccionX;
	}
	public int getDireccionY() {
	   return direccionY;
	}
	public void setDireccionY(int direccionY) {
	   this.direccionY = direccionY;
	}
	// cambia la direcci�n en el eje X
	public void cambiaDireccionX() {
	   direccionX = direccionX * -1;
	}
	// cambia la direcci�n en el eje Y
	public void cambiaDireccionY() {
	   direccionY = direccionY * -1;
	}
}
