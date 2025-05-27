import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class GitHubApiClient {
    private static final String GITHUB_API_URL = "https://api.github.com";
    private static final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        try {
            // Search for repositories about "java http client"
            searchRepositories("java http client");

            // Get details of a specific repository (e.g., OpenJDK's jdk repository)
            getRepositoryDetails("openjdk", "jdk");

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void searchRepositories(String query) throws IOException, InterruptedException {
        String encodedQuery = query.replace(" ", "+");
        String searchUrl = GITHUB_API_URL + "/search/repositories?q=" + encodedQuery + "&sort=stars";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(searchUrl))
                .header("Accept", "application/vnd.github.v3+json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Search Response Status: " + response.statusCode());

        if (response.statusCode() == 200) {
            var searchResults = mapper.readTree(response.body());
            var items = searchResults.get("items");

            System.out.println("\nTop repositories for '" + query + "':");
            System.out.println("----------------------------------------");

            List<GitHubRepo> repos = mapper.readValue(
                    items.toString(),
                    new TypeReference<List<GitHubRepo>>() {
                    });

            repos.stream()
                    .limit(5)
                    .forEach(System.out::println);
        }
    }

    private static void getRepositoryDetails(String owner, String repo) throws IOException, InterruptedException {
        String repoUrl = String.format("%s/repos/%s/%s", GITHUB_API_URL, owner, repo);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(repoUrl))
                .header("Accept", "application/vnd.github.v3+json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("\nRepository Details Response Status: " + response.statusCode());

        if (response.statusCode() == 200) {
            GitHubRepo repository = mapper.readValue(response.body(), GitHubRepo.class);
            System.out.println("\nRepository Details:");
            System.out.println("----------------------------------------");
            System.out.println(repository);
        }
    }
}