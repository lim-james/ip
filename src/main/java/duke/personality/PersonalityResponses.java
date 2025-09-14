package duke.personality;

import java.util.Random;

/**
 * Manages personality responses for the chatbot, providing varied responses based on command types
 * and response outcomes.
 */
public enum PersonalityResponses {
    // Success responses
    DELETE_SUCCESS(
            "Noted. I've removed this task:\n %s",
            "Poof! Task vanished into the digital void:\n %s",
            "Another one bites the dust:\n %s",
            "Consider it done! Removed:\n %s"),

    MARK_SUCCESS(
            "Nice! I've marked this task as done:\n %s",
            "Woohoo! Task conquered:\n %s",
            "Victory! Another task falls:\n %s",
            "Excellent work! Completed:\n %s"),

    UNMARK_SUCCESS(
            "OK, I've marked this task as not done yet:\n %s",
            "Back to the drawing board with:\n %s",
            "Hmm, second thoughts? Unmarked:\n %s",
            "No worries, we'll tackle this later:\n %s"),

    ADD_SUCCESS(
            "Got it. I've added this task:\n %s",
            "Roger that! New mission added:\n %s",
            "Task locked and loaded:\n %s",
            "Added to your ever-growing empire:\n %s"),

    FIND_SUCCESS(
            "Here are the matching tasks in your list:\n%s",
            "Found some treasures for you:\n%s",
            "Search results coming right up:\n%s",
            "Here's what I dug up:\n%s"),

    LIST_RESPONSE(
            "%s", // Simple pass-through since TaskList.toString() handles formatting
            "Behold, your magnificent task empire:\n%s",
            "Here's your current quest log:\n%s",
            "Your tasks, as requested:\n%s"),

    WELCOME(
            "Hello! I'm Peter. How can I help you?",
            "Greetings! Peter at your service. What's on the agenda today?",
            "Hey there! Peter here, ready to tackle your tasks!",
            "Welcome back! I'm Peter, your friendly task assistant. What can we accomplish today?",
            "Hi! Peter reporting for duty. Let's get things done!",
            "Salutations! I'm Peter, and I'm here to help you conquer your to-do list!"),

    GENERAL_ERROR(
            "OH DEAR! %s",
            "Oops! Something went sideways: %s",
            "Houston, we have a problem: %s",
            "Well, that didn't go as planned: %s");

    private final String[] responses;
    private static final Random random = new Random();

    PersonalityResponses(String... responses) {
        this.responses = responses;
    }

    /**
     * Gets a random response from this category, formatted with the provided arguments.
     *
     * @param args Arguments to format into the response template
     * @return A formatted response string
     */
    public String getRandomResponse(Object... args) {
        String template = responses[random.nextInt(responses.length)];
        return String.format(template, args);
    }

    /**
     * Gets a specific response by index (useful for testing or specific scenarios).
     *
     * @param index The index of the response to retrieve
     * @param args Arguments to format into the response template
     * @return A formatted response string
     */
    public String getResponse(int index, Object... args) {
        if (index < 0 || index >= responses.length) {
            return getRandomResponse(args);
        }
        return String.format(responses[index], args);
    }
}
