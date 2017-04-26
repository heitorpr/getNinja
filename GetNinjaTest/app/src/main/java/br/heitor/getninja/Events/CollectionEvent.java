package br.heitor.getninja.Events;

public class CollectionEvent {
    Class type;

    public CollectionEvent(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }
}
