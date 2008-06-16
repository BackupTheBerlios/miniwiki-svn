package org.tmjee.miniwiki.client.beans;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: tmjee
 * Date: Jun 1, 2008
 * Time: 11:22:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class PropertySupport implements IsSerializable {

    private transient Object source;
    private transient List<PropertyListener> propertyListener;
    private transient Map<String, List<PropertyListener>> mappedPropertyChangeListener;


    public PropertySupport() {
    }

    public PropertySupport(Object source) {
        if (source == null) {
            throw new NullPointerException("source is null");
        }
        this.source = source;
        propertyListener = new ArrayList<PropertyListener>();
        mappedPropertyChangeListener = new HashMap<String, List<PropertyListener>>();
    }


    public void addPropertyChangeListener(PropertyListener listener) {
        if (listener != null) {
            if (!propertyListener.contains(listener)) {
                propertyListener.add(listener);
            }
        }
    }


    public void addPropertyChangeListener(String propertyName, PropertyListener listener) {
        if (propertyName != null && (propertyName.length() > 0) && listener != null) {
            List propertyListeners = null;
            if (mappedPropertyChangeListener.containsKey(propertyName)) {
                propertyListeners = (List) mappedPropertyChangeListener.get(propertyName);
            }
            else {
                propertyListeners = new ArrayList();
                mappedPropertyChangeListener.put(propertyName, propertyListeners);
            }
            propertyListeners.add(listener);
        }
    }

    public void fireIndexedPropertyChange(String propertyName, int index, boolean oldValue, boolean newValue) {
        fireEvent(
                new IndexedPropertyChangeEvent(
                        source,
                        propertyName,
                        new Boolean(oldValue),
                        new Boolean(newValue),
                        index));
    }

    public void fireIndexedPropertyChange(String propertyName, int index, int oldValue, int newValue) {
        fireEvent(
                new IndexedPropertyChangeEvent(source, propertyName, new Integer(oldValue), new Integer(newValue), index)
        );
    }

    public void fireIndexedPropertyChange(String propertyName, int index, Object oldValue, Object newValue) {
        fireEvent(
                new IndexedPropertyChangeEvent(source, propertyName, oldValue, newValue, index)
        );
    }

    public void fireEvent(EventObject event) {
        // propertyListener
        for (Iterator i = propertyListener.iterator(); i.hasNext(); ) {
            PropertyListener listener = (PropertyListener) i.next();
            listener.propertyChange(event);
        }

        // MappedPropertyChangeListener
        for (Iterator i = mappedPropertyChangeListener.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry e = (Map.Entry) i.next();
            String propertyName = (String) e.getKey();
            if (propertyName.equals(event.getPropertyName())) {
                List listeners = (List) e.getValue();
                for (Iterator ii = listeners.iterator(); ii.hasNext(); ) {
                    PropertyListener listener = (PropertyListener) ii.next();
                    listener.propertyChange(event);
                }
            }
        }
    }

    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
        fireEvent(new PropertyChangeEvent(source, propertyName, new Boolean(oldValue), new Boolean(newValue)));
    }

    public void firePropertyChange(String propertyName, int oldValue, int newValue) {
        fireEvent(new PropertyChangeEvent(source, propertyName, new Integer(oldValue), new Integer(newValue)));
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        fireEvent(new PropertyChangeEvent(source, propertyName, oldValue, newValue));;
    }

    public void firePropertyDeletion(String propertyName, boolean oldValue) {
        fireEvent(new PropertyDeletionEvent(source, propertyName, oldValue));
    }

    public void firePropertyDeletion(String propertyName, int oldValue) {
        fireEvent(new PropertyDeletionEvent(source, propertyName, oldValue));
    }

    public void firePropertyDeletion(String propertyName, Object oldValue) {
        fireEvent(new PropertyDeletionEvent(source, propertyName, oldValue));
    }

    public void firePropertyAddition(String propertyName, boolean newValue) {
        fireEvent(new PropertyAdditionEvent(source, propertyName, newValue));
    }

    public void firePropertyAddition(String propertyName, int newValue) {
        fireEvent(new PropertyAdditionEvent(source, propertyName, newValue));
    }

    public void firePropertyAddition(String propertyName, Object newValue) {
        fireEvent(new PropertyAdditionEvent(source, propertyName, newValue));
    }

    public PropertyListener[] getPropertyChangeListeners() {
        List all = new ArrayList();
        all.addAll(propertyListener);
        for (Iterator i = mappedPropertyChangeListener.values().iterator(); i.hasNext(); ) {
            List listeners = (List) i.next();
            for (Iterator ii = listeners.iterator(); ii.hasNext(); ) {
                PropertyListener listener = (PropertyListener) ii.next();
                if (!all.contains(listener)) {
                     all.add(listener);
                }
            }
        }
        return (PropertyListener[]) all.toArray(new PropertyListener[0]);
    }

    public PropertyListener[] getPropertyChangeListeners(String propertyName) {
        List all = new ArrayList();
        all.addAll(propertyListener);
        List listeners = (List) mappedPropertyChangeListener.get(propertyName);
        if (listeners != null) {
            for (Iterator ii = listeners.iterator(); ii.hasNext(); ) {
                PropertyListener listener = (PropertyListener) ii.next();
                if (!all.contains(listener)) {
                    all.add(listener);
                }
            }
        }
        return (PropertyListener[]) all.toArray(new PropertyListener[0]);
    }
            
    public boolean hasListeners(String propertyName) {
       return ((!propertyListener.isEmpty()) || (mappedPropertyChangeListener.containsKey(propertyName)));
    }

    public void removePropertyChangeListener(PropertyListener listener) {
        propertyListener.remove(listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyListener listener) {
        List listeners = (List) mappedPropertyChangeListener.get(propertyName);
        listeners.remove(listener);
    }


    
}
