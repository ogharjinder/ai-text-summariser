import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your API Key: ");
        String apiKey = sc.nextLine().trim();

        if(apiKey.isEmpty()){
            System.out.println("API key cannot be empty!");
            return;
        }

        String text = "Java is a popular programming language, created in 1995. " +
                "It is owned by Oracle, and more than 3 billion devices run Java. " +
                "It is used for mobile applications, desktop applications, web development, and much more.";

        System.out.println("\nOriginal Text:\n" + text);
        System.out.println("\nSending request to API...");

        String summary = AiSummarizer.summarize(text, apiKey);

        System.out.println("\nAI Summary Result:\n" + summary);
        sc.close();
    }
}
