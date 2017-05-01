package br.heitor.getninja.Events;

public class ItemUpdateEvent {
    private String link;
    private Object object;

    public ItemUpdateEvent(String link, Object object) {
        this.link = link;
        this.object = object;
    }

    public String getLink() {
        return link;
    }

    public Object getObject() {
        return object;
    }
}
