import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubRepo {
    private long id;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private String description;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("stargazers_count")
    private int stars;
    @JsonProperty("forks_count")
    private int forks;
    private String language;

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return String.format("Repository: %s%n" +
                "Description: %s%n" +
                "URL: %s%n" +
                "Language: %s%n" +
                "Stars: %d%n" +
                "Forks: %d%n",
                fullName,
                description != null ? description : "No description",
                htmlUrl,
                language != null ? language : "Not specified",
                stars,
                forks);
    }
}