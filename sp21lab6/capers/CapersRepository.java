package capers;

import java.io.File;
import java.io.IOException;

import static capers.Utils.*;

/** A repository for Capers 
 * @author ChrisWang13
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab6 folder
 *    - dogs/ -- folder containing all the persistent data for dogs
 *    - story -- file containing the current story
 */

public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder, use path string join function to create root for repo. */
    static final File CAPERS_FOLDER = join(CWD, ".capers");

     /**
     * Do require filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure
     *
     * .capers/ -- top level folder for all persistent data in your lab6 folder
     *    - dogs/ -- folder containing all the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        CAPERS_FOLDER.mkdir();
        Dog.DOG_FOLDER.mkdir();
        // TODO: story file
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        // Create dest file descriptor in .caper/file
        File story = join(CAPERS_FOLDER, "story");
        // Check if story has been created or not, insert
        // (oldText + '\n' + text) to overwrite story
        String oldText;
        String newText;
        if (story.exists()) {
            oldText = readContentsAsString(story);
            newText = oldText + '\n' + text;
        } else {
            newText = text;
        }
        Utils.writeContents(story, newText);
        System.out.println(newText);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        // Create an instance of dog
        Dog dog = new Dog(name, breed, age);
        dog.saveDog();
        System.out.println(dog.toString());
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog dog = Dog.fromFile(name);
        dog.haveBirthday();
        dog.saveDog();
    }
}
