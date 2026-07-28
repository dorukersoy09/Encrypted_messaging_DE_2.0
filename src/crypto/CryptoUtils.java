package crypto;

public class CryptoUtils {
    private static String alphabet;

    public static void buildA() {
        if (alphabet != null) return;
        String alph = "";
        for (int i = 32; i <= 126; i++) {
            alph += (char) i;
        }
        String alphabet2 = alph + alph;
        alphabet = alphabet2;
    }

    public static String encrypt(String text) {
        buildA();
        String encripted = "";
        for(int i = 0; i<text.length(); i++){
                    char s1 = text.charAt(i);
                    int i1 = alphabet.indexOf(s1);
                    i1+=18;
                    encripted += alphabet.charAt(i1);
        }   
        return encripted;
    }

    public static String decrypt(String text) {
        buildA();
        String decrypted = "";
        for(int i = 0; i<text.length(); i++){
                    char s1 = text.charAt(i);
                    int i1 = alphabet.indexOf(s1);
                    if(i1<18){
                        i1+=95;
                    }
                    i1-=18;
                    decrypted += alphabet.charAt(i1);
        }  
        return decrypted;
    }
}