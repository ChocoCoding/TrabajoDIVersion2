package com.example.cocheconproperties;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class Vehiculo extends ImageView {

    private PropertyChangeSupport changeSupport;

    private double velocidadX;
    private double velocidadMaxima = 10.0;
    private boolean enMovimiento = false;
    private Random random = new Random();
    private AnimationTimer timer;
    private String nombre;
    private boolean ganado;
    private String rutaImagen;
    private Integer precio;

    public Vehiculo(String rutaImagen, String nombre) {
        super(rutaImagen);
        this.rutaImagen = rutaImagen;
        changeSupport = new PropertyChangeSupport(this);
        this.nombre = nombre;
        setVelocidadAleatoria();
        this.setFitWidth(150);
        this.setFitHeight(75);
        this.ganado = false;
    }

    void setVelocidadAleatoria() {
        velocidadX = Math.random() * velocidadMaxima;
    }

    public void mover() {
        this.ganado = false;
        if (!enMovimiento) {
            enMovimiento = true;
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    double nextX = getLayoutX() + velocidadX;
                    double paneWidth = ((Pane)getParent()).getWidth();
                    // Comprobamos si la siguiente posicion esta dentro del panel
                    if (nextX + getImage().getWidth() >= paneWidth) {
                        setLayoutX(paneWidth - getImage().getWidth());
                        System.out.println(nextX);
                        setGanado(true);
                    } else {
                        //Ajustamos para que el vehiculo no salga de la pantalla
                        setLayoutX(nextX);
                    }
                    // Aceleramos el coche
                        if (random.nextInt(100) < 1) { // Ajustamos la probabilidad de acelerar
                            acelerar();
                        }
                }
            };
            timer.start();
        }
    }

    public void acelerar() {
        velocidadX += random.nextDouble() * 3; // Incrementamos la velocidad
        System.out.println(velocidadX);
        if (velocidadX > velocidadMaxima) {
            velocidadX = velocidadMaxima;
        }
    }

    public void detener() {
        velocidadX = 0;
        enMovimiento = false;
        if (timer != null) {
            timer.stop();
        }
    }

    public double getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(double velocidadX) {
        this.velocidadX = velocidadX;
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public boolean isEnMovimiento() {
        return enMovimiento;
    }

    public void setEnMovimiento(boolean enMovimiento) {
        this.enMovimiento = enMovimiento;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public AnimationTimer getTimer() {
        return timer;
    }

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PropertyChangeSupport getChangeSupport() {
        return changeSupport;
    }

    public void setChangeSupport(PropertyChangeSupport changeSupport) {
        this.changeSupport = changeSupport;
    }

    public boolean isGanado() {
        return ganado;
    }

    public void setGanado(boolean ganado) {
        boolean oldGanado = this.ganado;
        this.ganado = ganado;

        changeSupport.firePropertyChange("ganado", oldGanado, ganado);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
}
