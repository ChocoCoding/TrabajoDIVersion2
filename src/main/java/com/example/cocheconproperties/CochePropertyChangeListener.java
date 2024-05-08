package com.example.cocheconproperties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class CochePropertyChangeListener implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName ( );
        Object oldValue = evt.getOldValue ();
        Object newValue = evt.getNewValue ();


        System.out.println(propertyName);
        System.out.println(oldValue);
        System.out.println(newValue);
    }


}
