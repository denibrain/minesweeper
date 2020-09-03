public class Main {

    public static void main(String[] args) {
        int k = 0; String z;
        for(Secret v: Secret.values()) {
            if (v.name().startsWith("STAR")) {
                k++;
            }
        }
        System.out.print(k);
    }
}

/* At least two constants start with STAR
enum Secret {
    STAR, CRASH, START, // ...
}
*/