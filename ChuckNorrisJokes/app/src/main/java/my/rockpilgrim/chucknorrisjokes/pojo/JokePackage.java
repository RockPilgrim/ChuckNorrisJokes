package my.rockpilgrim.chucknorrisjokes.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class JokePackage {

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("value")
    @Expose
    private List<Joke> jokeList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Joke> getJokeList() {
        return jokeList;
    }

    public void setJokeList(List<Joke> jokeList) {
        this.jokeList = jokeList;
    }
}
