import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int janken_num = 0;
        int win_num = 0;
        int lose_num = 0;
        int draw_num = 0;

        System.out.println("何回じゃんけんをしますか？");
        int totalGames = Integer.parseInt(scanner.nextLine());

        while (janken_num < totalGames) {
            System.out.println("じゃんけん" + (janken_num + 1) + "回戦目");
            System.out.println("0:グー 1:チョキ 2:パー");

            int input;
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input < 0 || input > 2) {
                    System.out.println("0~2の数字を入力してください。");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("0~2の数字を入力してください。");
                continue;
            }

            janken_num++;
            System.out.println("[あなた]" + getGestureName(input));

            Random rand = new Random();
            int computer_num = rand.nextInt(3);
            System.out.println("[コンピュータ]" + getGestureName(computer_num));

            int result = determineWinner(input, computer_num);
            if (result == 0) {
                draw_num++;
                System.out.println("引き分けです。");
            } else if (result == 1) {
                win_num++;
                System.out.println("あなたの勝ちです！");
            } else {
                lose_num++;
                System.out.println("コンピュータの勝ちです。");
            }
        }

        // 勝率の計算
        double winRate = (double) win_num / totalGames * 100.0;

        System.out.println("ゲーム終了！結果:");
        System.out.println("勝利回数: " + win_num);
        System.out.println("敗北回数: " + lose_num);
        System.out.println("引き分け回数: " + draw_num);
        System.out.println("勝率: " + winRate + "%");

        if (winRate > 50) {
            System.out.println("あなたの勝利！");
        } else if (winRate < 50) {
            System.out.println("コンピュータの勝利！");
        } else {
            System.out.println("引き分けです。");
        }
    }

    public static String getGestureName(int gesture) {
        switch (gesture) {
            case 0:
                return "グー";
            case 1:
                return "チョキ";
            case 2:
                return "パー";
            default:
                return "無効なジェスチャー";
        }
    }

    public static int determineWinner(int player, int computer) {
        if (player == computer) {
            return 0; // 引き分け
        } else if ((player == 0 && computer == 1) || (player == 1 && computer == 2) || (player == 2 && computer == 0)) {
            return 1; // プレイヤーの勝ち
        } else {
            return -1; // コンピュータの勝ち
        }
    }
}
