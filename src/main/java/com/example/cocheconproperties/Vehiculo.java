package com.example.cocheconproperties;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;

public class Vehiculo extends ImageView {

    private double velocidadX;
    private double velocidadMaxima = 5.0;
    private boolean enMovimiento = false;
    private Random random = new Random();

    public Vehiculo(String rutaImagen,double tamañoPantalla) {
        super(rutaImagen);
        setVelocidadAleatoria();
        this.setFitWidth(300);
        this.setFitHeight(200);
    }

    void setVelocidadAleatoria() {
        velocidadX = Math.random() * velocidadMaxima;
    }

    public void mover() {
        if (!enMovimiento) {
            enMovimiento = true;
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    double nextX = getLayoutX() + velocidadX;
                    // Check if the next X position is within the screen bounds
                    if (nextX <= getParent().getBoundsInLocal().getMaxX() - getImage().getWidth()) {
                        setLayoutX(nextX);
                    } else {
                        // If the next X position exceeds the screen bounds, stop the vehicle
                        detener();
                    }
                    // Randomly accelerate the vehicle
                    if (random.nextInt(100) < 5) { // Adjust probability as needed (5% chance here)
                        acelerar();
                    }
                }
            };
            timer.start();
        }
    }

    public void acelerar() {
        velocidadX += random.nextDouble() * 2; // Randomly increase velocity (0 to 2)
        if (velocidadX > velocidadMaxima) {
            velocidadX = velocidadMaxima;
        }
    }

    public void frenar() {
        velocidadX -= 1.0;
        if (velocidadX < 0) {
            velocidadX = 0;
        }
    }


    // Método para detener el vehículo
    public void detener() {
        velocidadX = 0;
        enMovimiento = false;
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
}
