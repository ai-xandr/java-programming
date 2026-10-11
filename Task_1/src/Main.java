import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1-slosh, 2-umn, 3-del, 4-transp, 5-det, 0-vyhod");
            int ch = sc.nextInt();
            if (ch == 0) break;

            try {
                if (ch == 4 || ch == 5) {
                    System.out.print("razmer (stroki stolbcy): ");
                    int r = sc.nextInt();
                    int c = sc.nextInt();
                    CMatrix m = readMtrx(sc, r, c);

                    if (ch == 4) {
                        System.out.println("rezultat:");
                        m.transp().print();
                    } else {
                        System.out.println("det = " + m.det());
                    }
                } else {
                    System.out.print("matr A razmer (stroki stolbcy): ");
                    int r1 = sc.nextInt();
                    int c1 = sc.nextInt();
                    System.out.println("vvod matr A:");
                    CMatrix m1 = readMtrx(sc, r1, c1);

                    System.out.print("matr B razmer (stroki stolbcy): ");
                    int r2 = sc.nextInt();
                    int c2 = sc.nextInt();
                    System.out.println("vvod matr B:");
                    CMatrix m2 = readMtrx(sc, r2, c2);

                    System.out.println("rezultat:");
                    if (ch == 1) m1.add(m2).print();
                    if (ch == 2) m1.mul(m2).print();
                    if (ch == 3) m1.div(m2).print();
                }
            } catch (Exception e) {
                System.out.println("oshibka: " + e.getMessage());
            }
        }
    }

    private static CMatrix readMtrx(Scanner sc, int rw, int cl) {
        CMatrix m = new CMatrix(rw, cl);
        for (int i = 0; i < rw; i++) {
            for (int j = 0; j < cl; j++) {
                System.out.print("elem [" + i + "][" + j + "] (re im): ");
                double r = sc.nextDouble();
                double i2 = sc.nextDouble();
                m.dt[i][j] = new Complex(r, i2);
            }
        }
        return m;
    }
}
