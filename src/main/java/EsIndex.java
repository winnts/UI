import java.util.LinkedList;
import java.util.List;

/**
 * Created by adyachenko on 12.11.15.
 */
public class EsIndex {
    private String name;
    private String description;
    private List<String> categories;
    private List<String> tags;

    public String getName() {return name;}
    public String getDescription() {return description;}
    public List<String> getCategories() {return categories;}
    public List<String> getTags() {return tags;}

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategories(List<String> categories){
        this.categories = categories;
    }

    public void setTags(List<String> tags){
        this.tags = tags;
    }

    public void addCategory(String category) {
        if (this.categories == null)
            this.categories = new LinkedList<String>();
        this.categories.add(category);
    }

    public void addTag(String tag) {
        if (this.tags == null)
            this.tags = new LinkedList<String>();
        this.tags.add(tag);
    }

    @Override
    public String toString () {
        return "Stored [name=" + name + ", description=" + description + ", categories="
                + categories + ", tags=" + tags + "]";
    }
}
