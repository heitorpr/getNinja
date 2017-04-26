package br.heitor.getninja.collections;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import br.heitor.getninja.models.Lead;

public class LeadCollection extends Collection<Lead> {
    @SerializedName("leads")
    private List<Lead> list;

    public LeadCollection() {
        this.list = new ArrayList<>();
    }

    public List<Lead> getList() {
        return list;
    }

    @Override
    public void setList(List<Lead> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Lead get(int position) {
        return list.get(position);
    }
}
