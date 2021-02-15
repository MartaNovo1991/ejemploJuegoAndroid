package com.example.juego_4;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//Nuestra vista principal de juego, hereda de Surfaceview e implementa la interfaz SurfaceHolder.Callback

public class VistaPrincipalJuego extends SurfaceView implements SurfaceHolder.Callback {
	private SubProcesoPrincipal buclePrincipal;
	private Sprite soldado, enemigo1,enemigo2,ladrillo;
	private int anchototalladrillo;
	private static final String TAG = VistaPrincipalJuego.class.getSimpleName();
	
	public VistaPrincipalJuego(Context context) {
	  super(context);
	  // Ponemos como objeto de llamada a nuestra Vista (this) para que le envie todos los eventos
	  getHolder().addCallback(this);
	  // Creamos el sprite para el soldado
	  soldado=new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.ftr1_fr1), 50, 50);
	  // Creamos el sprite del ejercicio 4.6
	  ladrillo=new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.bloque),0, getHeight()/2);

	  // Creamos los sprite del ejercicio 4.3
	  //enemigo1=new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.amg4_fr2), 50, 70);
	  //enemigo2=new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.avt1_fr1), 100, 40);
	  // Creamos una instancia del subproceso que contiene el bucle principal
	  buclePrincipal=new SubProcesoPrincipal(getHolder(),this);
	  // Activamos el envio de eventos para esta vista
	  setFocusable(true);
	 }
	 
	 public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		 }
	 
	 public void surfaceCreated(SurfaceHolder holder) {
		 this.buclePrincipal.setFinJuego(false);
		 this.buclePrincipal.start();
	 }
	 
	 public void surfaceDestroyed(SurfaceHolder holder) {
		 boolean repite = true;
		 	  while (repite) {
		 	   try {
		 	    buclePrincipal.join();
		 	    repite = false;
		 	   } catch (InterruptedException e) {
		     // Si hay algun problema lo intentamos de nuevo
		 	   }
		 	  }
	}

	 //Ejercicio 4.2
	 public void calculaAngulo(MotionEvent e){
		 //Implementamos las fomulas indicadas en el ejercicio 4.1
		 double x=e.getX();
		 double y=e.getY();
		 double dx=x-100;
		 double dy=y-100;
		 double r=Math.sqrt(dx*dx+dy*dy);
		 double angulo;
		 if(r<=50.0){
			 if(dx>0){
				 angulo=Math.atan(dy/dx);
			 }
			 else{
				 angulo=Math.PI/2.0;
			 }
			 Log.d(TAG,"Angulo ="+angulo);
		 }
	 }
	public boolean onTouchEvent(MotionEvent event) {
		 if (event.getAction() == MotionEvent.ACTION_DOWN) {
			 //Llamada para el ejercicio 4.2
			 calculaAngulo(event);
 		  // delegamos el evento al Sprite
		  soldado.handleActionDown((int)event.getX(), (int)event.getY());
		  // si pulsamos en la parate inferior de pantalla salir
		  if (event.getY() > getHeight() - 50) {
			  buclePrincipal.setFinJuego(true);
		   ((Activity)getContext()).finish();
		  } else {
		   Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
		  }
		 }
		 if (event.getAction() == MotionEvent.ACTION_MOVE) {
		   // el dedo se mueve
		   if (soldado.estaTocado()) {
		     // el soldado ha sido seleccionado
			 soldado.setX((int)event.getX());
			 soldado.setY((int)event.getY());
		   }
		 } 
		 if (event.getAction() == MotionEvent.ACTION_UP) {
		   // el dedo se levanta
		   if (soldado.estaTocado()) {
			 soldado.setTocado(false);
		   }
		 }
		 return true;
	}
	public void actualiza(){
		// Comprobamos colisi�n con la derecha del muro si la direcci�n es la derecha
		/*if (soldado.getVelocidad().getDireccionX() == Velocidad.DIRECCION_DERECHA
				&& soldado.getY() < (ladrillo.getY()+ (ladrillo.getHeight()))
				&& soldado.getX() + (soldado.getWidth()) >= (ladrillo.getX())
				&& soldado.getX() + soldado.getWidth() <= (ladrillo.getX() + (ladrillo.getWidth()/2)))


		{
			soldado.getVelocidad().cambiaDireccionX();
		}
			//Comprobamos colicsión con la izquierda del muro si la dirección es izquierda
			if (soldado.getVelocidad().getDireccionX() == Velocidad.DIRECCION_IZQUIERDA
					&& soldado.getY() < (ladrillo.getY()+ (ladrillo.getHeight()))
					&& soldado.getX()  <= (ladrillo.getX()) + ladrillo.getWidth()
					&& soldado.getX()  >= (ladrillo.getX() + (ladrillo.getWidth()/2)))

		{
				soldado.getVelocidad().cambiaDireccionX();
			}*/
		// Comprobamos colisi�n con la derecha si la direcci�n es derecha
		if (soldado.getVelocidad().getDireccionX() == Velocidad.DIRECCION_DERECHA
		     && soldado.getX() + (soldado.getWidth()) >= getWidth()) {
			soldado.getVelocidad().cambiaDireccionX();
		}
		// Comprobamos colisi�n con la izuierda si la direcci�n es la izquierda
		if (soldado.getVelocidad().getDireccionX() == Velocidad.DIRECCION_IZQUIERDA
		        && soldado.getX() <= 0) {
			soldado.getVelocidad().cambiaDireccionX();
		}
		// Comprobamos colisi�n con el bajo si la direcci�n es abajo
		if (soldado.getVelocidad().getDireccionY() == Velocidad.DIRECCION_ABAJO
		        && soldado.getY() + (soldado.getHeight()) >= getHeight()) {
			soldado.getVelocidad().cambiaDireccionY();
		}
		// Comprobamos colisi�n el tope si la direcci�n es arriba
		if (soldado.getVelocidad().getDireccionY() == Velocidad.DIRECCION_ARRIBA
		        && soldado.getY()  <= 0) {
			soldado.getVelocidad().cambiaDireccionY();
		}
		
		// Comprobamos colisi�n con la izquierda del muro si la direcci�n es la derecha
				if (soldado.getVelocidad().getDireccionX() == Velocidad.DIRECCION_DERECHA
						&& soldado.getX() + (soldado.getWidth())  == getWidth() - (4 * ladrillo.getWidth())
						&& soldado.getY() + (soldado.getHeight()) >= (ladrillo.getY())
				        && soldado.getY()  <= (ladrillo.getY() + (ladrillo.getHeight())))  {
			soldado.getVelocidad().cambiaDireccionX();
				}
		// Comprobamos colisi�n con la parte de arriba del ladrillo si la direcci�n es abajo
				if (soldado.getVelocidad().getDireccionY() == Velocidad.DIRECCION_ABAJO
				        && soldado.getY() + (soldado.getHeight()) >= (ladrillo.getY())
				        && soldado.getY() + (soldado.getHeight()/2) <= ladrillo.getY()
				        && soldado.getX() + (soldado.getWidth()) > getWidth() -(4 * ladrillo.getWidth())) {
			soldado.getVelocidad().cambiaDireccionY();
				}
		// Comprobamos colisi�n la parte de abajo del ladrillo si la direcci�n es arriba
				if (soldado.getVelocidad().getDireccionY() == Velocidad.DIRECCION_ARRIBA
				    && soldado.getY() <= (ladrillo.getY() + (ladrillo.getHeight()))
					&& soldado.getY() - (soldado.getHeight()/2) >= ladrillo.getY()
				&& soldado.getX() + (soldado.getWidth()) > getWidth() - (4 * ladrillo.getWidth())){
			soldado.getVelocidad().cambiaDireccionY();
			
				}
		soldado.actualiza();
	}
	protected void onDraw(Canvas lienzo) {
		//lienzo.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher),50,50,null);
		 // ponemos el fondo en blanco
		lienzo.drawColor(Color.WHITE);
		//ladrillo.setX(getWidth()/2-ladrillo.getWidth()/2);
		ladrillo.setY(getHeight()/2-ladrillo.getHeight()/2);
		for (int i=0;i<4;i++){
			//ladrillo.setY(i*ladrillo.getHeight());
			ladrillo.setX(getWidth()-ladrillo.getWidth()- i*ladrillo.getWidth());
			ladrillo.draw(lienzo);
		}
		//soldado.draw(lienzo);
		 // Lo primero es pintar los ladrillos

		 // pintamos el resto de personajes
		 soldado.draw(lienzo);
		 //enemigo1.draw(lienzo);
		 //enemigo2.draw(lienzo);
	}

	
}
