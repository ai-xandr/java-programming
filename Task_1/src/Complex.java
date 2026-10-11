public class Complex {
    double re, im;

    public Complex(double r, double i) {
        re = r;
        im = i;
    }

    public Complex add(Complex oth) {
        return new Complex(re + oth.re, im + oth.im);
    }

    public Complex sub(Complex oth) {
        return new Complex(re - oth.re, im - oth.im);
    }

    public Complex mul(Complex oth) {
        return new Complex(re * oth.re - im * oth.im, re * oth.im + im * oth.re);
    }

    public Complex div(Complex oth) {
        double znam = oth.re * oth.re + oth.im * oth.im;
        if (znam == 0) {
            throw new ArithmeticException("delenie na 0"); 
        }
        double nRe = (re * oth.re + im * oth.im) / znam;
        double nIm = (im * oth.re - re * oth.im) / znam;
        return new Complex(nRe, nIm);
    }

    @Override
    public String toString() {
        if (im == 0) return String.valueOf(re);
        if (re == 0) return im + "i";
        if (im < 0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }
}
