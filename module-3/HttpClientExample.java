import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientExample {
    private static final String GITHUB_API_URL = "https://api.github.com";
    private static final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) {
        try {
            // Get information about a specific GitHub repository
            getRepositoryInfo("openjdk", "jdk");

            // Search for repositories
            searchRepositories("java http client");

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void getRepositoryInfo(String owner, String repo) throws IOException, InterruptedException {
        String url = String.format("%s/repos/%s/%s", GITHUB_API_URL, owner, repo);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/vnd.github.v3+json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Repository Info Response Status: " + response.statusCode());
        System.out.println("Response Headers: " + response.headers());
        System.out.println("\nResponse Body:");
        System.out.println(formatJson(response.body()));
    }

    private static void searchRepositories(String query) throws IOException, InterruptedException {
        String encodedQuery = query.replace(" ", "+");
        String url = GITHUB_API_URL + "/search/repositories?q=" + encodedQuery + "&sort=stars";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/vnd.github.v3+json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("\nSearch Response Status: " + response.statusCode());
        System.out.println("Response Headers: " + response.headers());
        System.out.println("\nResponse Body (first 1000 characters):");
        String body = response.body();
        System.out.println(formatJson(body.substring(0, Math.min(body.length(), 1000))));
    }

    // Simple JSON formatting
    private static String formatJson(String json) {
        StringBuilder formatted = new StringBuilder();
        int indent = 0;
        boolean inQuotes = false;

        for (char c : json.toCharArray()) {
            switch (c) {
                case '{':
                case '[':
                    formatted.append(c).append('\n').append("  ".repeat(++indent));
                    break;
                case '}':
                case ']':
                    formatted.append('\n').append("  ".repeat(--indent)).append(c);
                    break;
                case ',':
                    if (!inQuotes) {
                        formatted.append(c).append('\n').append("  ".repeat(indent));
                    } else {
                        formatted.append(c);
                    }
                    break;
                case '"':
                    inQuotes = !inQuotes;
                    formatted.append(c);
                    break;
                default:
                    formatted.append(c);
            }
        }
        return formatted.toString();
    }
}