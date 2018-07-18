package me.picknchew.teachassistapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mark {

    @SerializedName("mark")
    @Expose
    private String mark;

    @SerializedName("outOf")
    @Expose
    private String outOf;

    @SerializedName("weight")
    @Expose
    private String weight;

    // empty constructor for gson
    public Mark() {}

    public Mark(String mark, String outOf, String weight) {
        this.mark = mark;
        this.outOf = outOf;
        this.weight = weight;
    }

    public String getMark() {
        return mark;
    }

    public String getOutOf() {
        return outOf;
    }

    public String getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark1 = (Mark) o;

        if (!mark.equals(mark1.mark)) return false;
        if (!outOf.equals(mark1.outOf)) return false;
        return weight.equals(mark1.weight);
    }

    @Override
    public int hashCode() {
        int result = mark.hashCode();
        result = 31 * result + outOf.hashCode();
        result = 31 * result + weight.hashCode();
        return result;
    }
}
