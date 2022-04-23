package cat.copernic.rgarrido.m08uf2multimedia;

import android.content.Context;
import android.graphics.*;
import android.hardware.*;
import android.renderscript.Element;
import android.text.style.BackgroundColorSpan;
import android.view.*;
import android.webkit.WebViewFragment;

public class Burbuja extends View implements SensorEventListener {
    Paint pincel = new Paint();

    int alto;
    int ancho;
    int tamanio = 100;
    int borde = 12;
    float ejeX = 550, ejeY=1000, ejeZ1=0, ejeZ=0;
    String X,Y,Z;

    public Burbuja (Context interfaz){
        super (interfaz);
        SensorManager smAdministrador = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor snsRotacion = smAdministrador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        smAdministrador.registerListener(this, snsRotacion, SensorManager.SENSOR_DELAY_GAME);
        Display pantalla = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        ancho = pantalla.getWidth();
        alto = pantalla.getHeight();
    }

    @Override
    public void onSensorChanged(SensorEvent cambio){

        ejeX += cambio.values[0];
        //ejeX -= cambio.values[0];
        X = Float.toString(ejeX);
        if (ejeX < (tamanio+borde)){
            ejeX = (tamanio+borde);
        }
        else if (ejeX > (ancho-(tamanio+borde))){
            ejeX = ancho-(tamanio+borde);
        }

        ejeY -= cambio.values[1];
        //ejeY += cambio.values[1];
        Y = Float.toString(ejeY);
        if (ejeY < (tamanio+borde)){
            ejeY = (tamanio+borde);
        }
        else if (ejeY > (alto-tamanio-170)){
            ejeY = alto-tamanio-170;
        }

        ejeZ = cambio.values[2];
        Z = String.format("%.2f", ejeZ);
        invalidate();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onDraw(Canvas lienzo){
        pincel.setColor(Color.BLUE);
        lienzo.drawCircle(ejeX, ejeY,ejeZ + tamanio, pincel);
        pincel.setColor(Color.WHITE);
        pincel.setTextSize(25);
        lienzo.drawText(X + " m/s^2", ejeX-95, ejeY-15, pincel);
        lienzo.drawText(Y + " m/s^2", ejeX-95, ejeY+30, pincel);

    }
}