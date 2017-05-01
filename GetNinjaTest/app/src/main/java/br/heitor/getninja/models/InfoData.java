package br.heitor.getninja.models;

import java.util.List;

public class InfoData {
    private String label;
    private List<String> value;

    public InfoData() {
    }

    public InfoData(String label, List<String> value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public String getValuesInString() {
        String valueInString = "";
        for (String val : value) {
            valueInString += val + ", ";
        }

        valueInString = valueInString.substring(0, valueInString.lastIndexOf(","));
        return valueInString + "\n";
    }
}
