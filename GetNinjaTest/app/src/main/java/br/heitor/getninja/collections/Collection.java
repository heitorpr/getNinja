package br.heitor.getninja.collections;

import java.util.List;

import br.heitor.getninja.managers.FetchFactory;

public abstract class Collection<T> {
    public abstract void setList(List<T> list);

    public abstract int size();

    public abstract T get(int position);

    public void fetch() {
        FetchFactory.fetch(this);
    }
}
