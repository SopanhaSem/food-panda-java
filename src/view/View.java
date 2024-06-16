package view;

import java.util.Scanner;

public class View {
    public static int menu(){
        System.out.println("=".repeat(60));
        System.out.println(
                "//  ███████╗ ██████╗  ██████╗ ██████╗       ██████╗  █████╗ ███╗   ██╗██████╗  █████╗ \n" +
                "//  ██╔════╝██╔═══██╗██╔═══██╗██╔══██╗      ██╔══██╗██╔══██╗████╗  ██║██╔══██╗██╔══██╗\n" +
                "//  █████╗  ██║   ██║██║   ██║██║  ██║█████╗██████╔╝███████║██╔██╗ ██║██║  ██║███████║\n" +
                "//  ██╔══╝  ██║   ██║██║   ██║██║  ██║╚════╝██╔═══╝ ██╔══██║██║╚██╗██║██║  ██║██╔══██║\n" +
                "//  ██║     ╚██████╔╝╚██████╔╝██████╔╝      ██║     ██║  ██║██║ ╚████║██████╔╝██║  ██║\n" +
                "//  ╚═╝      ╚═════╝  ╚═════╝ ╚═════╝       ╚═╝     ╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝ ╚═╝  ╚═╝\n" +
                "//                                                                                    ");
        System.out.println("=".repeat(60));
        System.out.println(
                "➕ [1]. Add New Product         ➕ [6]. Add New Customer         ➕ [11]. Add New Order" +"\n"+
                "🪟 [2]. List All Product        🪟 [7]. List All Customer        🪟 [12]. List All Order" + "\n"+
                "🤔 [3]. Find Product By ID      🤔 [8]. Find Customer By ID      🤔 [13]. Find Order By ID" +"\n"+
                "🧑‍🔧 [4]. Delete Product By ID    🧑‍🔧 [8]. Find Customer By ID      🧑‍🔧 [14]. Delete Order By ID" +"\n"+
                "‼️ [5]. Update Product By ID    ‼️ [10]. Update Customer By ID   ‼️ [15]. Update Order By ID" +"\n"+
                "🤩 [0-99]. Exit The Program"
        );
        System.out.print("[+] Insert Your Option: ");
        return (new Scanner(System.in)).nextInt();
    }
}
