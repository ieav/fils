

/**
 * 
 * 
 */
public class DemoIo {
	/**这是一个125 MB (131,143,320 字节)文件*/
    private static final String SRC = "C:\\Users\\Desktop\\RAF.txt";

    public static void main(String[] args) throws IOException {

        System.out.println("开始测试！");
        // BufferedReader字符流读取文件
        bufferedReader();

        // 字节流读取文件
        inputStream();

        // 字节流读取文件（加入缓冲）
        BufferedInputStream();
    }

    /**
     * 为字符流专门打造的(适合字符的操作)
     *
     * @throws IOException
     */
    private static void bufferedReader() throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(SRC), StandardCharsets.UTF_8));
        try {
            while (true) {
                String s = bufferedReader.readLine();
                if (s != null) {
//                    System.out.println(s);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferedReader.close();
        long end = System.currentTimeMillis();
        System.out.println("bufferedReaderDemo耗时" + (end - start));
    }

    /**
     * 字节流读取文件（通用型的）
     *
     * 
     */
    private static void inputStream() throws IOException {
        long start = System.currentTimeMillis();
        InputStream inputStream = new FileInputStream(SRC);

        byte[] bytes = new byte[1024];
        while (true) {
            int read = inputStream.read(bytes);
            if (read != -1) {
//                System.out.println(new String(bytes, 0, read));
            } else {
                break;
            }
        }
        inputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("inputStream耗时" + (end - start));

    }

    /**
     * 这个最快
     * 字节流读取文件（通用型的）+加上缓冲
     *
     * @throws IOException
     */
    private static void BufferedInputStream() throws IOException {
        long start = System.currentTimeMillis();
        InputStream inputStream = new FileInputStream(SRC);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bytes = new byte[1024];
        while (true) {
            int read = bufferedInputStream.read(bytes);
            if (read != -1) {
//                System.out.println(new String(bytes, 0, read));
            } else {
                break;
            }
        }
        inputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("BufferedInputStream耗时" + (end - start));
    }
}

