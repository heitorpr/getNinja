package br.heitor.getninja.Events;

import br.heitor.getninja.views.adapter.CustomAdapter;

public class ItemClickEvent {
    private int position;
    private CustomAdapter.ViewType viewType;

    public ItemClickEvent(int position, CustomAdapter.ViewType viewType) {
        this.position = position;
        this.viewType = viewType;
    }

    public int getPosition() {
        return position;
    }

    public CustomAdapter.ViewType getViewType() {
        return viewType;
    }
}
