package br.heitor.getninja.collections;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import br.heitor.getninja.models.Offer;

public class OfferCollection extends Collection<Offer> {
    @SerializedName("offers")
    private List<Offer> list;

    public OfferCollection() {
        this.list = new ArrayList<>();
    }

    public List<Offer> getList() {
        return list;
    }

    @Override
    public void setList(List<Offer> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Offer get(int position) {
        return list.get(position);
    }
}
