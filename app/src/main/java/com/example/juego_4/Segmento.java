package com.example.juego_4;


public class Segmento {
	double o,f;
	Segmento(double x0,double x1){
		if(x0<x1){
			this.o=x0;
			this.f=x1;
		}
		else{
			this.o=x1;
			this.f=x0;
		}
	}
	public boolean pertenece(double x){
		return (o<=x && x<=f);
	}
	public boolean pertenece(Segmento s){
		return (pertenece(s.getOrigen()) && pertenece(s.getFin()));
	}
	public boolean colision(Segmento s){

		return (this.pertenece(s) || s.pertenece(this));
	}
	private double getFin() {

		return f;
	}
	private double getOrigen() {
		return o;
	}
}
