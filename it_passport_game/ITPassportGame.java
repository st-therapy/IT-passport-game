package it_passport_game;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

// ITパスポートの学習ゲームを実行するクラスです。
public class ITPassportGame {
    public static void main(String[] args) {
        // 3ジャンルそれぞれ5問、合計15問を配列にまとめて管理します。
        String[] questions = {
            "ストラテジ系：企業が経営目標を達成するために、現状と目標の差を分析する手法はどれですか？",
            "ストラテジ系：商品を販売する相手を年齢や地域などで分けることを何といいますか？",
            "ストラテジ系：情報システムの導入前に、費用と効果を比較して実施価値を検討することは何ですか？",
            "ストラテジ系：企業の強み・弱みと、外部環境の機会・脅威を整理する分析手法はどれですか？",
            "ストラテジ系：顧客にとっての商品の価値を高め、競争優位を築くための活動全体を何といいますか？",
            "マネジメント系：プロジェクトの作業を細かい単位に分解して整理した構成図は何ですか？",
            "マネジメント系：システム開発で、要件定義からテストまで順番に進める開発モデルは何ですか？",
            "マネジメント系：サービスの提供内容や品質目標を利用者と合意した文書は何ですか？",
            "マネジメント系：プロジェクトの進捗を管理するとき、予定と実績を比較するために使う図は何ですか？",
            "マネジメント系：ソフトウェアの不具合を見つけるため、仕様に基づいて動作を確認する作業は何ですか？",
            "テクノロジ系：コンピュータが扱う情報の最小単位は何ですか？",
            "テクノロジ系：同じネットワーク内の機器を識別するために使うIPアドレスの役割は何ですか？",
            "テクノロジ系：データを一定の規則で変換し、第三者に読まれにくくする処理は何ですか？",
            "テクノロジ系：複数のコンピュータをつなぎ、データをやり取りできるようにした仕組みは何ですか？",
            "テクノロジ系：CPUが一度に処理できるデータの幅を表す単位は何ですか？"
        };

        // 各問題の4つの選択肢を、問題と同じ添え字の行に格納します。
        String[][] choices = {
            {"ギャップ分析", "損益分岐点分析", "ABC分析", "回帰分析"},
            {"デバッグ", "セグメンテーション", "正規化", "バックアップ"},
            {"費用対効果分析", "コードレビュー", "稼働監視", "棚卸し"},
            {"SWOT分析", "時系列分析", "クラスタ分析", "ベンチマークテスト"},
            {"バリューチェーン", "ガントチャート", "フィッシング", "正規化"},
            {"WBS", "DFD", "UML", "SLA"},
            {"アジャイルモデル", "ウォーターフォールモデル", "スパイラルモデル", "プロトタイプモデル"},
            {"RFP", "SLA", "FAQ", "WBS"},
            {"ガントチャート", "散布図", "状態遷移図", "特性要因図"},
            {"テスト", "要件定義", "運用", "企画"},
            {"バイト", "ビット", "ピクセル", "パケット"},
            {"機器を識別するための番号", "画面の色を決める番号", "ファイルを圧縮する番号", "時刻を記録する番号"},
            {"暗号化", "複製", "圧縮", "集計"},
            {"ネットワーク", "データベース", "アルゴリズム", "インターフェース"},
            {"ビット幅", "画素数", "通信速度", "記憶容量"}
        };

        // 正解番号は選択肢の番号（1〜4）で管理します。
        int[] answers = {1, 2, 1, 1, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1};

        // 問題ごとの解説を、問題文や選択肢と同じ添え字で管理します。
        String[] explanations = {
            "ギャップ分析は、現状と目標の差を明らかにし、必要な対策を考える方法です。",
            "セグメンテーションは、市場を共通の特徴を持つグループに分けることです。",
            "費用対効果分析では、導入にかかる費用と期待できる効果を比較します。",
            "SWOT分析では、強み・弱み・機会・脅威の4つの観点で状況を整理します。",
            "バリューチェーンは、商品やサービスの価値を生み出す一連の活動を指します。",
            "WBSは、プロジェクトの作業を細分化して階層的に整理したものです。",
            "ウォーターフォールモデルは、工程を順番に進める開発モデルです。",
            "SLAは、サービスの内容や目標とする品質について利用者と提供者が合意する文書です。",
            "ガントチャートは、作業の予定や実績を時間軸に沿って表す進捗管理の図です。",
            "テストでは、プログラムが仕様どおりに動作するかを確認し、不具合を検出します。",
            "ビットは、0または1で表される情報の最小単位です。8ビットで1バイトになります。",
            "IPアドレスは、ネットワーク上で機器を識別し、通信相手を特定するために使います。",
            "暗号化は、決められた鍵などを使ってデータを変換し、内容を読みにくくする処理です。",
            "ネットワークは、コンピュータなどを接続して情報を交換する仕組みです。",
            "ビット幅は、CPUが一度に扱えるデータ量を示します。"
        };

        // Scannerはここで1つだけ作り、必要なメソッドに渡して使います。
        Scanner scanner = new Scanner(System.in);
        boolean quizPlayed = false;
        int latestCorrectCount = 0;
        boolean running = true;

        // 終了メニューが選ばれるまで、メニューと選択処理を繰り返します。
        while (running) {
            showTitle();
            showMenu();
            int menuNumber = readInteger(scanner, "番号を入力してください：");

            switch (menuNumber) {
                case 1:
                    System.out.println("クイズを開始します");
                    // クイズをやり直すたびに、runQuiz内で正解数を0から数えます。
                    latestCorrectCount = runQuiz(scanner, questions, choices, answers, explanations);
                    quizPlayed = true;
                    showResults(5, latestCorrectCount);
                    break;
                case 2:
                    System.out.println("準備中です");
                    break;
                case 3:
                    // クイズをまだしていない場合は、記録がないことを知らせます。
                    if (quizPlayed) {
                        showResults(5, latestCorrectCount);
                    } else {
                        System.out.println("学習記録はまだありません");
                    }
                    break;
                case 4:
                    System.out.println("ゲームを終了します");
                    running = false;
                    break;
                default:
                    System.out.println("1〜4の番号を入力してください");
                    break;
            }
        }

        // ゲームを終了する前に、Scannerを閉じます。
        scanner.close();
    }

    // タイトルを表示するメソッドです。
    public static void showTitle() {
        System.out.println("================================");
        System.out.println("       IT PASSPORT QUEST");
        System.out.println("================================");
    }

    // メニューを表示するメソッドです。
    public static void showMenu() {
        System.out.println("1. クイズに挑戦する");
        System.out.println("2. 苦手問題を復習する");
        System.out.println("3. 成績を確認する");
        System.out.println("4. ゲームを終了する");
    }

    // 整数が入力されるまで尋ね、入力された整数を戻り値として返します。
    public static int readInteger(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("整数を入力してください。");
            scanner.next(); // 数字以外の入力を読み捨て、再入力できるようにします。
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }

    // 問題バンクから重複なしで5問を出題し、正解数を戻り値として返します。
    public static int runQuiz(Scanner scanner, String[] questions,
            String[][] choices, int[] answers, String[] explanations) {
        int correctCount = 0; // クイズ開始時に正解数をリセットします。

        ArrayList<Integer> questionOrder = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) questionOrder.add(i);
        Collections.shuffle(questionOrder);

        for (int round = 0; round < 5; round++) {
            int i = questionOrder.get(round);
            System.out.println();
            System.out.println("問題 " + (i + 1) + " / " + questions.length);
            System.out.println(questions[i]);

            // その問題に対応する4つの選択肢を表示します。
            for (int j = 0; j < choices[i].length; j++) {
                System.out.println((j + 1) + ". " + choices[i][j]);
            }

            int userAnswer = readAnswer(scanner);
            // 判定メソッドの戻り値がtrueなら、正解数を1増やします。
            if (checkAnswer(userAnswer, answers[i], choices[i])) {
                correctCount++;
            }

            // どちらの結果でも、次の問題に進む前に解説を表示します。
            System.out.println("解説：" + explanations[i]);
        }
        return correctCount;
    }

    // 1〜4の有効な回答が入力されるまで、同じ問題の回答を受け付けます。
    public static int readAnswer(Scanner scanner) {
        while (true) {
            int answer = readInteger(scanner, "回答番号を入力してください：");
            if (answer >= 1 && answer <= 4) {
                return answer;
            }
            // 無効な回答ではこのメソッドを抜けず、同じ問題で再入力します。
            System.out.println("1〜4の番号を入力してください。");
        }
    }

    // 回答を判定してメッセージを表示し、正解ならtrueを返します。
    public static boolean checkAnswer(int userAnswer, int correctAnswer, String[] choices) {
        if (userAnswer == correctAnswer) {
            System.out.println("正解！");
            return true;
        } else {
            System.out.println("不正解。正しい回答は「" + correctAnswer + ". "
                    + choices[correctAnswer - 1] + "」です。");
            return false;
        }
    }

    // 成績を計算して表示します。問題数と正解数は引数で受け取ります。
    public static void showResults(int questionCount, int correctCount) {
        int incorrectCount = questionCount - correctCount;
        int score = correctCount * 20;
        // int同士の割り算で小数が失われないよう、double型に変換して計算します。
        double accuracy = (double) correctCount / questionCount * 100;

        System.out.println();
        System.out.println("===== クイズ結果 =====");
        System.out.println("問題数：" + questionCount + "問");
        System.out.println("正解数：" + correctCount + "問");
        System.out.println("不正解数：" + incorrectCount + "問");
        System.out.println("得点：" + score + "点 / 100点");
        System.out.printf("正答率：%.1f%%%n", accuracy);
    }
}
