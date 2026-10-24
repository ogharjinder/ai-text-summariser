import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AiSummarizer{
    public static String summarize(String text, String apiKey){
        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

            String jsonBody = "{\n" + 
        " \"contents\": [{\n" +
        " \"parts\": [{\n" +
        " \"text\": \"Summarize this in one short sentence: " + text + "\"\n" +
        "}]\n" +
        "}]\n" +
        "}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("Content-type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
        .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            return extractTextFromJson(response.body());
        }
        else{
            return "Error: " + response.statusCode();
        }
        
        } catch (Exception e) {
            return "An error occured: " + e.getMessage();
        }
    }

    private static String extractTextFromJson(String jsonResponse){
        String targetKey = "\"text\": \"";
        int startIndex = jsonResponse.indexOf(targetKey);

        if(startIndex == -1){
            return "Could not find text in response.";
        }

        startIndex += targetKey.length();
        int endIndex = jsonResponse.indexOf("\"", startIndex);

        if(endIndex == -1){
            return "Could not parse JSON properly.";
        }

        String rawText = jsonResponse.substring(startIndex, endIndex);
        return rawText.replace("\\n", "\n");
    }
}

