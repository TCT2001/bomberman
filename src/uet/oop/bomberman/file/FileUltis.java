package uet.oop.bomberman.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUltis {
    private static final String PATH_SETTING = "res/game/setting";
    private static final String PATH_HIGHTSCORE = "res/game/hightscore";

    public static String[] getKeyNav() {
        String s[] = new String[5];
        File myObj = new File(PATH_SETTING);
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
            for (int i = 0; i < 5; i++) {
                s[i] = myReader.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        myReader.close();
        return s;
    }

    public static void setKeyNav(String keys[]) {
        try {
            FileWriter myWriter = new FileWriter(PATH_SETTING);
            for (String key : keys) {
                myWriter.write(key + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHighScore() {
        String score = "0";
        try {
            File myObj = new File(PATH_HIGHTSCORE);
            Scanner myReader = new Scanner(myObj);
            score = myReader.nextLine();
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return score;
    }

    public static void setHighScore(int score) {
        try {
            FileWriter myWriter = new FileWriter(PATH_HIGHTSCORE);
            myWriter.write(String.valueOf(score));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
