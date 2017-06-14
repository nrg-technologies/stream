public class Usage {

    public static void main(String[] args)
            throws Exception {
        // Token class initialization with API secret key
        Token token = new Token("08D1230B84123284BD24CAD64B59DF94");
        // Setting agent name
        token.setData("Agent007");
        // Token generation
        String result = token.generate(System.currentTimeMillis()/200);
        // Printing result
        System.out.println(result);
    }
}