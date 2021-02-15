package com.example.juego_4;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Sprite {
	private Bitmap bitmap; // la imagen del sprite
	private int x;
	private int y;   // la coordenada y
	private int soldadoderecha;
	private boolean tocado; // el sprite ha sido tocado
	private Velocidad v; // velocidad del sprite
	public Sprite(Bitmap bitmap, int x, int y) {
	 this.bitmap = bitmap;
	 this.x = x;
	 this.y = y;
	 this.v = new Velocidad();
	}
	public Bitmap getBitmap() {
	 return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
	 this.bitmap = bitmap;
	}
	public int getX() {
	 return x;
	}
	public void setX(int x) {
	 this.x = x;
	}
	public int getY() {
	 return y;
	}
	public void setY(int y) {
	 this.y = y;
	}
	public boolean estaTocado() {
	  return tocado;
	}
	public void setTocado(boolean tocado) {
	 this.tocado = tocado;
	}
	public void getVelocidad(Velocidad v){
		this.v=v;
	}
	public Velocidad getVelocidad(){
		return this.v;
	}
	public void mueve(){
        x += (v.getVx() * v.getDireccionX());
        y += (v.getVy() * v.getDireccionY());	
	}
	public void actualiza() {
		if(!tocado) {
			mueve();
		}	
	}
	public void draw(Canvas lienzo) {
//	 lienzo.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
		lienzo.drawBitmap(bitmap, x , y, null);
	}
	public void handleActionDown(int eventX, int eventY) {
//	 if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
//	  if (eventY >= (y - bitmap.getHeight() / 2) && (eventY <= (y + bitmap.getHeight() / 2))) {
	   // Sprite tocado
		if (eventX >= x && (eventX <= (x + bitmap.getWidth()))) {
	  if (eventY >= y  && (eventY <= (y + bitmap.getHeight()))) {
	   setTocado(true);
	  } else {
	   setTocado(false);
	  }
	 } else {
	  setTocado(false);
	 }
    }
	public boolean colision(Sprite s){
		Segmento stx=new Segmento(this.getX(),this.getX()+this.getWidth());
		Segmento ssx=new Segmento(s.getX(),s.getX()+s.getWidth());
		if(stx.colision(ssx)){
			Segmento sty=new Segmento(this.getY(),this.getY()+this.getHeight());
			Segmento ssy=new Segmento(s.getY(),s.getY()+s.getHeight());
			return sty.colision(ssy);
		}
		return false;
	}
	public int getHeight() {
		return bitmap.getHeight();
	}
	public int getWidth() {
		return bitmap.getWidth();
	}
}
