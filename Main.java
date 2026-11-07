import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String name;
    private int price;
    private int stock;

    //コンストラクタを作成する
    public Product(String name, int price, int stock){
    this.name = name;
    this.price = price;
    this.stock = stock;
    }

    public void showInfo() {
        //商品名、価格、在庫数を表示する
        System.out.println("商品名：" + name);
        System.out.println("価格：" + price + "円");
        System.out.println("在庫：" + stock + "個");
    }

    // 商品名を返す
    public String getName() {
        return name;
    }

    //販売個数の判定
    public boolean sell(int quantity) {
        if (quantity <= 0 || quantity > stock) {
            return false;
        } else {
            stock -= quantity;
            return true;
        }
    }
    
    //在庫補充の判定
    public boolean restock(int quantity) {
        if (quantity <= 0) {
            return false;
        } else {
            stock += quantity;
            return true;
        }
    }
}

//FoodProductクラスを作成する
class FoodProduct extends Product {

    private String expirationDate;

    public FoodProduct(
            String name,
            int price,
            int stock,
            String expirationDate) {

        //super()で親クラスのコンストラクタを呼び出す
        super(name, price, stock);

        //賞味期限をフィールドに保存する
        this.expirationDate = expirationDate;
    }

    //showInfo()をオーバーライドする
    @Override
    public void showInfo() {
    super.showInfo();
    System.out.println("賞味期限：" + expirationDate);
    }
}

public class Main {
    public static void main(String[] args) {

        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product("みかん", 100, 5));
        products.add(new FoodProduct("牛乳", 200, 10, "2026-11-15"));
        products.add(new Product("りんご", 150, 8));

        Scanner scanner = new Scanner(System.in, "UTF-8");

        boolean running = true;

        //runningがtrueの間、繰り返す
            while(running){
                System.out.println("===== 商品管理システム =====");
                System.out.println("1. 商品一覧");
                System.out.println("2. 商品検索");
                System.out.println("3. 商品販売");
                System.out.println("4. 在庫補充");
                System.out.println("0. 終了");
                System.out.print("番号を入力してください：");
    
                int choice = scanner.nextInt();
                scanner.nextLine(); // 残っている改行を読み捨てる

                //switch文でchoiceの値を判定する
                switch (choice) {
                    case 1:
                        System.out.println("===== 商品一覧 =====");
                        // TODO 1：拡張for文でproductsを取り出す
                        for (Product product : products){
                        product.showInfo();
                        }
                        break;

                    case 2:{
                        System.out.print("検索する商品名を入力してください：");

                        //scanner.nextLine()で商品名を取得する
                        String searchName = scanner.nextLine();
                        //デバック用
                        System.out.println("入力された商品名：[" + searchName + "]");
                        System.out.println("文字数：" + searchName.length());

                        boolean found = false;

                        //拡張for文で商品を検索する
                        for (Product product : products) {
                            //デバック用
                            System.out.println("比較中：[" + product.getName() + "]");

                            //商品名が一致するか判定する
                            if (product.getName().equals(searchName)) {
                                product.showInfo();
                                //foundをtrueにする
                                found = true;
                                break;
                            }
                        }

                        //見つからなかった場合のメッセージ
                        if(!found){
                        System.out.println("商品が見つかりません");
                        }
                        break;
                    }

                    case 3:{
                        System.out.print("販売する商品名：");
                        //商品名を取得
                        String searchName = scanner.nextLine();

                        System.out.print("販売数量：");
                        //販売数量を取得
                        int searchQuantity = scanner.nextInt();

                        boolean foundProduct = false;

                        //拡張for文で商品を検索
                        for (Product product : products) {

                            //商品名が一致したらfoundProduct = true にする
                            if (product.getName().equals(searchName)) {
                                foundProduct = true;
                                boolean success = product.sell(searchQuantity);
                                if (success) {
                                    System.out.println("販売しました");
                                } else {
                                    System.out.println("在庫不足または数量が不正です");
                                }
                                break;
                            }
                        }

                        //商品が見つからなかった場合の処理
                        if(!foundProduct){
                            System.out.println("商品が見つかりません");
                        }
                        break;
                    }

                    case 4:{
                        System.out.print("補充する商品名：");
                        //商品名を取得
                        String searchName = scanner.nextLine();

                        System.out.print("補充数量：");
                        //補充数量を取得
                        int searchRestock = scanner.nextInt();

                        boolean foundProduct = false;

                        //拡張for文で商品を検索
                        for (Product product : products) {
                            //商品名が一致したらfoundProduct = true にする
                            if (product.getName().equals(searchName)) {
                                foundProduct = true;
                                boolean success = product.restock(searchRestock);
                                if (success) {
                                    System.out.println("在庫を補充しました");
                                } else {
                                    System.out.println("補充数量が不正です");
                                }
                                break;
                            }
                        }

                        //商品が見つからなかった場合の処理
                        if(!foundProduct){
                            System.out.println("商品が見つかりません");
                        }
                        break;
                    }

                    case 0:
                        System.out.println("システムを終了します");
                        running = false;
                        break;
                    default:
                        System.out.println("正しい番号を入力してください");
                }
            }
        scanner.close();
    }
}