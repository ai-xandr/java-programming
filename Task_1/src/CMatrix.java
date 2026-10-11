public class CMatrix {
    Complex[][] dt;
    int rw, cl;

    public CMatrix(int r, int c) {
        rw = r;
        cl = c;
        dt = new Complex[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                dt[i][j] = new Complex(0, 0);
            }
        }
    }

    public CMatrix add(CMatrix oth) {
        if (rw != oth.rw || cl != oth.cl) {
            throw new IllegalArgumentException("razmery ne sovpadayut");
        }
        CMatrix res = new CMatrix(rw, cl);
        for (int i = 0; i < rw; i++) {
            for (int j = 0; j < cl; j++) {
                res.dt[i][j] = dt[i][j].add(oth.dt[i][j]);
            }
        }
        return res;
    }

    public CMatrix mul(CMatrix oth) {
        if (cl != oth.rw) {
            throw new IllegalArgumentException("nelzya umnozhit");
        }
        CMatrix res = new CMatrix(rw, oth.cl);
        for (int i = 0; i < rw; i++) {
            for (int j = 0; j < oth.cl; j++) {
                Complex sm = new Complex(0, 0);
                for (int k = 0; k < cl; k++) {
                    sm = sm.add(dt[i][k].mul(oth.dt[k][j]));
                }
                res.dt[i][j] = sm;
            }
        }
        return res;
    }

    public CMatrix transp() {
        CMatrix res = new CMatrix(cl, rw);
        for (int i = 0; i < rw; i++) {
            for (int j = 0; j < cl; j++) {
                res.dt[j][i] = dt[i][j];
            }
        }
        return res;
    }

    public Complex det() {
        if (rw != cl) {
            throw new IllegalArgumentException("ne kvadratnaya");
        }
        return calcDet(dt);
    }

    private Complex calcDet(Complex[][] m) {
        int n = m.length;
        if (n == 1) return m[0][0];
        if (n == 2) {
            return m[0][0].mul(m[1][1]).sub(m[0][1].mul(m[1][0]));
        }

        Complex r = new Complex(0, 0);
        for (int j = 0; j < n; j++) {
            Complex[][] mnr = getMnr(m, 0, j);
            Complex trm = m[0][j].mul(calcDet(mnr));
            if (j % 2 != 0) {
                trm = new Complex(0, 0).sub(trm);
            }
            r = r.add(trm);
        }
        return r;
    }

    private Complex[][] getMnr(Complex[][] m, int row, int col) {
        int n = m.length;
        Complex[][] mnr = new Complex[n - 1][n - 1];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                mnr[r][c] = m[i][j];
                c++;
            }
            r++;
        }
        return mnr;
    }

    public CMatrix inv() {
        Complex d = det();
        if (d.re == 0 && d.im == 0) {
            throw new ArithmeticException("det = 0");
        }

        CMatrix adj = new CMatrix(rw, cl);
        for (int i = 0; i < rw; i++) {
            for (int j = 0; j < cl; j++) {
                Complex[][] mnr = getMnr(dt, i, j);
                Complex mDet = calcDet(mnr);
                if ((i + j) % 2 != 0) {
                    mDet = new Complex(0, 0).sub(mDet);
                }
                adj.dt[i][j] = mDet;
            }
        }
        adj = adj.transp();
        for (int i = 0; i < rw; i++) {
            for (int j = 0; j < cl; j++) {
                adj.dt[i][j] = adj.dt[i][j].div(d);
            }
        }
        return adj;
    }

    public CMatrix div(CMatrix oth) {
        return mul(oth.inv());
    }

    public void print() {
        for (int i = 0; i < rw; i++) {
            for (int j = 0; j < cl; j++) {
                System.out.print(dt[i][j].toString() + "\t");
            }
            System.out.println();
        }
    }
}
