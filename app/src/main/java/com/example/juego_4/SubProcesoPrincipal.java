package com.example.juego_4;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class SubProcesoPrincipal extends Thread {
	 // variable q indica que acaba el juego
	 private boolean finJuego;
	 private SurfaceHolder surfaceHolder;
	 private VistaPrincipalJuego vistaJuego;
	 
	 	 
	 public SubProcesoPrincipal(SurfaceHolder surfaceHolder, VistaPrincipalJuego vista) {
		 super();
	     this.surfaceHolder = surfaceHolder;
	     this.vistaJuego = vista;
	 }

	 public void setFinJuego(boolean fj) {

	 	this.finJuego = fj;
	 }
	 
	 @SuppressLint("WrongCall")
	@Override
	 public void run() {
		 Canvas lienzo=null;
	 // Este es nuestro bucle principal
	  while (!finJuego) {
		  try {
			  // obtenemos el lienzo de nuestra vista
		     lienzo = this.surfaceHolder.lockCanvas();
		     synchronized (surfaceHolder) {
		      // Actualizamos el juego
	          this.vistaJuego.actualiza();
		      // dibujamos en lienzo en la vista
		      this.vistaJuego.onDraw(lienzo);
		     }
		  } finally {
		     // en caso de alguna excepci�n no dejamos 
		     // al lienzo en un estado inconsistente
		     if (lienzo != null) {
		      surfaceHolder.unlockCanvasAndPost(lienzo);
		     }
		  } // end finally
	  }
	 }
	
}
